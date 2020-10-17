package com.yovelas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.Blob;
import com.yovelas.AppConfig;
import com.yovelas.entity.Progress;
import com.yovelas.service.PhotoThumbnailService;
import com.yovelas.service.PhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/thumb")
@Api(tags = "BookController", description = "BookController | 通过书来测试swagger")
@CrossOrigin
public class ThumbnailController {

    private static final Logger log = LoggerFactory.getLogger(ThumbnailController.class);

    @Autowired
    PhotosService photosService;

    @Autowired
    PhotoThumbnailService photoThumbnailService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AppConfig appConfig;

    @ApiOperation(value="创建图书", notes="创建图书")
    @ApiImplicitParam(name = "book", value = "图书详细实体", required = true, dataType = "Book")
    @CrossOrigin(allowCredentials = "true")
    @PostMapping("/fetchthumbnail")
    void thumbnail(ServletRequest req, ServletResponse resp) throws IOException {
        int id =  Integer.valueOf(req.getParameter("id"));
        String size = req.getParameter("size");
        ServletOutputStream os = resp.getOutputStream();
        File file = photoThumbnailService.getThumbnail(id, size);
        FileInputStream in = new FileInputStream(file);
        byte[] data = new byte[in.available()];
        in.read(data);
        os.write(data);
        os.flush();
    }



}
