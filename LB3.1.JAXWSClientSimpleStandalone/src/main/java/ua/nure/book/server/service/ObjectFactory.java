
package ua.nure.book.server.service;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.book.server.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DAOException_QNAME = new QName("http://book.nure.ua/server/service", "DAOException");
    private final static QName _AddBook_QNAME = new QName("http://book.nure.ua/server/service", "addBook");
    private final static QName _AddBookResponse_QNAME = new QName("http://book.nure.ua/server/service", "addBookResponse");
    private final static QName _EditBook_QNAME = new QName("http://book.nure.ua/server/service", "editBook");
    private final static QName _EditBookResponse_QNAME = new QName("http://book.nure.ua/server/service", "editBookResponse");
    private final static QName _ListBooks_QNAME = new QName("http://book.nure.ua/server/service", "listBooks");
    private final static QName _ListBooksResponse_QNAME = new QName("http://book.nure.ua/server/service", "listBooksResponse");
    private final static QName _RemoveBook_QNAME = new QName("http://book.nure.ua/server/service", "removeBook");
    private final static QName _RemoveBookResponse_QNAME = new QName("http://book.nure.ua/server/service", "removeBookResponse");
    private final static QName _TakeBook_QNAME = new QName("http://book.nure.ua/server/service", "takeBook");
    private final static QName _TakeBookResponse_QNAME = new QName("http://book.nure.ua/server/service", "takeBookResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.book.server.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DAOException }
     * 
     */
    public DAOException createDAOException() {
        return new DAOException();
    }

    /**
     * Create an instance of {@link AddBook }
     * 
     */
    public AddBook createAddBook() {
        return new AddBook();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link EditBook }
     * 
     */
    public EditBook createEditBook() {
        return new EditBook();
    }

    /**
     * Create an instance of {@link EditBookResponse }
     * 
     */
    public EditBookResponse createEditBookResponse() {
        return new EditBookResponse();
    }

    /**
     * Create an instance of {@link ListBooks }
     * 
     */
    public ListBooks createListBooks() {
        return new ListBooks();
    }

    /**
     * Create an instance of {@link ListBooksResponse }
     * 
     */
    public ListBooksResponse createListBooksResponse() {
        return new ListBooksResponse();
    }

    /**
     * Create an instance of {@link RemoveBook }
     * 
     */
    public RemoveBook createRemoveBook() {
        return new RemoveBook();
    }

    /**
     * Create an instance of {@link RemoveBookResponse }
     * 
     */
    public RemoveBookResponse createRemoveBookResponse() {
        return new RemoveBookResponse();
    }

    /**
     * Create an instance of {@link TakeBook }
     * 
     */
    public TakeBook createTakeBook() {
        return new TakeBook();
    }

    /**
     * Create an instance of {@link TakeBookResponse }
     * 
     */
    public TakeBookResponse createTakeBookResponse() {
        return new TakeBookResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DAOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DAOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "DAOException")
    public JAXBElement<DAOException> createDAOException(DAOException value) {
        return new JAXBElement<DAOException>(_DAOException_QNAME, DAOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "addBook")
    public JAXBElement<AddBook> createAddBook(AddBook value) {
        return new JAXBElement<AddBook>(_AddBook_QNAME, AddBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "addBookResponse")
    public JAXBElement<AddBookResponse> createAddBookResponse(AddBookResponse value) {
        return new JAXBElement<AddBookResponse>(_AddBookResponse_QNAME, AddBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EditBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "editBook")
    public JAXBElement<EditBook> createEditBook(EditBook value) {
        return new JAXBElement<EditBook>(_EditBook_QNAME, EditBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EditBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "editBookResponse")
    public JAXBElement<EditBookResponse> createEditBookResponse(EditBookResponse value) {
        return new JAXBElement<EditBookResponse>(_EditBookResponse_QNAME, EditBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListBooks }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListBooks }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "listBooks")
    public JAXBElement<ListBooks> createListBooks(ListBooks value) {
        return new JAXBElement<ListBooks>(_ListBooks_QNAME, ListBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListBooksResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListBooksResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "listBooksResponse")
    public JAXBElement<ListBooksResponse> createListBooksResponse(ListBooksResponse value) {
        return new JAXBElement<ListBooksResponse>(_ListBooksResponse_QNAME, ListBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "removeBook")
    public JAXBElement<RemoveBook> createRemoveBook(RemoveBook value) {
        return new JAXBElement<RemoveBook>(_RemoveBook_QNAME, RemoveBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RemoveBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "removeBookResponse")
    public JAXBElement<RemoveBookResponse> createRemoveBookResponse(RemoveBookResponse value) {
        return new JAXBElement<RemoveBookResponse>(_RemoveBookResponse_QNAME, RemoveBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeBook }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TakeBook }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "takeBook")
    public JAXBElement<TakeBook> createTakeBook(TakeBook value) {
        return new JAXBElement<TakeBook>(_TakeBook_QNAME, TakeBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeBookResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TakeBookResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://book.nure.ua/server/service", name = "takeBookResponse")
    public JAXBElement<TakeBookResponse> createTakeBookResponse(TakeBookResponse value) {
        return new JAXBElement<TakeBookResponse>(_TakeBookResponse_QNAME, TakeBookResponse.class, null, value);
    }

}
