package LoginRegister;

import StaffManager.StaffTools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StaffLoginCheckServlet")
public class StaffLoginCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String account = req.getParameter("account");
        String password = req.getParameter("password");

        //调用自定义的Tool封装类判断用户名和密码是否正确
        StaffTools staffTools = new StaffTools(account, password);
        int exit = staffTools.FindStaff();

        //如果用户名存在，判断密码是否正确
        if(exit == 1){
            exit = staffTools.FindPassword();
        }

        //如果密码正确，记录session
        if(exit == 1){
            exit = staffTools.FindPassword();
            Tools tools = new Tools();
            tools.setAccount(account);
            tools.SetSession(req);
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}