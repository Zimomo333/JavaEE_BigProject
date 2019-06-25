package ProductionManager;

import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import java.util.ArrayList;
import java.util.List;

public class ProductionTools {

    private int orderNumber;

    public ProductionTools(){

    }

    public ProductionTools(int orderNumber){
        this.orderNumber = orderNumber;
    }

    public List GetSingleRs() {

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Produce where orderNumber =" + "'" + orderNumber + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Produce produce = new Produce();
                produce.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                produce.setNeedPay(dbUtil_bo.rs.getString("needPay"));
                produce.setID(dbUtil_bo.rs.getInt("ID"));
                list.add(produce);

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
            String sql = "select * from Produce";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Produce produce = new Produce();
                produce.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                produce.setNeedPay(dbUtil_bo.rs.getString("needPay"));
                produce.setID(dbUtil_bo.rs.getInt("ID"));
                list.add(produce);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int FindProduce(){
        //判断该产品是否存在
        int exit = 0;
        try {
            List list = GetSingleRs();
            if(list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    Produce produce = (Produce)list.get(i);
                    if (produce.getOrderNumber() == orderNumber) {
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

    public int FindMaxProduction(){
        int num = 1;
        List list = GetRs();
        if(list.size() != 0) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "select MAX(orderNumber) from Produce";
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

    public int DeleteOrder(){
        int flag = 1;
        //判断产品是否存在
        int exit = FindProduce();
        if(exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "DELETE FROM Produce a where a.orderNumber = " + "'" + orderNumber + "'";
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

    public Produce produce() {
        int exit = FindProduce();
        if (exit == 1) {
            List list = GetSingleRs();
            if(list.size() > 0){
                Produce produce = (Produce)list.get(0);
                return produce;
            }
            else
                return null;
        } else
            return null;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

}
