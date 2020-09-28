package io.github.yovelas.mapper;

import io.github.yovelas.entity.Article;
import io.github.yovelas.entity.ArticlePhotoList;
import io.github.yovelas.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
	@Select("SELECT * FROM article order by create_time desc")
	@Results({
			@Result(property = "id",column = "id"),
			@Result(property = "userId",column = "user_id"),
			@Result(property = "content",column = "content"),
			@Result(property = "tags",column = "tags"),
			@Result(property = "createTime",column = "create_time"),
	})
	List<Article> selectAllArticle();

	@Select("SELECT * FROM article where user_id=#{userId} order by create_time desc")
	@Results({
			@Result(property = "id",column = "id"),
			@Result(property = "userId",column = "user_id"),
			@Result(property = "content",column = "content"),
			@Result(property = "tags",column = "tags"),
			@Result(property = "createTime",column = "create_time"),
	})
	List<Article> selectArticleByUserId(@Param("userId") int userId);

	@Select("SELECT * FROM article_photo_list where article_id=#{articleId} order by create_time desc")
	@Results({
			@Result(property = "id",column = "id"),
			@Result(property = "articleId",column = "article_id"),
			@Result(property = "photoId",column = "photo_id"),
			@Result(property = "createTime",column = "create_time")
	})
	List<ArticlePhotoList> selectArticlePhotoListByArticleId(@Param("articleId") int articleId);

	@Insert("INSERT INTO article VALUES(default,#{userId},#{content},#{tags},now())")
	int insertArticle(
            @Param("userId") int userId,
            @Param("content") String content,
            @Param("tags") String tags);
}