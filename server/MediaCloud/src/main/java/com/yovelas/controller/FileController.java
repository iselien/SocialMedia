package com.yovelas.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
//        System.out.println("file:"+"/Users/yovelas/Documents/temp/"+file);
        System.out.println("file:"+"/Users/yovelas/Pictures/girl"+file);
        Path filestorage = Paths.get("filestorage").resolve("/Users/yovelas/Pictures/girl/"+file);
        UrlResource urlResource = new UrlResource(filestorage.toUri());
        ResponseEntity<UrlResource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file+"").body(urlResource);
        return body;
    }

    //    @CrossOrigin(allowCredentials = "true")
    @CrossOrigin
    @GetMapping("/read")
    ResponseEntity readFile(HttpServletResponse resp, @Param("file") String file) throws IOException {
        System.out.println(111);


//        FileInputStream fis = new FileInputStream("/Users/yovelas/Desktop/2020Ui-pic1.jpg");
        File file1 = new File("/Users/yovelas/Pictures/girl");
        System.out.println(file1.listFiles());
        for(File f: file1.listFiles()){
            System.out.println(f);
            System.out.println(f.isFile());
            System.out.println(f.canRead());
            System.out.println(f.toURI());
            System.out.println(f.toURI());
            if(f.isFile() && f.canRead()){
                System.out.println(55555555);
                System.out.println(new FileInputStream(f));
                System.out.println(ImageIO.createImageInputStream(f));

//                System.out.println(ImageIO.read(ImageIO.createImageInputStream(f)));
            }
//            System.out.println(ImageIO.read(new FileInputStream(file1)).getWidth());
        }
        return null;
    }


}
