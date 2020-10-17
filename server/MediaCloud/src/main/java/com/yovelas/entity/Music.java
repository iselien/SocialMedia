package com.yovelas.entity;

public class Music {
    private int musicId;
    private String musicType;
    private String musicName;
    private String musicPath;
    private String musicDescript;
    private String musicAuthor;
    private String musicImage;
    private String musicVideo;
    private String musicAlbum;
    private String musicSubtitlePath;
    private int musicFlag;
    private String musicUpdateTime;
    private String musicUpdateUser;

    public Music(int musicId, String musicType, String musicName, String musicPath, String musicDescript, String musicAuthor, String musicImage, String musicVideo, String musicAlbum, String musicSubtitlePath, int musicFlag, String musicUpdateTime, String musicUpdateUser) {
        this.musicId = musicId;
        this.musicType = musicType;
        this.musicName = musicName;
        this.musicPath = musicPath;
        this.musicDescript = musicDescript;
        this.musicAuthor = musicAuthor;
        this.musicImage = musicImage;
        this.musicVideo = musicVideo;
        this.musicAlbum = musicAlbum;
        this.musicSubtitlePath = musicSubtitlePath;
        this.musicFlag = musicFlag;
        this.musicUpdateTime = musicUpdateTime;
        this.musicUpdateUser = musicUpdateUser;
    }

    public Music() {
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getMusicDescript() {
        return musicDescript;
    }

    public void setMusicDescript(String musicDescript) {
        this.musicDescript = musicDescript;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getMusicImage() {
        return musicImage;
    }

    public void setMusicImage(String musicImage) {
        this.musicImage = musicImage;
    }

    public String getMusicVideo() {
        return musicVideo;
    }

    public void setMusicVideo(String musicVideo) {
        this.musicVideo = musicVideo;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public String getMusicSubtitlePath() {
        return musicSubtitlePath;
    }

    public void setMusicSubtitlePath(String musicSubtitlePath) {
        this.musicSubtitlePath = musicSubtitlePath;
    }

    public int getMusicFlag() {
        return musicFlag;
    }

    public void setMusicFlag(int musicFlag) {
        this.musicFlag = musicFlag;
    }

    public String getMusicUpdateTime() {
        return musicUpdateTime;
    }

    public void setMusicUpdateTime(String musicUpdateTime) {
        this.musicUpdateTime = musicUpdateTime;
    }

    public String getMusicUpdateUser() {
        return musicUpdateUser;
    }

    public void setMusicUpdateUser(String musicUpdateUser) {
        this.musicUpdateUser = musicUpdateUser;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", musicType='" + musicType + '\'' +
                ", musicName='" + musicName + '\'' +
                ", musicPath='" + musicPath + '\'' +
                ", musicDescript='" + musicDescript + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                ", musicImage='" + musicImage + '\'' +
                ", musicVideo='" + musicVideo + '\'' +
                ", musicAlbum='" + musicAlbum + '\'' +
                ", musicSubtitlePath='" + musicSubtitlePath + '\'' +
                ", musicFlag=" + musicFlag +
                ", musicUpdateTime='" + musicUpdateTime + '\'' +
                ", musicUpdateUser='" + musicUpdateUser + '\'' +
                '}';
    }
}
