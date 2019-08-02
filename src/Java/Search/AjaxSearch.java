package Search;

import OrderManager.Orders;
import Utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AjaxSearch")
public class AjaxSearch extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取搜索框输入的内容
        String name=request.getParameter("name");
        name=new String(name.getBytes("iso-8859-1"), "utf-8");
        //向server层调用相应的业务
        QueryRunner runner = new QueryRunner();
        List<Orders> nameList =null;
        Connection connection = C3P0Utils.getConnection();
        try {
            nameList=runner.query(connection, "select orderNumber from orders where orderNumber like ?", new BeanListHandler<>(Orders.class), "%"+name+"%");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            C3P0Utils.close(connection,(PreparedStatement)null, (ResultSet)null);
        }

        String res="";
        for (int i=0;i<nameList.size();i++) {
            if(i>0){
                res+=","+nameList.get(i).getOrderNumber();
            }else{
                res+=nameList.get(i).getOrderNumber();
            }
        }
        //返回结果
        response.getWriter().write(res);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }
}
