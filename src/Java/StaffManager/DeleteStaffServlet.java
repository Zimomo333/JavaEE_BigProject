package StaffManager;

import LoginRegister.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteStaffServlet")
public class DeleteStaffServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String account = req.getParameter("account");
        String password = req.getParameter("password");

        StaffTools staffTools = new StaffTools();
        staffTools.setAccount(account);

        int exit = staffTools.FindStaff();
        if(exit == 1){
            Tools tools = new Tools();
            StaffTools adminStaff = new StaffTools(tools.getSession(req), password);
            exit = adminStaff.FindPassword();
            if(exit == 1){
                exit = staffTools.DeleteStaff();
            }
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);

    }
}
