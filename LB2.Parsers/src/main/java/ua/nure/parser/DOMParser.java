package ua.nure.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.book.entity.book.*;

public class DOMParser {
    private static boolean logEnabled = true;

    private static void log(Object o) {
        if (logEnabled) {
            System.out.println(o);
        }
    }

    private Book parseBook(Node node) {
        Book book = new Book();
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            String localName = item.getLocalName();
            String textContent = item.getTextContent();
            log(localName);
            if (Const.TAG_TITLE.equals(localName)) {
                book.setTitle(textContent);
            } else if (Const.TAG_BODY.equals(localName)) {
                book.setBody(textContent);
            } else if (Const.TAG_AUTHOR.equals(localName)) {
                book.setAuthor(parseAuthor(item));
            } else if (Const.TAG_GENRE.equals(localName)) {
                book.setGenre(parseGenre(item));
            }
        }

        return book;
    }

    private Author parseAuthor(Node node) {
        Author author = new Author();

        Integer attrId = this.GetAttributeIdFromNode(node);
        if (attrId != null) {
            author.setId(attrId);
        }

        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            String localName = item.getLocalName();
            String textContent = item.getTextContent();
            log(localName);

            if (Const.TAG_NAME.equals(localName)) {
                author.setName(textContent);
                this.LogFieldAndValue(localName, textContent);
            } else if (Const.TAG_PLACEOFBIRTH.equals(localName)) {
                author.setPlaceOfBirth(textContent);
                this.LogFieldAndValue(localName, textContent);
            } else if (Const.TAG_BIOGRAPHY.equals(localName)) {
                author.setBiography(textContent);
                this.LogFieldAndValue(localName, textContent);
            } else if (Const.TAG_MAJORWORKS.equals(localName)) {
                author.setMajorWorks(textContent);
                this.LogFieldAndValue(localName, textContent);
            }
        }

        return author;
    }

    private Genre parseGenre(Node node) {
        Genre genre = new Genre();

        Integer attrId = this.GetAttributeIdFromNode(node);
        if (attrId != null) {
            genre.setId(attrId);
        }

        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            String localName = item.getLocalName();
            String textContent = item.getTextContent();
            log(localName);

            if (Const.TAG_GENRENAME.equals(localName)) {
                genre.setGenreName(textContent);
                this.LogFieldAndValue(localName, textContent);
            } else if (Const.TAG_BOOKID.equals(localName)) {
                genre.setBookId(Integer.parseInt(textContent));
                this.LogFieldAndValue(localName, textContent);
            }
        }

        return genre;
    }

    private void LogFieldAndValue(String fieldName, String fieldValue) {
        log(fieldName + "=" + fieldValue);
    }

    private Integer GetAttributeIdFromNode(Node node) {
        if (node.hasAttributes()) {
            NamedNodeMap attrs = node.getAttributes();
            Node item = attrs.getNamedItem(Const.ATTRIBUTE_ID);
            String textContent = item.getTextContent();
            log(item.getLocalName() + "=" + textContent);
            return Integer.parseInt(textContent);
        }

        return null;
    }

    public List<Book> parse(InputStream in, Schema schema)
            throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        // to be compliant, completely disable DOCTYPE declaration:
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // or completely disable external entities declarations:
//		dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
//		dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

        // make parser validating against XSD, which internally referenced in XML doc
        // !!! OLD VULNERABLE FEATURE !!!
//		dbf.setFeature(Const.FEATURE__TURN_VALIDATION_ON, true);
//		dbf.setFeature(Const.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);

        // set the validation against schema
        dbf.setSchema(schema);

        DocumentBuilder db = dbf.newDocumentBuilder();

        // setup validation error handler
        // the preferred way is the throwing an exception
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                System.err.println(e.getMessage()); // log error
//				throw e;
            }
        });

        // get the top of the xml tree
        Document root = db.parse(in);

        List<Book> books = new ArrayList<>();

        // get the root element of the xml document
        Element e = root.getDocumentElement();
        NodeList xmlBooks = e.getElementsByTagNameNS(Const.BOOKS__NAMESPACE_URI, Const.TAG_BOOK);
        for (int i = 0; i < xmlBooks.getLength(); i++) {
            books.add(parseBook(xmlBooks.item(i)));
        }

        return books;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Create against validation schema
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("xml/Books.xsd"));

        System.out.println("--== DOM Parser ==--");
        DOMParser domParser = new DOMParser();
        InputStream in = new FileInputStream("xml/Books.xml");
        List<Book> books = domParser.parse(in, schema);

        System.out.println("====================================");
        System.out.println("Here is the books: \n" + books);
        System.out.println("====================================");

        in = new FileInputStream("xml/invalid_books.xml");
        books = domParser.parse(in, schema);
        System.out.println("====================================");
        System.out.println("Here is the books from invalid xml: \n" + books);
        System.out.println("====================================");
    }
}
