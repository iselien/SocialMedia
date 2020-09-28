package io.github.yovelas.controller;

import io.github.yovelas.entity.*;
import io.github.yovelas.service.AlbumService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {

    /**
     * 专辑API
     * ``:GET           查找所有专辑
     * ``:POST          插入一张专辑
     * `/{id}`:GET      根据用户ID查找该用户的所有专辑
     * `/tag`:GET       根据相似标签名查找标签
     * `/tag`:POST      插入一个标签
     * `/tag`:PUT       插入一条标签记录
     * `/photo`:GET     获取专辑图片记录
     * `/photo`:POST    插入专辑图片记录
     */

    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);
    @Autowired
    AlbumService albumService;

    // 查找所有专辑
    @CrossOrigin(allowCredentials = "true")
    @RequestMapping
    Object allAlbums() {
       return new JsonResult().setStatus(0).setMessage("success").setData(albumService.selectAllAblums());
    }

    // 插入专辑
    @CrossOrigin(allowCredentials = "true")
    @PostMapping
    Object insertOneAlbum(@RequestBody Album album) {
        albumService.insertAlbum(album);
        return new JsonResult().setMessage("succuess").setStatus(0);
    }

    // 根据用户id查找专辑
    @CrossOrigin(allowCredentials = "true")
    @RequestMapping(path = "/{id}")
    Object someOneAlbum(@PathVariable int id) {
        return new JsonResult().setStatus(0).setMessage("success").setData(albumService.selectAlbumsByUserId(id));
    }

    // 根据相似的标签名查找标签
    @CrossOrigin(allowCredentials = "true")
    @GetMapping(path = "/tag")
    Object PhotoTags(@Param("likelyName") String likelyName) {
        System.out.println("aaaaa:"+likelyName);
        return new JsonResult().setStatus(0).setMessage("success").setData(albumService.selectTagsByLikelyTag(likelyName));
    }

    // 插入标签
    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/tag")
    Object PhotoTags(@RequestBody PhotoTag photoTag) {
        System.out.println("bbbbb:"+photoTag);
        return new JsonResult().setStatus(0).setMessage("success").setData(albumService.insertPhotoTag(photoTag));
    }

    // 插入专辑标签记录
    @CrossOrigin(allowCredentials = "true")
    @PutMapping(path = "/tag", consumes = "application/json")
    Object insertAlbums(@RequestBody PhotoAlbumTagRecord photoAlbumTagRecord) {
        albumService.insertAlbumTagRecord(photoAlbumTagRecord);
        return new JsonResult().setMessage("succuess").setStatus(0);
    }

    // 插入专辑图片记录
    @CrossOrigin(allowCredentials = "true")
    @GetMapping(path = "/photo", consumes = "application/json")
    Object oneAlbums(@RequestBody PhotoAlbumPhotoList photoAlbumPhotoList) {
        albumService.insertAlbumPhotoList(photoAlbumPhotoList);
        return new JsonResult().setMessage("succuess").setStatus(0);
    }

    // 插入专辑图片记录
    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "/photo", consumes = "application/json")
    Object insertOneAlbums(@RequestBody PhotoAlbumPhotoList photoAlbumPhotoList) {
        albumService.insertAlbumPhotoList(photoAlbumPhotoList);
        return new JsonResult().setMessage("succuess").setStatus(0);
    }


}
