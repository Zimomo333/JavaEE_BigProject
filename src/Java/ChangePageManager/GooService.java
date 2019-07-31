package ChangePageManager;

import OrderManager.Orders;

import Utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class GooService {

    public List<Orders> getPageList(Integer i, Integer j) {
        QueryRunner runner = new QueryRunner();
        List<Orders> list =null;
        Connection connection = C3P0Utils.getConnection();
        try {
            list=runner.query(connection, "select * from ORDERS limit ?,?", new BeanListHandler<>(Orders.class), i,j);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            C3P0Utils.close(connection,(PreparedStatement)null, (ResultSet)null);
        }
//		System.out.println(list);
        return list;
    }

    public Integer getCounter() {
        Integer counter=null;
        Connection connection =C3P0Utils.getConnection();
        PreparedStatement pst=null;
        ResultSet rs = null;
        try {
            pst=connection.prepareStatement("select count(orderNumber) from ORDERS");
            rs=pst.executeQuery();
            while(rs.next()) {
                counter=rs.getInt(1);
            }
//			System.out.println(counter);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            C3P0Utils.close(connection,(PreparedStatement)null, (ResultSet)null);
        }
        return counter;
    }

}
