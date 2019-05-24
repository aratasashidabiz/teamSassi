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
            ItemBean bean = new ItemBean(
                    rs.getInt("product_id"),
                    rs.getString("product_title"),
                    rs.getInt("product_price"),
                    rs.getString("cast_name_list"),
                    rs.getString("director_name"),
                    rs.getString("description"),
                    rs.getTimestamp("updated_date"),
                    rs.getTimestamp("created_date")
            );
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
