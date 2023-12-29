package ua.nure.book.server.dao;

import ua.nure.book.entity.Author;
import ua.nure.book.entity.Book;
import ua.nure.book.entity.Genre;
import ua.nure.dbtable.DBTable;
import ua.nure.dbtable.DBTableFactory;
import ua.nure.dbtable.Filter;

import java.util.*;

public class BookDAOInMemoryImpl implements BookDAO {

    DBTable<Book> Books = DBTableFactory.instance();

    private static ua.nure.book.server.dao.BookDAOInMemoryImpl instance;

    private static int UniqId = 1;

    private static int GetUniqId() {
        return UniqId++;
    }

    private BookDAOInMemoryImpl() {
        initBooks();
    }

    public static synchronized ua.nure.book.server.dao.BookDAOInMemoryImpl instance() {
        if (instance == null) {
            instance = new ua.nure.book.server.dao.BookDAOInMemoryImpl();
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
        genre.setBookId(book.getId());
        this.Books.insert(book);

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
