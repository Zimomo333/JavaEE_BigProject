package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login/dgut")
public class TokenFilter implements Filter {

    public void destroy() {
    }
    public void init(FilterConfig config) throws ServletException {

    }
    public  void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = req.getParameter("token");

        if(token == null){
            res.sendRedirect("https://cas.dgut.edu.cn?appid=javaee&state=STATE");
        }
        else{
            chain.doFilter(req,res);
        }

    }

}

