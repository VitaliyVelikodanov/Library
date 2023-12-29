package book.dao;

import book.dao.dbtable.DBTable;
import book.dao.dbtable.DBTableFactory;
import book.dao.dbtable.Filter;
import book.entity.Author;
import book.entity.Book;
import book.entity.Genre;


import java.util.*;

public class BookDAOInMemoryImpl implements BookDAO {

    DBTable<Book> Books = DBTableFactory.instance();

    private static BookDAOInMemoryImpl instance;

    private static int UniqId = 1;

    private static int GetUniqId() {
        return UniqId++;
    }

    private BookDAOInMemoryImpl() {
        initBooks();
    }

    public static synchronized BookDAOInMemoryImpl instance() {
        if (instance == null) {
            instance = new BookDAOInMemoryImpl();
        }
        return instance;
    }

    private Book newBook(int bookId, String title, String body, Author author, Genre genres) {
        Book book = new Book();
        book.setId(bookId);
        book.setTitle(title);
        book.setBody(body);
        book.setAuthor(author);
        book.setGenre(genres);
        return book;
    }

    private Genre newGenre(String genreName, int bookId) {
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        genre.setBookId(bookId);

        return genre;
    }

    private Author newAuthor(String name, String placeOfBirth, String biography, String majorWorks) {
        Author author = new Author();
        author.setName(name);
        author.setPlaceOfBirth(placeOfBirth);
        author.setBiography(biography);
        author.setMajorWorks(majorWorks);

        return author;
    }

    private void initBooks() {
        Book[] books = new Book[]{
                newBook(1,
                        "title1",
                        "Body1",
                        newAuthor("Oleg", "Kropyvnitskyi", "Biography1", "majorwork1"),
                        newGenre("Horor1", 1)
                ),
                newBook(2,
                        "title2",
                        "Body2",
                        newAuthor("Kto-to", "otkyda-to", "Biography2", "majorwork2"),
                        newGenre("Horor2", 2)
                ),
                newBook(3,
                        "title3",
                        "Body1",
                        newAuthor("Artem", "Kiev", "Biography1", "majorwork3"),
                        newGenre("Horor3", 3)
                ),
                newBook(4,
                        "title4",
                        "Body1",
                        newAuthor("Ivan", "Kapysta", "Biography4", "majorwork4"),
                        newGenre("Horor4", 4)
                ),
        };
        for (int i = 0; i < books.length; i++) {
            addBook(books[i]);
        }
    }

    @Override
    public Collection<Book> listBooks() {
        var a = this.Books.selectAll();
        return a;
    }

    @Override
    public int addBook(Book book) {
        int id = GetUniqId();
        book.setId(id);

        var author = book.getAuthor();
        author.setId(GetUniqId());

        var genre = book.getGenre();
        genre.setId(GetUniqId());

        this.Books.insert(book);
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
    public void removeBook(int bookId) {
        Books.delete(bookId, bookFilterById);
    }

    @Override
    public Book takeBook(int bookId) {
        var book = getBookById(bookId);
        return book;
    }
    @Override
    public void editBook(Book book) {
        removeBook(book.getId());
        Books.insert(book);
    }
    private Book getBookById(int id) {
        return this.Books.filter(id, bookFilterById).iterator().next();
    }

    Comparator<Book> byBookId = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    };


    Filter<Book> bookFilterById = new Filter<>() {
        @Override
        public boolean accept(Book item, Object pattern) {
            int p = (int) pattern;
            if (item.getId() == p) {
                return true;
            }

            return false;
        }
    };
}
