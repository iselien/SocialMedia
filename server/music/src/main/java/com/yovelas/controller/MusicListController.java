package com.yovelas.controller;

import com.yovelas.dao.MusicDao;
import com.yovelas.dao.MusicListDao;
import com.yovelas.entity.JsonResult;
import com.yovelas.service.MusicListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/musiclist")
@CrossOrigin
public class MusicListController {
// 根据用户id获取音乐列表
// 根据列表id获取列表项

    @Autowired
    private MusicListDao musicListDao;

    @Autowired
    private MusicDao musicDao;

    @Autowired
    private MusicListService musicListService;

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "/getusermusiclist", consumes = "application/json")
    JsonResult getUserMusicList(@RequestBody Map jsonMap) {
        JsonResult jsonResult = new JsonResult();
        System.out.println("/musiclist/getusermusiclist"+jsonMap);
        if(musicListService.getUserMusicList(jsonMap) != null){
            jsonResult.setStatus(200);
            jsonResult.setMassage("SUCCESS");
            jsonResult.setData(musicListService.getUserMusicList(jsonMap));
        }
        return jsonResult;
    }


    @PostMapping(value = "/loadmusiclist", consumes = "application/json")
    String loadMusicList(@RequestBody Map jsonMap) {
        System.out.println("hello music!");
        System.out.println(jsonMap);
        System.out.println(musicListDao.loadMusicList(jsonMap));
        return null;
    }

//    @CrossOrigin(allowCredentials = "true")
//    @PostMapping(value = "/getmusicbylist", consumes = "application/json")
//    JsonResult getMusicByList(@RequestBody Map jsonMap) {
//        JsonResult jsonResult = new JsonResult();
//        System.out.println(jsonMap);
//        System.out.println(musicDao.getMusicByList(jsonMap));
//        jsonResult.setData(musicDao.getMusicByList(jsonMap));
//        jsonResult.setStatus(200);
//        return jsonResult;
//    }


}
