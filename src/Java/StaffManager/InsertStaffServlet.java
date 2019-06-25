package StaffManager;

import LoginRegister.Tools;
import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertStaffServlet")
public class InsertStaffServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

        String account = req.getParameter("account");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String IDcard = req.getParameter("IDcard");
        String power = req.getParameter("power");

        String phone = "未设置";
        String address = "未设置";
        String password = "123456";
        String img = "123.jpg";

        //判断员工用户名是否已经存在
        StaffTools staffTools = new StaffTools(account, password);
        int exit = staffTools.FindStaff();

        //如果用户名不存在
        if(exit == 0){
            Tools tools = new Tools();
            tools.setAccount(account);
            exit = tools.FindAccount();
            if(exit == 0) {
                try {
                    int workNum = staffTools.FindMaxWorkNum();
                    DBUtil_BO dbUtil_bo = new DBUtil_BO();
                    dbUtil_bo.conn = C3P0Utils.getConnection();
                    String sql = "insert into Staff"
                            + "(account, workNum, name, sex, phone, address, IDcard, password, power, img)"
                            + "values(" + "'" + account + "'" + "," + "'" + workNum + "'" + ","
                            + "'" + name + "'" + "," + "'" + sex + "'" + "," + "'"
                            + phone + "'" + "," + "'" + address + "'" + ","
                            + "'" + IDcard + "'" + "," + "'" + password + "'" + ","
                            + "'" + power + "'" + "," + "'" + img + "'"
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
