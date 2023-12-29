package ua.nure.parser;

import ua.nure.book.entity.book.Author;
import ua.nure.book.entity.book.Book;
import ua.nure.book.entity.book.Genre;

import javax.xml.namespace.QName;
import javax.xml.stream.events.*;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private static final boolean LOG_ENABLED = false;

    private String current;
    private Book book;
    private Author author;
    private Genre genre;
    private List<Book> books;

    private static void log(Object o) {
        if (LOG_ENABLED) {
            System.out.println(o);
        }
    }

    public void endElement(XMLEvent event) {
        EndElement endElement = event.asEndElement();
        String localName = endElement.getName().getLocalPart();
        if (Const.TAG_BOOK.equals(localName)) {
            this.books.add(this.book);
        } else if (Const.TAG_GENRE.equals(localName)) {
            this.book.setGenre(this.genre);
        } else if (Const.TAG_AUTHOR.equals(localName)) {
            this.book.setAuthor(this.author);
        }
    }

    public void characters(XMLEvent event) {
        Characters characters = event.asCharacters();
        String value = characters.getData();
        log("Characters: " + this.current);
        if (Const.TAG_TITLE.equals(this.current)) {
            this.book.setTitle(value);
        } else if (Const.TAG_BODY.equals(this.current)) {
            this.book.setBody(value);
        } else if (Const.TAG_NAME.equals(this.current)) {
            this.author.setName(value);
        } else if (Const.TAG_PLACEOFBIRTH.equals(this.current)) {
            this.author.setPlaceOfBirth(value);
        } else if (Const.TAG_BIOGRAPHY.equals(this.current)) {
            this.author.setBiography(value);
        } else if (Const.TAG_MAJORWORKS.equals(this.current)) {
            this.author.setMajorWorks(value);
        }
    }

    public void startElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        this.current = startElement.getName().getLocalPart();
        log("StartElement: " + startElement.getName());
        Attribute attr = startElement.getAttributeByName(new QName(Const.ATTRIBUTE_ID));

        if (Const.TAG_BOOKS.equals(this.current)) {
            this.books = new ArrayList<>();
        } else if (Const.TAG_BOOK.equals(this.current)) {
            this.book = new Book();
            if (attr != null) {
                this.book.setId(Integer.parseInt(attr.getValue()));
            }
        } else if (Const.TAG_AUTHOR.equals(this.current)) {
            this.author = new Author();
            if (attr != null) {
                this.author.setId(Integer.parseInt(attr.getValue()));
            }
        } else if (Const.TAG_GENRE.equals(this.current)) {
            this.genre = new Genre();
            if (attr != null) {
                this.genre.setId(Integer.parseInt(attr.getValue()));
            }
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
