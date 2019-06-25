package LoginRegister;

import StaffManager.StaffTools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String account = req.getParameter("account");
        String password = req.getParameter("password");

        //调用自定义的Tool封装类判断用户名和密码是否正确
        Tools tools = new Tools(account, password);
        int exit = tools.FindAccount();

        //如果用户名存在，判断密码是否正确
        if(exit == 1){
            exit = tools.FindPassword();
        }
        else{
            StaffTools staffTools = new StaffTools(account, password);
            int flag = staffTools.FindStaff();
            if(flag == 1){
                flag = staffTools.FindPassword();
            }
            exit = flag;
        }

        //如果密码正确，记录session
        if(exit == 1){
            tools.SetSession(req);
        }


        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}