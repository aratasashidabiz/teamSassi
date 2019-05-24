package sassi.servlet;

import sassi.bean.CartBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/mbshop/OrdersServlet")
public class OrdersServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mode = request.getParameter("mode");

        if(mode.equals("1")){
            OrderDAO order = new OrderDAO();
            HttpSession session = request.getSession(true);
            CartBean cart = (CartBean) session.getAttribute("cart");
            order.setOrderItems(cart);
            order.save();
            request.setAttribute("order",order);
            session.setAttribute("cart",null);
            gotoPage(request,response,"/saved.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request,response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
