package io.github.yovelas.service;

import io.github.yovelas.dao.ArticleDao;
import io.github.yovelas.dao.UserDao;
import io.github.yovelas.entity.Article;
import io.github.yovelas.entity.ArticlePhotoList;
import io.github.yovelas.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    public List<Article> selectAllArticle() {
        List<Article> articles = articleDao.selectAllArticle();
        for(int i = 0;i<articles.size();i++){
            articles.get(i).setArticlePhotoList(articleDao.selectArticlePhotoListByArticleId(articles.get(i).getId()));
        }
        return articles;
    }

    public List<Article> selectArticleByUserId(int userId) {
        List<Article> articles = articleDao.selectArticleByUserId(userId);
        for(int i = 0;i<articles.size();i++){
            articles.get(i).setArticlePhotoList(articleDao.selectArticlePhotoListByArticleId(articles.get(i).getId()));
        }
        return articles;
    }

    public List<ArticlePhotoList> selectArticlePhotoListByArticleId(int articleId) {
        return articleDao.selectArticlePhotoListByArticleId(articleId);
    }

    public int insertArticle(Article article) {
        return articleDao.insertArticle(article);
    }
}
