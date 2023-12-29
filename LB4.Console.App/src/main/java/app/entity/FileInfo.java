package app.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for file_info complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="file_info"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://example.com/cloud_storage}entityId"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="user" type="{http://example.com/cloud_storage}user"/&gt;
 *         &lt;element name="file" type="{http://example.com/cloud_storage}file"/&gt;
 *         &lt;element name="user_permissions" type="{http://example.com/cloud_storage}user_permission"/&gt;
 *         &lt;element name="description"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "file_info", namespace = "http://example.com/cloud_storage", propOrder = {
    "user",
    "file",
    "userPermissions",
    "description"
})
public class FileInfo
    extends EntityId {

    @XmlElement(namespace = "http://example.com/cloud_storage", required = true)
    protected User user;
    @XmlElement(namespace = "http://example.com/cloud_storage", required = true)
    protected File file;
    @XmlElement(name = "user_permissions", namespace = "http://example.com/cloud_storage", required = true)
    protected UserPermission userPermissions;
    @XmlElement(namespace = "http://example.com/cloud_storage", required = true)
    protected String description;

    /**
     * Gets the value of the user property.
     *
     * @return possible object is
     * {@link User }
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     *
     * @param value allowed object is
     *              {@link User }
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the file property.
     *
     * @return possible object is
     * {@link File }
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     *
     * @param value allowed object is
     *              {@link File }
     */
    public void setFile(File value) {
        this.file = value;
    }

    /**
     * Gets the value of the userPermissions property.
     *
     * @return possible object is
     * {@link UserPermission }
     */
    public UserPermission getUserPermissions() {
        return userPermissions;
    }

    /**
     * Sets the value of the userPermissions property.
     *
     * @param value allowed object is
     *              {@link UserPermission }
     */
    public void setUserPermissions(UserPermission value) {
        this.userPermissions = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
            "user=" + user +
            ", file=" + file +
            ", userPermissions=" + userPermissions +
            ", description='" + description + '\'' +
            '}';
    }
}
