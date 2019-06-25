package LoginRegister;

import Json.Message;
import Json.User;
import Utils.C3P0Utils;
import Utils.DBUtil_BO;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login/dgut")
public class DgutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String token = req.getParameter("token");
        String appid = "javaee";
        String appsecret = "b3b52e43ccfd";
        String userip = req.getRemoteAddr();     //获取本地ip

        // 创建checkToken的参数列表
        List<BasicNameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("token", token));
        paramList.add(new BasicNameValuePair("appid", appid));
        paramList.add(new BasicNameValuePair("appsecret", appsecret));
        paramList.add(new BasicNameValuePair("userip", userip));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        HttpPost httpPost = new HttpPost("https://cas.dgut.edu.cn/ssoapi/v2/checkToken");

        //在实体中得到jsonb
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
        httpPost.setEntity(entity);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        response = httpClient.execute(httpPost);

        String json = null;
        if (response.getEntity() != null)
            json = EntityUtils.toString(response.getEntity(), "utf-8");

        Jsonb jsonb = JsonbBuilder.create();
        Message message = jsonb.fromJson(json, Message.class);


        if (Integer.parseInt(message.getError()) != 0) {
            resp.getWriter().println(json);
            return;
        }
        else {

            String access_token = message.getAccess_token();
            String openid = message.getOpenid();

            List<BasicNameValuePair> paramList1 = new ArrayList<>();
            paramList1.add(new BasicNameValuePair("access_token", access_token));
            paramList1.add(new BasicNameValuePair("openid", openid));

            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost("https://cas.dgut.edu.cn/oauth/getUserInfo");
            entity = new UrlEncodedFormEntity(paramList1);
            httpPost.setEntity(entity);

            //在实体中得到jsonb
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            response = httpClient.execute(httpPost);

            if (response.getEntity() != null)
                json = EntityUtils.toString(response.getEntity(), "utf-8");

            User user = jsonb.fromJson(json, User.class);


            if (user.getError() != null) {
                resp.getWriter().println(json);
                return;
            }

            //判断用户名是否已经存在
            Tools tools = new Tools();
            tools.setAccount(user.getUsername());
            int exit = tools.FindAccount();

            if (exit == 0) {
                //写入
                try {
                    DBUtil_BO dbUtil_bo = new DBUtil_BO();
                    dbUtil_bo.conn = C3P0Utils.getConnection();

                    int ID = tools.FindMaxID();
                    String password = "123456";
                    String img = "123.png";
                    int power = 0;

                    String sql = "insert into Customer" + "(account, ID, name, password, power, img)"
                            + "values(" + "'" + user.getUsername() + "'" + "," + "'" + ID + "'" + ","
                            + "'" + user.getUsername() + "'" + "," + "'" + password + "'" + ","
                            + "'" + power + "'" + "," + "'" + img + "'" + ","
                            + ")";

                    dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                    C3P0Utils.executeUpdate(dbUtil_bo);
                    C3P0Utils.realseSource(dbUtil_bo);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            Map<String, String> info = new HashMap<String, String>();
            info.put("account", user.getUsername());
            HttpSession session = req.getSession(true);
            session.setAttribute("isLogin", info);

            resp.sendRedirect("/webapp/index.jsp");
        }
    }
}