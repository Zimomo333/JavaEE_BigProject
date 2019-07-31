package OrderManager;

import java.sql.Timestamp;

public class Orders {
    private int orderNumber;        //订 单 号
    private String userAccount;     //顾    客
    private String staffAccount;    //负 责 人
    private double paid;             //已付金额
    private String address;         //送货地址
    private String request;         //用户备注
    private String requestMaterial; //所需材料
    private String isDistribute;    //是否配材
    private String isAccept;        //是否接受
    private String isCompletion;    //是否完成
    private Timestamp startDate;    //开始日期
    private Timestamp realDate;     //实际日期

    public Orders(){

    }

    public Orders(int orderNumber, String userAccount, String staffAccount, float paid, String address, String request, String requestMaterial, String isCompletion, String isDistribute, String isAccept, Timestamp startDate, Timestamp realDate){
        this.orderNumber = orderNumber;
        this.userAccount = userAccount;
        this.staffAccount = staffAccount;
        this.paid = paid;
        this.address = address;
        this.request = request;
        this.requestMaterial = requestMaterial;
        this.isCompletion = isCompletion;
        this.isDistribute = isDistribute;
        this.isAccept = isAccept;
        this.startDate = startDate;
        this.realDate = realDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getStaffAccount() {
        return staffAccount;
    }

    public void setStaffAccount(String staffAccount) {
        this.staffAccount = staffAccount;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequestMaterial() {
        return requestMaterial;
    }

    public void setRequestMaterial(String requestMaterial) {
        this.requestMaterial = requestMaterial;
    }

    public String getIsCompletion() {
        return isCompletion;
    }

    public String getIsDistribute() {
        return isDistribute;
    }

    public void setIsDistribute(String isDistribute) {
        this.isDistribute = isDistribute;
    }

    public String getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }

    public void setIsCompletion(String isCompletion) {
        this.isCompletion = isCompletion;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getRealDate() {
        return realDate;
    }

    public void setRealDate(Timestamp realDate) {
        this.realDate = realDate;
    }
}
