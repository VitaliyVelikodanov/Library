package app.entity;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="file_info" type="{http://example.com/cloud_storage}file_info" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fileInfo"
})
@XmlRootElement(name = "files_info", namespace = "http://example.com/cloud_storage")
public class FilesInfo {

    @XmlElement(name = "file_info", namespace = "http://example.com/cloud_storage", required = true)
    protected List<FileInfo> fileInfo;

    /**
     * Gets the value of the fileInfo property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the fileInfo property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileInfo().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileInfo }
     */
    public List<FileInfo> getFileInfo() {
        if (fileInfo == null) {
            fileInfo = new ArrayList<FileInfo>();
        }
        return this.fileInfo;
    }

    @Override
    public String toString() {
        return "FilesInfo{" +
            "fileInfo=" + fileInfo +
            '}';
    }
}
