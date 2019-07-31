package ChangePageManager;

import OrderManager.Orders;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GooService gooService = new GooService();
    private PageUtils pageUtils = null;
    final Integer PAGESIZE = 4;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String method = request.getParameter("method");
        if ("changePage".equals(method)) {
            changePage(request, response);
        } else {
            getGooList(request, response);
        }
    }

    private void changePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Orders> goolist = null;
        List<Object> list = new ArrayList<>();
        String page = request.getParameter("page");
//	System.out.println(page);
        pageUtils = new PageUtils(page, PAGESIZE, gooService.getCounter());
        goolist = gooService.getPageList((Integer.parseInt(page) - 1) * PAGESIZE, PAGESIZE);
        list.add(pageUtils);
        list.add(goolist);
        String string = JSON.toJSONString(list);
//	System.out.println(string);
        response.getWriter().write(string);
    }

    private void getGooList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Orders> goolist = null;
        List<Object> list = new ArrayList<>();
        String currentPage = null;
        pageUtils = new PageUtils(currentPage, PAGESIZE, gooService.getCounter());
        goolist = gooService.getPageList((pageUtils.getCurrentPage() - 1) * PAGESIZE, PAGESIZE);
        list.add(pageUtils);
        list.add(goolist);
        String string = JSON.toJSONString(list);
//	System.out.println(string);
        response.getWriter().write(string);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
