package com.yovelas.mapper;

import java.util.List;

import com.yovelas.entity.MusicList;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MusicListMapper {
	@Select("SELECT * FROM music_list")
	List<MusicList> getMusicList();

	@Select("SELECT * FROM music_list where musicList_uid=#{uid}")
	@Results({
			@Result(property = "musicListId",column = "musiclist_id"),
			@Result(property = "musicListName",column = "musiclist_name"),
			@Result(property = "musicListUser",column = "musiclist_uid"),
			@Result(property = "musicListDescript",column = "musiclist_descript"),
			@Result(property = "musicListPlayNumber",column = "musiclist_playNumber"),
			@Result(property = "musicListCreateTime",column = "musiclist_createTime"),
	})
	List<MusicList> getUserMusicList(@Param("uid") int uid);

	@Select("SELECT * FROM music_list where musiclist_id=#{musicid} and musicList_uid=#{uid}")
	MusicList loadMusicList(@Param("musicid") int musicid,@Param("uid") int uid);

//	@Select("SELECT * FROM music_list where musiclist_id=#{musicid} and musicList_uid=#{uid}")
//	MusicList loadMusicList(@Param("musicid") int musicid,@Param("uid") int uid);
}
