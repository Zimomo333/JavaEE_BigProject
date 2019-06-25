package StaffManager;

import LoginRegister.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeStaffInfoServlet")
public class ChangeStaffInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String IDcard = req.getParameter("IDcard");

        Tools tools = new Tools();;
        String account = tools.getSession(req);

        StaffTools staffTools = new StaffTools();
        staffTools.setAccount(account);
        Staff staff = staffTools.getStaff();

        if(name != null)
            staff.setName(name);
        if(sex != null)
            staff.setSex(sex);
        if(phone != null)
            staff.setPhone(phone);
        if(address != null)
            staff.setAddress(address);
        if(IDcard != null)
            staff.setIDcard(IDcard);

        int mark = staffTools.ChangeStaffInfo(staff);

        String json = "{\"mark\":\"" + mark + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
