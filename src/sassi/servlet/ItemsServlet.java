package sassi.servlet;

import sassi.bean.ItemBean;
import sassi.dao.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mbshop/ItemsServlet")
public class ItemsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 商品検索
        String mode = request.getParameter("mode");

        if(mode.equals("1")){
            showDetail(request,response);
        }else{
            searchKeyword(request,response);
        }
    }

    protected void searchKeyword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        String keyword = request.getParameter("keyword");
        String page = request.getParameter("page");
        List<String> ItemList = new ArrayList<>();

        try{
            if(page.length() != 0 || page != null){
                ItemList = ItemDao.getList(keyword,page);
            }
            else if(keyword.length() != 0 || keyword != null){
                ItemList = ItemDao.getList(keyword);
            }
            else if(keyword.length() == 0 || keyword == null){
                ItemList = ItemDao.getList("");
            }
            request.setAttribute("ItemList",ItemList);
            gotoPage(request,response,"/list.jsp");
        }catch (DAOException e){
            e.printStackTrace();
            request.setAttribute("message", "内部エラーが発生しました。");
            gotoPage(request, response, "/errInternal.jsp");
        }
    }

    protected void showDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        ItemBean item = new ItemBean();
        String id = request.getParameter("id");
        item = ItemDao.getList(id);
        gotoPage(request,response,"/detail.jsp");
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
