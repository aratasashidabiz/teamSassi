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

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mode = request.getParameter("mode");
        request.setCharacterEncoding("UTF-8");

        if (mode.equals("1")) {
            OrderDAO order = null;
            try {
                order = new OrderDAO();
            } catch (DAOException e) {
                e.printStackTrace();
            }
            HttpSession session = request.getSession(true);
            CartBean cart = (CartBean) session.getAttribute("cart");
            String name = request.getParameter("name");
            String postal = request.getParameter("postal");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            CustomerBean customer = new CustomerBean(name, postal, address, phone);
            try {
                order.saveOrdered(customer, cart);
            } catch (DAOException e) {
                e.printStackTrace();
            }
            request.setAttribute("customer", customer);
            request.setAttribute("cart", cart);
            session.removeAttribute("cart");
            gotoPage(request, response, "/ordered.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doPost(request, response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
