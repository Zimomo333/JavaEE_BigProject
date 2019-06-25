package MaterialManager;

import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import java.util.ArrayList;
import java.util.List;

public class MaterialTools {

    private String material;

    public MaterialTools(){

    }

    public MaterialTools(String material){
        this.material = material;
    }

    public List GetSingleRs() {

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();


            String sql = "select * from Material where material =" + "'" + material + "'";

            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Material mat = new Material();
                mat.setMaterial(dbUtil_bo.rs.getString("material"));
                mat.setNum(dbUtil_bo.rs.getInt("num"));
                mat.setID(dbUtil_bo.rs.getInt("ID"));
                list.add(mat);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List GetRs() {

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Material";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Material mat = new Material();
                mat.setMaterial(dbUtil_bo.rs.getString("material"));
                mat.setNum(dbUtil_bo.rs.getInt("num"));
                mat.setID(dbUtil_bo.rs.getInt("ID"));
                list.add(mat);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int FindMaterial(){
        //判断该材料是否存在
        int exit = 0;
        try {
            List list = GetSingleRs();
            if(list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    Material mat = (Material)list.get(i);
                    if (mat.getMaterial().equals(material)) {
                        exit = 1;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return exit;
    }

    public int FindMaxMaterial(){
        int num = 1;
        List list = GetRs();
        if(list.size() != 0) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "select MAX(ID) from Material";
                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                C3P0Utils.executeQuery(dbUtil_bo);
                dbUtil_bo.rs.next();
                int number = Integer.valueOf(dbUtil_bo.rs.getInt(1));
                num = number + 1;
                C3P0Utils.realseSource(dbUtil_bo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return num;
    }

    public int DeleteMaterial(){
        //判断零件是否存在
        int flag = 1;
        int exit = FindMaterial();
        if(exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "DELETE FROM Material a where a.material = " + "'" + material + "'";
                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                C3P0Utils.executeUpdate(dbUtil_bo);
                C3P0Utils.realseSource(dbUtil_bo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            flag = 0;
        return flag;
    }

    public int ChangeMaterialInfo(Material mat) {

        int flag = 1;
        int exit = FindMaterial();

        if (exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();

                String sql = "update Material set num = " + "'" + mat.getNum() + "'where material =" + "'" + mat.getMaterial() + "'";

                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);

                C3P0Utils.executeUpdate(dbUtil_bo);
                C3P0Utils.realseSource(dbUtil_bo);

            }catch (Exception e) {
                e.printStackTrace();
            }
        } else
            flag = 0;
        return flag;
    }

    public Material getMaterial() {
        int exit = FindMaterial();
        if (exit == 1) {
            List list = GetSingleRs();
            if(list.size() > 0){
                Material mat = (Material) list.get(0);
                return mat;
            }
            else
                return null;
        } else
            return null;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialName(){
        return material;
    }
}
