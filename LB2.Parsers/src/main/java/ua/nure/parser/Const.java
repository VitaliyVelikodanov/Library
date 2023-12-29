package ua.nure.parser;

public interface Const {

	String TAG_BOOK = "Book";
	String TAG_BOOKS = "Books";
	String ATTRIBUTE_ID = "Id";
	String TAG_NAME = "Name";
	String TAG_BOOKID = "BookId";
	String TAG_BODY = "Body";
	String TAG_TITLE = "Title";
	String BOOKS__NAMESPACE_URI = "http://book.nure.ua/entity";
	String TAG_AUTHOR = "Author";
	String TAG_GENRE = "Genre";


	String XML_FILE = "xml/Books.xml";
	String INVALID_XML_FILE = "xml/invalid_books.xml";
	String XSD_FILE = "xml/Books.xsd";
	Class<?> OBJECT_FACTORY = ua.nure.book.entity.ObjectFactory.class;
	
	String SCHEMA_LOCATION__ATTR_NAME = "schemaLocation";
	String SCHEMA_LOCATION__ATTR_FQN = "xsi:schemaLocation";
	String XSI_SPACE__PREFIX = "xsi";
	String SCHEMA_LOCATION__URI = "http://book.nure.ua/entity Books.xsd";

	// validation features
	public static final String FEATURE__TURN_VALIDATION_ON = 
			"http://xml.org/sax/features/validation";
	public static final String FEATURE__TURN_SCHEMA_VALIDATION_ON = 
			"http://apache.org/xml/features/validation/schema";

	String TAG_PLACEOFBIRTH = "PlaceOfBirth";
	String TAG_BIOGRAPHY = "Biography";
	String TAG_GENRENAME = "GenreName";
	Object TAG_MAJORWORKS = "MajorWorks";
}
