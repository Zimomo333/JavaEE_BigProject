package UserManager;

import LoginRegister.Tools;
import StaffManager.Staff;
import StaffManager.StaffTools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeUserPasswordServlet")
public class ChangeUserPasswordServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{


        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");

        Tools tools = new Tools();
        String account = tools.getSession(req);

        StaffTools staffTools = new StaffTools();
        staffTools.setAccount(account);

        tools.setAccount(account);
        int exit = tools.FindAccount();

        if(exit == 0){
            int flag = staffTools.FindStaff();
            if(flag == 1){
                staffTools.setPassword(oldPassword);
                exit = staffTools.FindPassword();
                if(exit == 1){
                    Staff staff = staffTools.getStaff();
                    staff.setPassword(newPassword);
                    staffTools.ChangeStaffInfo(staff);
                }
            }
        }
        else{
            tools.setPassword(oldPassword);
            exit = tools.FindPassword();
            if(exit == 1){
                Customer customer = tools.getCustomer();
                customer.setPassword(newPassword);
                tools.ChangeAccountInfo(customer);
            }
        }

        req.getSession().removeAttribute("isLogin");  //清除Session记录

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
