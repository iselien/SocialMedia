package com.yovelas.mapper;

import com.yovelas.entity.Music;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MusicMapper {

    @Select("select * from music m join music_to_list ml on m.music_id =ml.music_id where ml.user_id =#{user_id} and ml.musiclist_id=#{musiclist_id};")
    @Results({
            @Result(property = "musicId", column = "music_id"),
            @Result(property = "musicType", column = "music_type"),
            @Result(property = "musicName", column = "music_name"),
            @Result(property = "musicPath", column = "music_path"),
            @Result(property = "musicDescript", column = "music_descript"),
			@Result(property = "musicAuthor", column = "music_author"),
			@Result(property = "musicImage", column = "music_image"),
			@Result(property = "musicVideo", column = "music_video"),
			@Result(property = "musicAlbum", column = "music_album"),
			@Result(property = "musicSubtitlePath", column = "music_subtitlePath"),
			@Result(property = "musicFlag", column = "music_flag"),
			@Result(property = "musicUpdateTime", column = "music_updateTime"),
			@Result(property = "musicUpdateUser", column = "music_uploadUser")
	})
	List<Music> loadMusicByList(@Param("user_id") int user_id, @Param("musiclist_id") int musiclist_id);


    @Insert("INSERT INTO music VALUES(#{music_id},#{music_type}, #{music_name}, #{music_path}, #{music_descript}, #{music_author}, #{music_image}, #{music_video}, #{music_album}, #{music_subtitlePath}, #{music_updateTime}, #{music_updateUser})")
    int insertMusic(@Param("music_id") int music_id, @Param("music_type") String music_type, @Param("music_name") String music_name, @Param("music_path") String music_path, @Param("music_descript") String music_descript, @Param("music_author") String music_author, @Param("music_image") String music_image, @Param("music_video") String music_video, @Param("music_album") String music_album, @Param("music_subtitlePath") String music_subtitlePath, @Param("music_updateTime") String music_updateTime, @Param("music_updateUser") String music_updateUse);


}
