package LoginRegister;

import StaffManager.StaffTools;
import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterCheckServlet")
public class RegisterCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String account = req.getParameter("account");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");

        String sex = "未设置";
        String address = "未设置";
        String img = "123.jpg";
        int power = 0;

        //判断用户名是否已经存在
        Tools tools = new Tools(account, password);
        int exit = tools.FindAccount();

        //如果用户名不存在
        if(exit == 0){
            StaffTools staffTools = new StaffTools();
            staffTools.setAccount(account);
            exit = staffTools.FindStaff();
            if(exit == 0) {
                try {
                    int ID = tools.FindMaxID();
                    DBUtil_BO dbUtil_bo = new DBUtil_BO();
                    dbUtil_bo.conn = C3P0Utils.getConnection();
                    String sql = "insert into Customer" + "(account, ID, name, sex, phone, address, password, power, img)"
                            + "values(" + "'" + account + "'" + "," + "'" + ID + "'" + ","
                            + "'" + username + "'" + "," + "'" + sex + "'" + ","
                            + "'" + phone + "'" + "," + "'" + address + "'" + ","
                            + "'" + password + "'" + "," + "'" + power + "'" + ","
                            + "'" + img + "'"
                            + ")";
                    dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                    C3P0Utils.executeUpdate(dbUtil_bo);

                    C3P0Utils.realseSource(dbUtil_bo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
