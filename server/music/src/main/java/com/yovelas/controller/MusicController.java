package com.yovelas.controller;

import com.yovelas.dao.MusicDao;
import com.yovelas.entity.JsonResult;
import com.yovelas.service.MusicService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/music")
public class MusicController {
    // 上传音乐
    // 下载音乐
    // 修改音乐
    // 删除音乐

    @Autowired
    MusicDao musicDao;

    @Autowired
    MusicService musicService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {

        String name = file.getName();
        String filename = file.getResource().getFilename();

        return "hello Worlds!!";
    }

    @RequestMapping("/download")
    ResponseEntity downloadMusic(HttpServletResponse resp, @Param("file") String file) throws IOException {
        System.out.println("download");
//        System.out.println(jsonMap);
//        File file = new File((String) jsonMap.get("path"));
//        System.out.println("file:"+file.getName());
//        FileInputStream fileInputStream = new FileInputStream(file);
//        resp.setHeader("Content-Disposition","attachment; filename=Attention");
//        ServletOutputStream outputStream = resp.getOutputStream();
//        byte[] bytes = new byte[1024];
//        while (fileInputStream.read(bytes) != -1){
//           outputStream.write(bytes);
//        }
//        resp.flushBuffer();

        System.out.println("file:"+file);
//        Path filestorage = Paths.get("filestorage").resolve("/Users/yovelas/Desktop/Attention.mp3");
        Path filestorage = Paths.get("filestorage").resolve(file);
        System.out.println("file:"+filestorage);
        UrlResource urlResource = new UrlResource(filestorage.toUri());
        ResponseEntity<UrlResource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename='Attention.mp3'").body(urlResource);

        System.out.println(body);
        return body;
    }

    String updataMusic() {
        return null;
    }

    String deleteMusic() {
        return null;
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "/loadmusicbylist", consumes = "application/json")
    JsonResult getMusicByList(@RequestBody Map jsonMap) {
        JsonResult jsonResult = new JsonResult();
        System.out.println(jsonMap);
        System.out.println(musicService.loadMusicByList(jsonMap));
        jsonResult.setData(musicDao.loadMusicByList(jsonMap));
        jsonResult.setStatus(200);
        return jsonResult;
    }
}
