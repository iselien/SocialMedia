package com.yovelas.controller;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//@RestController
@Controller
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {

        String name = file.getName();
        String filename = file.getResource().getFilename();
        log.info(name);
        log.info(filename);

        return "hello Worlds!!";
    }

//    @CrossOrigin(allowCredentials = "true")
    @CrossOrigin
    @GetMapping("/download")
    ResponseEntity downloadMusic(HttpServletResponse resp, @Param("file") String file) throws IOException {
        System.out.println("file:"+"/Users/yovelas/Documents/temp/"+file);
        Path filestorage = Paths.get("filestorage").resolve("/Users/yovelas/Documents/temp/"+file);
        UrlResource urlResource = new UrlResource(filestorage.toUri());
        ResponseEntity<UrlResource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file+"").body(urlResource);
        return body;
    }


}
