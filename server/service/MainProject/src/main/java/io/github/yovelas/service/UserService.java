package io.github.yovelas.service;

import io.github.yovelas.dao.AlbumDao;
import io.github.yovelas.dao.UserDao;
import io.github.yovelas.entity.Album;
import io.github.yovelas.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AlbumDao albumDao;

    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public User getUserByUP(User user) {
        return userDao.getUserByUP(user);
    }

    public int insertUser(User user){
        user.setNickname(user.getUsername());
        int i = userDao.insertUser(user);
        User userByName = getUserByName(user.getUsername());

        // 添加默认专辑
        Album album = new Album();
        album.setUserId(userByName.getId());
        album.setAlbumName("default album");
        album.setAlbumDescribe("when user sign up automatic created");
        album.setAlbumTag("tags");
        album.setCentralPhotoId(1);
        albumDao.insertAlbum(album);
        return i;
    }
}
