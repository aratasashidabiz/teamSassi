package sassi.dao;

import sassi.bean.ItemBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connection;

    public ItemDAO() throws DAOException {
        getConnection();
    }

    public List<ItemBean> getListAll() throws DAOException {
        if (connection == null) {
            getConnection();
        }

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "select * from product";
            st = connection.prepareStatement(sql);
            rs= st.executeQuery();
            return itemAddLoop(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("レコードを取得できませんでした。");
        } finally {
            try {
                close(st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("リソースの解放ができませんでした");
            }
        }
    }

    public ItemBean getItem(int id) throws DAOException {
        if (connection == null) {
            getConnection();
        }

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "select * from product where id = ?";
            st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            rs.next();
            ItemBean bean = new ItemBean(rs);
            return bean;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("リソースを解放できませんでした。");
        } finally {
            try {
                close(st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("リソースを解放できませんでした");
            }
        }
    }

    private List<ItemBean> itemAddLoop(ResultSet rs) throws SQLException {
        ArrayList<ItemBean> itemsBean = new ArrayList<>();
        while (rs.next()) {
            ItemBean itemBean = new ItemBean(rs);
            itemsBean.add(itemBean);
        }
        return itemsBean;
    }

    private void getConnection() throws DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:33061/mbshop";
            String user = "mbshop";
            String pass = "himitu";
            Connection connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("接続に失敗しました。");
        }
    }

    private void close(PreparedStatement st, ResultSet rs) throws DAOException, SQLException {
        String msg = "リソースが解放できませんでした。";

        if (rs != null || st != null) {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(msg);
            }
        } else {
            throw new DAOException(msg);
        }
    }
}
