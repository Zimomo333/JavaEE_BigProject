package ProductionManager;

public class Produce {

    private int orderNumber;     //订 单 号
    private String needPay;      //需付金额
    private int ID;              //ID    号

    public Produce(){

    }

    public Produce(int orderNumber, String needPay, int ID){
        this.orderNumber = orderNumber;
        this.needPay = needPay;
        this.ID = ID;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNeedPay() {
        return needPay;
    }

    public void setNeedPay(String needPay) {
        this.needPay = needPay;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
