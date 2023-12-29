
package ua.nure.book.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Author complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Author"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://book.nure.ua/entity}EntityWithId"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PlaceOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Biography" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MajorWorks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Author", propOrder = {
    "name",
    "placeOfBirth",
    "biography",
    "majorWorks"
})
public class Author
    extends EntityWithId
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "PlaceOfBirth")
    protected String placeOfBirth;
    @XmlElement(name = "Biography")
    protected String biography;
    @XmlElement(name = "MajorWorks")
    protected String majorWorks;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the placeOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets the value of the placeOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceOfBirth(String value) {
        this.placeOfBirth = value;
    }

    /**
     * Gets the value of the biography property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Sets the value of the biography property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBiography(String value) {
        this.biography = value;
    }

    /**
     * Gets the value of the majorWorks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajorWorks() {
        return majorWorks;
    }

    /**
     * Sets the value of the majorWorks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorWorks(String value) {
        this.majorWorks = value;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", biography='" + biography + '\'' +
                ", majorWorks='" + majorWorks + '\'' +
                '}';
    }
}