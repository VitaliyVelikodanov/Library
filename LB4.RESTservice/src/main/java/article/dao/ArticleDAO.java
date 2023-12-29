package article.dao;

import article.entity.Article;
import article.entity.ArticleTag;
import article.entity.Comment;
import article.entity.Grade;

import java.util.Collection;

public interface ArticleDAO {
    public Collection<Article> listArticles();
    public int addArticle(Article item);
    public int addGrade(Grade grade);
    public int addComment(Comment comment);
    public int addArticleTag(ArticleTag articleTag);
    public Collection<Article> findByTag(String pattern);

}