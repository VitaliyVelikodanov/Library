package app.entity;

import jakarta.xml.bind.annotation.*;

import java.math.BigInteger;


/**
 * <p>Java class for entityId complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="entityId"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="id" use="required" type="{http://example.com/cloud_storage}nonNegativeNumber" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entityId", namespace = "http://example.com/cloud_storage")
@XmlSeeAlso({
    FileInfo.class,
    User.class,
    File.class,
    UserPermission.class
})
public abstract class EntityId {

    @XmlAttribute(name = "id", required = true)
    protected BigInteger id;

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

}
