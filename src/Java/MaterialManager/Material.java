package MaterialManager;

public class Material {
    private String material;     //材  料
    private int num;             //材料量
    private int ID;              //ID  号

    public Material(){

    }

    public Material(String material, int num, int ID)throws NullPointerException{
        this.material = material;
        this.num = num;
        this.ID = ID;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
