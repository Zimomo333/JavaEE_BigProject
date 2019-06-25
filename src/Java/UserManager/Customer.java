package UserManager;

//顾客类
public class Customer {
    //主键
    public String account;          //账  号

    //其他信息
    private int ID;                 //ID  号
    private String name;            //姓  名
    private String sex;             //性  别
    private String phone;           //手机号
    private String address;         //地  址
    private String password;        //密  码
    private int power;              //权  限
    private String img;             //头  像

    public Customer(){

    }

    public Customer(String account, int ID, String name, String sex, String phone, String address, String password, int power, String img){
        this.account = account;
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.power = power;
        this.img = img;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
