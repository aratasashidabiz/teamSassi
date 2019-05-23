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

    public List<ItemBean> findAll() throws DAOException {
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
                tryClose(st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("リソースの解放ができませんでした");
            }
        }
    }

    private List<ItemBean> itemAddLoop(ResultSet rs) throws SQLException {
        ArrayList<ItemBean> itemsBean = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("product_id");
            String title = rs.getString("product_title");
            int price = rs.getInt("product_price");
            String players = rs.getString("cast_name_list");
            String directors = rs.getString("director_name");
            String description = rs.getString("description");
            Timestamp updated = rs.getTimestamp("updated_date");
            Timestamp created = rs.getTimestamp("created_date");
            ItemBean itemBean = new ItemBean(id, title, price, players, directors, description, updated, created);
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

    private void tryClose(PreparedStatement st, ResultSet rs) throws DAOException, SQLException {
        String msg = "リソースが解放できませんでした。";

        if (rs != null || st != null) {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(msg);
            }
        } else {
            throw new DAOException(msg);
        }
    }

    private void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

}
