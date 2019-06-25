package MaterialManager;

import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertMaterialServlet")
public class InsertMaterialServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String material = req.getParameter("material");
        int num = Integer.parseInt(req.getParameter("num"));

        MaterialTools materialTools = new MaterialTools(material);

        int exit = materialTools.FindMaterial();

        if(exit == 0) {
            try {
                int ID = materialTools.FindMaxMaterial();

                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();

                String sql = "insert into Material"
                        + "(ID, material, num)"
                        + "values(" + "'" + ID + "'" + ","
                        + "'" + material + "'" + "," + "'" + num + "'"
                        + ")";

                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                C3P0Utils.executeUpdate(dbUtil_bo);

                C3P0Utils.realseSource(dbUtil_bo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
