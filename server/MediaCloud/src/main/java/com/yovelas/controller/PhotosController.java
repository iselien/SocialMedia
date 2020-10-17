package com.yovelas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.yovelas.AppConfig;
import com.yovelas.entity.*;
import com.yovelas.service.MusicService;
import com.yovelas.service.PhotoThumbnailService;
import com.yovelas.service.PhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.result.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.bytebuddy.dynamic.ClassFileLocator.ForClassLoader.read;

@RestController
@RequestMapping("/photo")
@CrossOrigin
public class PhotosController {

    private static final Logger log = LoggerFactory.getLogger(PhotosController.class);

    @Autowired
    PhotosService photosService;

    @Autowired
    PhotoThumbnailService photoThumbnailService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AppConfig appConfig;

    @CrossOrigin(allowCredentials = "true")
    @RequestMapping("/retrieve")
    String retrieval(HttpServletResponse resp) throws IOException {
        log.info("Ready to start retrieve");
        ValueOperations valueOperations = redisTemplate.opsForValue();

        if (valueOperations.get("progress") != null) {
            JSONObject progressJson = JSONObject.parseObject(valueOperations.get("progress").toString());
            Progress progress = (Progress) JSON.toJavaObject(progressJson, Progress.class);
            if (progress.getTotal() != progress.getCurrent()) {
                return "{\"status\":\"Running\"}";
            }
        }

        photosService.retrieval();
        log.info("Retrieve finish");
        return "{\"status\":\"DONE\"}";
    }

    @CrossOrigin(allowCredentials = "true")
    @RequestMapping("/progress")
    String progress() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (valueOperations.get("progress") != null) {
            return valueOperations.get("progress").toString();
        }
        return "{\"status\":\"Running or not\"}";
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/showall")
    List showall(ServletResponse resp) throws IOException {
        return photosService.selecrtPhoto();
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/upload")
    JsonResult upload(HttpServletRequest req, HttpServletResponse resp) throws IOException, NoSuchAlgorithmException {
        JsonResult jsonResult = new JsonResult();
        if(req.getSession().getAttribute("user") == null){
                return jsonResult.setMassage("Not logged in").setStatus(1);
        }
        long currentTime = System.currentTimeMillis();
        File file = new File(appConfig.getDataDirectory()+"/photos/"+currentTime);

        BufferedInputStream bin = new BufferedInputStream(req.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        int data ;
        while ((data=bin.read()) != -1)
            bos.write(data);
        bos.close();
        bin.close();

        BufferedInputStream base64fin = new BufferedInputStream(new FileInputStream(file));
        byte[] temp  = new byte[base64fin.available()];
        base64fin.read(temp);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(Base64.encodeBase64(temp));
        BigInteger bi = new BigInteger(1, md5.digest());
        String md5value = bi.toString(16).toUpperCase();
        long l = System.currentTimeMillis() - currentTime;
        System.out.println(l);
        HashMap<String, Object> map = new HashMap<>();
        map.put("MD5",md5value);
        map.put("name",file.getName());
        map.put("time",System.currentTimeMillis()-currentTime);
        return jsonResult.setMassage("success").setStatus(0).setData(map);
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/record")
    JsonResult record(ServletRequest req,@RequestBody List<PhotoRecord> photoRecord) throws IOException {
        for(PhotoRecord p : photoRecord){
            System.out.println(p);
            if(p.isError()){
                File file = new File(appConfig.getDataDirectory()+"/photos/"+p.getRecordName());
                file.delete();
                System.out.println(file.exists());
            }
        }
        photosService.retrieval();
        return new JsonResult().setStatus(0).setMassage("success");
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/fetchmatedata")
    JsonResult fetchMatedata(@RequestBody List<PhotoRecord> photoRecords) throws IOException {
        photosService.retrieval();
        ArrayList<Photo> list = new ArrayList<>();
        for (PhotoRecord p :photoRecords){
            System.out.println(p);
            if(!p.isError()){
                list.add(photosService.selectPhotoByFileName(p.getRecordName()));
            }
        }

       return new JsonResult().setStatus(0).setMassage("success").setData(list);
    }

    @CrossOrigin
    @RequestMapping("/test")
    String test(ServletRequest req) throws IOException {
        System.out.println("test method");
//        String fileMd5 = DigestUtils.md5DigestAsHex(new FileInputStream("/Users/yovelas/Pictures/TheGirlReally.jpg"));
//        System.out.println(fileMd5);
        System.out.println(photosService.selectPhotoByFileName("1584664875558"));
        System.out.println(photosService.selecrtPhoto());

//        ServletInputStream inputStream = req.getInputStream();
//        System.out.println(inputStream);
//        OutputStream outputStream = new FileOutputStream(new File("/Users/yovelas/Desktop/aaa.jpg"));
//        byte[] bytes = new byte[8];
//
//        while (inputStream.read(bytes) != -1) {
//            outputStream.write(bytes, 0, bytes.length);
//        }
//        outputStream.close();
        return "aaa";
    }
}
