//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.12.15 at 06:29:38 PM EET 
//


package ua.nure.book.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Genre complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Genre"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://book.nure.ua/entity}EntityWithId"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GenreName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BookId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Genre", propOrder = {
    "genreName",
    "bookId"
})
public class Genre
    extends EntityWithId
{

    @XmlElement(name = "GenreName")
    protected String genreName;
    @XmlElement(name = "BookId")
    protected int bookId;

    /**
     * Gets the value of the genreName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * Sets the value of the genreName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenreName(String value) {
        this.genreName = value;
    }

    /**
     * Gets the value of the bookId property.
     * 
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Sets the value of the bookId property.
     * 
     */
    public void setBookId(int value) {
        this.bookId = value;
    }

}
