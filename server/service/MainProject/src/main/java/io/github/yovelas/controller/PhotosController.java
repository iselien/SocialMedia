package io.github.yovelas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.yovelas.config.AppConfig;
import io.github.yovelas.entity.JsonResult;
import io.github.yovelas.entity.Progress;
import io.github.yovelas.service.PhotosService;
import io.github.yovelas.entity.Photo;
import io.github.yovelas.entity.PhotoRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/photo")
@CrossOrigin
public class PhotosController {

    private static final Logger log = LoggerFactory.getLogger(PhotosController.class);

    @Autowired
    PhotosService photosService;

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
    @PostMapping("/upload1")
    JsonResult upload(HttpServletRequest req, HttpServletResponse resp,MultipartResolver file) throws IOException, NoSuchAlgorithmException {
        JsonResult jsonResult = new JsonResult();
        System.out.println(file);
        return jsonResult.setMessage("success").setStatus(0);
    }


    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/upload")
    JsonResult upload(HttpServletRequest req, HttpServletResponse resp) throws IOException, NoSuchAlgorithmException {
        JsonResult jsonResult = new JsonResult();
        if(req.getSession().getAttribute("user") == null){
                return jsonResult.setMessage("Not logged in").setStatus(1);
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
        return jsonResult.setMessage("success").setStatus(0).setData(map);
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
            }else{
                Photo photo = new Photo();
                photo.setFileName(p.getRecordName());
                photo.setFileOriginName(p.getFileName());
                photosService.insertPhoto(photo);
            }
        }
        photosService.generateMetadata();
        return new JsonResult().setStatus(0).setMessage("success");
    }

    @CrossOrigin(allowCredentials = "true")
    @RequestMapping("/thumbnail/{size}/{id}")
    JsonResult thumbnail(ServletRequest req, ServletResponse resp,@PathVariable String size, @PathVariable int id) throws IOException {
        ServletOutputStream os = resp.getOutputStream();
        File file = photosService.thumbnail(size,id);
        if(file == null){ return new JsonResult().setStatus(1).setMessage("photo is not exist");}
        FileInputStream in = new FileInputStream(file);
        byte[] data = new byte[in.available()];
        in.read(data);
        os.write(data);
        os.flush();
        return null;
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

       return new JsonResult().setStatus(0).setMessage("success").setData(list);
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/getidbyname")
    JsonResult getPhotoIdByName(@RequestBody String name) throws IOException {
        Photo a = photosService.selectPhotoByFileName(name);
        System.out.println("name:"+name+":"+a);
        return new JsonResult().setStatus(0).setMessage("success").setData(a);
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
