package sassi.servlet;

import sassi.bean.CartBean;
import sassi.bean.CustomerBean;
import sassi.dao.DAOException;
import sassi.dao.OrderDAO;

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

        if (mode.equals("1")) {
            OrderDAO order = null;
            try {
                order = new OrderDAO();
            } catch (DAOException e) {
                e.printStackTrace();
            }
            HttpSession session = request.getSession(true);
            CartBean cart = (CartBean) session.getAttribute("cart");
            String name = (String) request.getAttribute("name");
            String postal = (String) request.getAttribute("postal");
            String address = (String) request.getAttribute("address");
            String phone = (String) request.getAttribute("phone");
            CustomerBean customer = new CustomerBean(name, postal, address, phone);
            try {
                order.saveOrdered(customer, cart);
            } catch (DAOException e) {
                e.printStackTrace();
            }
            request.setAttribute("order", order);
            session.setAttribute("cart", cart);
            gotoPage(request, response, "/saved.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
