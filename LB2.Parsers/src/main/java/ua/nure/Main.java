package ua.nure;

import ua.nure.parser.*;

public class Main {

	public static void main(String[] args) throws Exception {
		DOMParser.main(new String[] {});
		SAXParser.main(new String[] {});
		StaxParser.main(new String[] {});
		Stax2Parser.main(new String[] {});
		JAXBParser.main(new String[] {});

		XSLTransform.main(new String[] { "xml/Books.xsl", "xml/Books.xml", "xml/Books.html" });
	}
}
