package app.entity;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for access_type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="access_type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="read"/&gt;
 *     &lt;enumeration value="write"/&gt;
 *     &lt;enumeration value="none"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "access_type", namespace = "http://example.com/cloud_storage")
@XmlEnum
public enum AccessType {

    @XmlEnumValue("read")
    READ("read"),
    @XmlEnumValue("write")
    WRITE("write"),
    @XmlEnumValue("none")
    NONE("none");
    private final String value;

    AccessType(String v) {
        value = v;
    }

    public static AccessType fromValue(String v) {
        for (AccessType c : AccessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
