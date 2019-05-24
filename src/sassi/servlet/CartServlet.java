package sassi.servlet;

import sassi.bean.CartBean;
import sassi.bean.ItemBean;
import sassi.dao.DAOException;
import sassi.dao.ItemDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String mode = req.getParameter("mode");

        if (mode.equals("1")) { // カートに追加

            int itemId = Integer.parseInt(req.getParameter("id"));
            int quantity = 1;
            HttpSession session = req.getSession(true);
            CartBean cart = (CartBean) session.getAttribute("cart");
            if (cart == null) {
                cart = new CartBean();
                session.setAttribute("cart", cart);
            }

            try {
                ItemDAO itemDAO = new ItemDAO();
                ItemBean item = itemDAO.getItem(itemId);
                if(item == null){ // データベースにitemがない場合
                    req.setAttribute("message", "商品が存在しません。");
                    gotoPage(req, resp, "/errInternal.jsp");
                    return;
                }
                cart.addItem(item, quantity);
            } catch (DAOException e) {
                e.printStackTrace();
            }

            gotoPage(req, resp, "cart.jsp");

        }else if(mode.equals("2")){ // 数量の変更

            HttpSession session = req.getSession(false);
            if(session == null) { // セッションオブジェクトなし
                req.setAttribute("message",
                        "セッションが切れています。もう一度トップページより操作してください。");
                gotoPage(req, resp, "errInternal.jsp");
                return;
            }

            int itemId = Integer.parseInt(req.getParameter("id"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            CartBean cart = (CartBean) session.getAttribute("cart");
            if(cart == null) { // カートがない
                req.setAttribute("message", "カートが存在していません。");
                gotoPage(req, resp, "/errInternal.jsp");
                return;
            }

            cart.changeQuantity(itemId, quantity);

            gotoPage(req, resp, "cart.jsp");

        }else if (mode.equals("3")){ // 商品の削除

            HttpSession session = req.getSession(false);
            if(session == null) { // セッションオブジェクトなし
                req.setAttribute("message",
                        "セッションが切れています。もう一度トップページより操作してください。");
                gotoPage(req, resp, "errInternal.jsp");
                return;
            }

            CartBean cart = (CartBean) session.getAttribute("cart");

            if(cart == null) { // カートがない
                req.setAttribute("message", "カートが存在していません。");
                gotoPage(req, resp, "/errInternal.jsp");
                return;
            }

            int itemId = Integer.parseInt(req.getParameter("id"));

            cart.deleteCart(itemId);
            gotoPage(req, resp, "cart.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        gotoPage(req, resp, "cart.jsp");
    }

    private void gotoPage(HttpServletRequest req, HttpServletResponse resp, String toPage) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = req.getRequestDispatcher(toPage);
        rd.forward(req, resp);
    }
}

class Tester{
    public static void main(String[] args) {

    }
}
