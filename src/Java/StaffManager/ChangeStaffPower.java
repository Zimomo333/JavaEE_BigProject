package StaffManager;

import LoginRegister.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeStaffPower")
public class ChangeStaffPower extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String staffAccount = req.getParameter("staffAccount");
        String password = req.getParameter("password");
        int power = Integer.parseInt(req.getParameter("power"));

        StaffTools staffTools = new StaffTools();

        staffTools.setAccount(staffAccount);

        int mark = staffTools.FindStaff();

        if(mark == 1) {

            Tools tools = new Tools();
            String account = tools.getSession(req);

            StaffTools boss = new StaffTools(account, password);

            mark = boss.FindPassword();

            if(mark == 1){
                Staff staff = staffTools.getStaff();
                staff.setPower(power);
                mark = staffTools.ChangeStaffInfo(staff);
            }

        }

        String json = "{\"mark\":\"" + mark + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
