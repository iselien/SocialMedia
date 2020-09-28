package io.github.yovelas.dao;

import io.github.yovelas.entity.Article;
import io.github.yovelas.entity.ArticlePhotoList;
import io.github.yovelas.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Article> selectAllArticle() {
        return sqlSession.selectList("io.github.yovelas.mapper.ArticleMapper.selectAllArticle");
    }

    public List<Article> selectArticleByUserId(int userId) {
        return sqlSession.selectList("io.github.yovelas.mapper.ArticleMapper.selectArticleByUserId", userId);
    }

    public List<ArticlePhotoList> selectArticlePhotoListByArticleId(int articleId) {
        return sqlSession.selectList("io.github.yovelas.mapper.ArticleMapper.selectArticlePhotoListByArticleId", articleId);
    }

    public int insertArticle(Article article) {
        return sqlSession.insert("io.github.yovelas.mapper.ArticleMapper.insertArticle",article);
    }

}