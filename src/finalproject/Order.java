import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TypeHost;

import java.util.Date;

public class Order {
    private String appID;
    private String orderId;
    private String date;
    private String clientName;
    private String itemName;
    private int unitsCount;
    private double netItemPrice;
    private double taxPercentage;

    public Order(){
    }

    public Order(String appID,String orderId,String date,String clientName,String itemName, int unitsCount,double netItemPrice,double taxPercentage){
        this.appID = appID;
        this.orderId = orderId;
        this.date = date;
        this.clientName = clientName;
        this.itemName = itemName;
        this.unitsCount = unitsCount;
        this.netItemPrice =  Math.floor(netItemPrice * 100) / 100;
        this.taxPercentage =  Math.floor(taxPercentage * 100) / 100;
    }

    // Getters and Setters


    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getUnitsCount() {
        return unitsCount;
    }

    public void setUnitsCount(int unitsCount) {
        this.unitsCount = unitsCount;
    }

    public double getNetItemPrice() {
        return netItemPrice;
    }

    public void setNetItemPrice(double netItemPrice) {
        this.netItemPrice =  Math.floor(netItemPrice * 100) / 100;;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage =  Math.floor(taxPercentage * 100) / 100;;
    }

    @Override
    public String toString() {
        return orderId+"  "+date+"  "+"  "+clientName+"  "+itemName+"  "+unitsCount+"  "+netItemPrice+"$  "+taxPercentage+"%";
    }

    public String toCsv() {
        return appID+","+orderId+","+date+","+clientName+","+itemName+","+unitsCount+","+netItemPrice+","+taxPercentage;
    }
}