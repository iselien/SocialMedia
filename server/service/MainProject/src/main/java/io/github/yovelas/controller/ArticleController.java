package io.github.yovelas.controller;

import io.github.yovelas.entity.Album;
import io.github.yovelas.entity.Article;
import io.github.yovelas.entity.JsonResult;
import io.github.yovelas.service.AlbumService;
import io.github.yovelas.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin(allowCredentials = "true")
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    // ``:GET           所有文章，按创建日期排序
    // `/{userId}`:GET  查询指定用户的所有文章，按创建日期排序
    // `/{sort}`:GET    根据指定的排序方式查询
    // `/{sort}/{userId}`:GET    根据指定的排序方式及指定用户查询

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(path = "")
    Object selectAllArticle(HttpServletRequest req, HttpServletResponse resp) {
        // 查询所有文章，按创建日期排序
        List<Article> articles = articleService.selectAllArticle();
        return new JsonResult().setStatus(0).setMessage("success").setData(articleService.selectAllArticle());
    }

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(path = "/{sort}/{userId}")
    Object selectAllArticle(@PathVariable("sort") String sort, @PathVariable("userId") int id) {
        System.out.println("sort:"+sort+"userId:"+id);
        // 按照指定排序方式，及指定用户查询
        List<Article> articles = articleService.selectArticleByUserId(id);

        return new JsonResult().setStatus(0).setMessage("success").setData(articleService.selectArticleByUserId(id));
    }

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(path = "/{userId:^[0-9]*$}")
    Object selectAllArticle(@PathVariable("userId") int id) {
        // 查询指定用户的所有文章，按创建日期排序
        System.out.println("userId:"+id);
        return new JsonResult().setStatus(0).setMessage("success").setData(articleService.selectArticleByUserId(id));
    }

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(path = "/{sort:[a-z-]+}")
    Object selectAllArticle(@PathVariable("sort") String sort) {
        // 按照指定排序方式查询所有用户文章
        System.out.println("sort:"+sort);
        return new JsonResult().setStatus(0).setMessage("success").setData(articleService.selectAllArticle());
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(path = "")
    Object insertArticle(HttpServletRequest req, HttpServletResponse resp, @RequestBody Article article) {
        System.out.println("article insert");
        System.out.println(article);
        articleService.insertArticle(article);
        return new JsonResult().setStatus(0).setMessage("success").setData(null);
    }
}
