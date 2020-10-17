package com.yovelas.controller;

import com.yovelas.entity.Album;
import com.yovelas.entity.JsonResult;
import com.yovelas.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {

    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    AlbumService albumService;

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/insert", consumes = "application/json")
    Object diaryinsert(HttpServletRequest req, HttpServletResponse resp,@RequestBody Album diary) {
        albumService.insertDiary(diary);
        return new JsonResult().setMassage("succuess").setStatus(0);
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/showall", consumes = "application/json")
    Object diaryselect(HttpServletRequest req, HttpServletResponse resp) {
       return new JsonResult().setStatus(0).setMassage("success").setData(albumService.selectDiary());
    }

}
