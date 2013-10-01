package jp.sample.jaxrs.service;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "orderNo", "userName", "itemName", "orderDate" })
public class Order {

    /** order no. */
    private Long orderNo;

    /** user name. */
    private String userName;

    /** item name. */
    private String itemName;

    /** order date. */
    private Date orderDate;

    /**
     * @return the orderNo
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     *            the orderNo to set
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName
     *            the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate
     *            the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
