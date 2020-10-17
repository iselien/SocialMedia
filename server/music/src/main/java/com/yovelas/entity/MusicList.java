package com.yovelas.entity;

public class MusicList {
    private int musicListId;
    private String musicListName;
    private String musicListUser;
    private String musicListDescript;
    private int musicListPlayNumber;
    private String musicListCreateTime;

    public MusicList(int musicListId, String musicListName, String musicListUser, String musicListDescript, int musicListPlayNumber, String musicListCreateTime) {
        this.musicListId = musicListId;
        this.musicListName = musicListName;
        this.musicListUser = musicListUser;
        this.musicListDescript = musicListDescript;
        this.musicListPlayNumber = musicListPlayNumber;
        this.musicListCreateTime = musicListCreateTime;
    }

    public MusicList() {
    }

    public int getMusicListId() {
        return musicListId;
    }

    public void setMusicListId(int musicListId) {
        this.musicListId = musicListId;
    }

    public String getMusicListName() {
        return musicListName;
    }

    public void setMusicListName(String musicListName) {
        this.musicListName = musicListName;
    }

    public String getMusicListUser() {
        return musicListUser;
    }

    public void setMusicListUser(String musicListUser) {
        this.musicListUser = musicListUser;
    }

    public String getMusicListDescript() {
        return musicListDescript;
    }

    public void setMusicListDescript(String musicListDescript) {
        this.musicListDescript = musicListDescript;
    }

    public int getMusicListPlayNumber() {
        return musicListPlayNumber;
    }

    public void setMusicListPlayNumber(int musicListPlayNumber) {
        this.musicListPlayNumber = musicListPlayNumber;
    }

    public String getMusicListCreateTime() {
        return musicListCreateTime;
    }

    public void setMusicListCreateTime(String musicListCreateTime) {
        this.musicListCreateTime = musicListCreateTime;
    }

    @Override
    public String toString() {
        return "MusicList{" +
                "musicListId=" + musicListId +
                ", musicListName='" + musicListName + '\'' +
                ", musicListUser='" + musicListUser + '\'' +
                ", musicListDescript='" + musicListDescript + '\'' +
                ", musicListPlayNumber=" + musicListPlayNumber +
                ", musicListCreateTime='" + musicListCreateTime + '\'' +
                '}';
    }
}
