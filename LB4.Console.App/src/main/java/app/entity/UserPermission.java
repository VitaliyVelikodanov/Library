package app.entity;

import jakarta.xml.bind.annotation.*;

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for user_permission complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="user_permission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://example.com/cloud_storage}entityId"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="access_type" type="{http://example.com/cloud_storage}access_type"/&gt;
 *         &lt;element name="end_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user_permission", namespace = "http://example.com/cloud_storage", propOrder = {
    "accessType",
    "endDate"
})
public class UserPermission
    extends EntityId {

    @XmlElement(name = "access_type", namespace = "http://example.com/cloud_storage", required = true)
    @XmlSchemaType(name = "string")
    protected AccessType accessType;
    @XmlElement(name = "end_date", namespace = "http://example.com/cloud_storage", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;

    /**
     * Gets the value of the accessType property.
     *
     * @return possible object is
     * {@link AccessType }
     */
    public AccessType getAccessType() {
        return accessType;
    }

    /**
     * Sets the value of the accessType property.
     *
     * @param value allowed object is
     *              {@link AccessType }
     */
    public void setAccessType(AccessType value) {
        this.accessType = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    @Override
    public String toString() {
        return "UserPermission{" +
            "accessType=" + accessType +
            ", endDate=" + endDate +
            '}';
    }
}
