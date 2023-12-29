package ua.nure.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.book.entity.book.Author;
import ua.nure.book.entity.book.Book;
import ua.nure.book.entity.book.Genre;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SAXParser extends DefaultHandler {
    private static final boolean LOG_ENABLED = false;

    public static void log(Object o) {
        if (LOG_ENABLED) {
            System.out.println(o);
        }
    }

    private String current;
    private List<Book> books;
    private Genre genre;
    private Author author;
    private Book book;

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
//		throw e; // throw exception if xml not valid
        System.err.println(e.getMessage());
    }

    public List<Book> parse(InputStream in, Schema schema) throws ParserConfigurationException, SAXException, IOException {

        /**
         * SAXParserFactory factory = SAXParserFactory.newInstance();
         *
         * // to be compliant, completely disable DOCTYPE declaration:
         * factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
         *
         * // or completely disable external entities declarations:
         * factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
         * factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
         *
         * // or prohibit the use of all protocols by external entities:
         * SAXParser parser = factory.newSAXParser(); // Noncompliant
         * parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
         * parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
         *
         */
        // XML parsers should not be vulnerable to XXE attacks
        // Fix by yourself
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        // make parser validating
//		factory.setFeature(Const.FEATURE__TURN_VALIDATION_ON, true);
//		factory.setFeature(Const.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);

        factory.setSchema(schema);
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        parser.parse(in, this);

        return this.books;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        this.current = localName;

        if (Const.TAG_BOOKS.equals(this.current)) {
            this.books = new ArrayList<>();
        } else if (Const.TAG_BOOK.equals(this.current)) {
            this.book = new Book();
            if (attributes.getLength() > 0) {
                this.book.setId(Integer.parseInt(attributes.getValue("Id")));
            }
        } else if (Const.TAG_GENRE.equals(this.current)) {
            this.genre = new Genre();
            if (attributes.getLength() > 0) {
                this.genre.setId(Integer.parseInt(attributes.getValue("Id")));
            }
        } else if (Const.TAG_AUTHOR.equals(this.current)) {
            this.author = new Author();
            if (attributes.getLength() > 0) {
                this.author.setId(Integer.parseInt(attributes.getValue("Id")));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (value.isBlank()) {
            return;
        }
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

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (Const.TAG_BOOK.equals(localName)) {
            this.books.add(this.book);
        } else if (Const.TAG_GENRE.equals(localName)) {
            this.book.setGenre(this.genre);
        } else if (Const.TAG_AUTHOR.equals(localName)) {
            this.book.setAuthor(this.author);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Create against validation schema
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(Const.XSD_FILE));

        System.out.println("--== SAX Parser ==--");
        SAXParser parser = new SAXParser();
        parser.parse(new FileInputStream("xml/Books.xml"), schema);
        List<Book> books = parser.getBooks();
        System.out.println("====================================");
        System.out.println("Here is the books: \n" + books);
        System.out.println("====================================");
        parser.parse(new FileInputStream(Const.INVALID_XML_FILE), schema);
        books = parser.getBooks();
        System.out.println("====================================");
        System.out.println("Here is the invalid books: \n" + books);
        System.out.println("====================================");
    }
}


