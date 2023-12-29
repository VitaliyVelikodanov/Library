package article.dao;



import article.dao.dbtable.DBTable;
import article.dao.dbtable.DBTableFactory;
import article.dao.dbtable.Filter;
import article.entity.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ArticleDAOInMemoryImpl implements ArticleDAO {
    DBTable<Article> articles = DBTableFactory.instance();

    private static ArticleDAOInMemoryImpl instance;

    private static int UniqId = 1;

    private static int GetUniqId() {
        return UniqId++;
    }

    private ArticleDAOInMemoryImpl() {
        initArticles();
    }

    public static synchronized ArticleDAOInMemoryImpl instance() {
        if (instance == null) {
            instance = new ArticleDAOInMemoryImpl();
        }
        return instance;
    }

    private Article newArticle(int articleId, String title, String body, Author author, List<ArticleTag> articleTags, List<Comment> comments, List<Grade> grades) {
        Article article = new Article();
        article.setId(articleId);
        article.setTitle(title);
        article.setBody(body);
        article.setAuthor(author);
        article.getArticleTag().addAll(articleTags);
        article.getComment().addAll(comments);
        article.getGrade().addAll(grades);
        return article;
    }

    private ArticleTag newArticleTag(String name, int articleId) {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setName(name);
        articleTag.setArticleId(articleId);

        return articleTag;
    }

    private Comment newComment(String body, int articleId) {
        Comment comment = new Comment();
        comment.setBody(body);
        comment.setArticleId(articleId);

        return comment;
    }

    private Grade newGrade(int value, int articleId) {
        Grade grade = new Grade();
        grade.setValue(value);
        grade.setArticleId(articleId);

        return grade;
    }

    private Author newAuthor(String firstName, String lastName, int age) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setAge(age);

        return author;
    }
    private void initArticles() {
        Article[] articles = new Article[] {
                newArticle(1,
                        "Почему ASP, а не Java",
                        "Body1",
                        newAuthor("Джон", "Смит", 18),
                        Arrays.asList(new ArticleTag[] { newArticleTag("Real", 1) }),
                        Arrays.asList(new Comment[] { newComment("BEAUTIFUL1", 1) }),
                        Arrays.asList(new Grade[] { newGrade(5, 1)})
                        ),
                newArticle(2,
                        "Почему не Java, а ASP",
                        "Body2",
                        newAuthor("Джон", "Смит", 19),
                        Arrays.asList(new ArticleTag[] { newArticleTag("Real Tomorrow", 2) }),
                        Arrays.asList(new Comment[] { newComment("BEAUTIFUL2", 2) }),
                        Arrays.asList(new Grade[] { newGrade(4, 2)})
                ),
                newArticle(3,
                        "Почему не Java",
                        "Body3",
                        newAuthor("Джон", "Смит", 20),
                        Arrays.asList(new ArticleTag[] { newArticleTag("Fantasy", 3) }),
                        Arrays.asList(new Comment[] { newComment("BEAUTIFUL3", 3) }),
                        Arrays.asList(new Grade[] { newGrade(3, 3)})
                ),
                newArticle(4,
                        "Почему ASP",
                        "Body4",
                        newAuthor("Джон", "Смит", 21),
                        Arrays.asList(new ArticleTag[] { newArticleTag("Real today", 4) }),
                        Arrays.asList(new Comment[] { newComment("BEAUTIFUL4", 4) }),
                        Arrays.asList(new Grade[] { newGrade(4, 4)})
                ),
        };
        for (int i = 0; i < articles.length; i++) {
            addArticle(articles[i]);
//            List<ArticleTag> articleTags = articles[i].getArticleTag();
//            for (int ag = 0; ag < articleTags.size(); ag++) {
//                addArticleTag(articleTags.get(ag));
//            }
//
//            List<Grade> grades = articles[i].getGrade();
//            for (int ag = 0; ag < grades.size(); ag++) {
//                addGrade(grades.get(ag));
//            }
//
//            List<Comment> comments = articles[i].getComment();
//            for (int ag = 0; ag < comments.size(); ag++) {
//                addComment(comments.get(ag));
//            }
//
//            Author author = articles[i].getAuthor();
//            if(author != null)
//            {
//                addAuthor(articles[i].getAuthor());
//            }
        }
    }

    @Override
    public Collection<Article> listArticles() {
        var a = this.articles.selectAll();
        return a;
    }

    @Override
    public int addArticle(Article article) {
        int id = GetUniqId();
        article.setId(id);

        var author = article.getAuthor();
        author.setId(GetUniqId());

        var articleTags = article.getArticleTag();
        for (int i = 0; i < articleTags.size(); i++) {
            var articleTag = articleTags.get(i);
            articleTag.setArticleId(article.getId());
            articleTag.setId(GetUniqId());
        }

        var comments = article.getComment();
        for (int i = 0; i < comments.size(); i++) {
            var comment = comments.get(i);
            comment.setArticleId(article.getId());

            comment.setId(GetUniqId());
        }

        var grades = article.getGrade();
        for (int i = 0; i < grades.size(); i++) {
            var grade = grades.get(i);
            grade.setArticleId(article.getId());

            grade.setId(GetUniqId());
        }

        this.articles.insert(article);

//        List<ArticleTag> articleTags1 = article.getArticleTag();
//        for (int ag = 0; ag < articleTags1.size(); ag++) {
//            var articleTag = articleTags1.get(ag);
//            articleTag.setId(id);
//            var agId = articleTags.insert(articleTag);
//            try {
//                articleTags.update(id, articleTag);
//            } catch (SQLException e) {
//                // inmemory
//            }
//        }
//
//        List<Grade> grades1 = article.getGrade();
//        for (int ag = 0; ag < grades1.size(); ag++) {
//            var grade = grades1.get(ag);
//            grade.setId(id);
//            var agId = grades.insert(grade);
//            try {
//                grades.update(id, grade);
//            } catch (SQLException e) {
//                // inmemory
//            }
//        }
//
//        List<Comment> comments1 = article.getComment();
//        for (int ag = 0; ag < comments1.size(); ag++) {
//            var comment = comments1.get(ag);
//            comment.setId(id);
//            var agId = comments.insert(comment);
//            try {
//                comments.update(id, comment);
//            } catch (SQLException e) {
//                // inmemory
//            }
//        }
//
//        Author author = article.getAuthor();
//        if(author != null)
//        {
//            var agId = authors.insert(author);
//            try {
//                authors.update(id, author);
//            } catch (SQLException e) {
//                // inmemory
//            }
//        }

        return id;
    }

    @Override
    public int addGrade(Grade grade) {
        var article = this.articles.filter(grade.getArticleId(), articleFilterById).iterator().next();

        article.getGrade().sort(byGradeId);
        var size = article.getGrade().size();
        var lastGrade = article.getGrade().get(size - 1);
        grade.setId(lastGrade.getId() + 1);
        article.getGrade().add(grade);

        return grade.getId();
    }

    @Override
    public int addArticleTag(ArticleTag articleTag) {
        var article = getArticleById(articleTag.getArticleId());

        article.getArticleTag().sort(byArticleTagId);
        var size = article.getArticleTag().size();
        var lastArticleTag = article.getArticleTag().get(size - 1);
        articleTag.setId(lastArticleTag.getId() + 1);
        article.getArticleTag().add(articleTag);

        return articleTag.getId();
    }


    @Override
    public int addComment(Comment comment) {
        var article = getArticleById(comment.getArticleId());

        article.getComment().sort(byCommentId);
        var size = article.getComment().size();
        var lastComment = article.getComment().get(size - 1);
        comment.setId(lastComment.getId() + 1);
        article.getComment().add(comment);

        return comment.getId();
    }

    @Override
    public Collection<Article> findByTag(String pattern) {
        return articles.filter(pattern, tagFilter);
    }

    Filter<Article> tagFilter = new Filter<>() {
        @Override
        public boolean accept(Article item, Object pattern) {
            String p = (String) pattern;
            for (int i = 0; i < item.getArticleTag().size(); i++) {
                ArticleTag articleTag = item.getArticleTag().get(i);
                if(articleTag.getName().equals(p)) {
                    return true;
                }
            }

            return false;
        }
    };

    private Article getArticleById(int id) {
        return this.articles.filter(id, articleFilterById).iterator().next();
    }

    Comparator<Article> byArticleId = new Comparator<Article>() {
        @Override
        public int compare(Article o1, Article o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    };

    Comparator<Grade> byGradeId = new Comparator<Grade>() {
        @Override
        public int compare(Grade o1, Grade o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    };

    Comparator<Comment> byCommentId = new Comparator<Comment>() {
        @Override
        public int compare(Comment o1, Comment o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    };

    Comparator<Author> byAuthorId = new Comparator<Author>() {
        @Override
        public int compare(Author o1, Author o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    };
    Comparator<ArticleTag> byArticleTagId = new Comparator<ArticleTag>() {
        @Override
        public int compare(ArticleTag o1, ArticleTag o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    };


    Filter<Article> articleFilterById = new Filter<>() {
        @Override
        public boolean accept(Article item, Object pattern) {
            int p = (int) pattern;
            if(item.getId() == p) {
                return true;
            }

            return false;
        }
    };

}
