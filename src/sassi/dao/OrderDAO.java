package sassi.dao;

import sassi.bean.CartBean;
import sassi.bean.CustomerBean;
import sassi.bean.ItemBean;

import java.sql.*;
import java.util.Collection;
import java.util.Map;

public class OrderDAO {

    private Connection connection;

    public OrderDAO() throws DAOException {
        getConnection();
    }

    public int saveOrdered(CustomerBean customer, CartBean cart)
            throws DAOException {
        if (connection == null)
            getConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int lastInsertId = 0;

        try {
            // 注文先の顧客情報のOrderedテーブルへの追加
            String sql = "INSERT INTO ordered(customer_name, zip_code, address, telephone_number) VALUES(?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPostal());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());

            // SQLの実行
            statement.executeUpdate();
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
            if (resultSet != null && resultSet.next()) {
                lastInsertId = resultSet.getInt("LAST");
            }
            statement.close();

            // 注文情報のOrderedDetailテーブルへの追加
            saveOrderedDetail(cart, lastInsertId);

            return lastInsertId;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("レコードの操作に失敗しました。");
        } finally {
            try {
                // リソースの開放
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                close();
            } catch (Exception e) {
                throw new DAOException("リソースの開放に失敗しました。");
            }
        }
    }

    private void getConnection() throws DAOException {
        try {
            // JDBCドライバの登録
//			Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            // URL、ユーザ名、パスワードの設定
            String url = "jdbc:mysql://localhost:33061/mbshop";
            String user = "mbshop";
            String password = "himitu";

            // データベースへの接続
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("接続に失敗しました。");
        }
    }

    public int saveOrderedDetail(CartBean cart, int ordered_id) throws DAOException {
        if (connection == null) getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int lastOrderedDetailId = 0;

        try {
            // 注文情報のOrderedDetailテーブルへの追加
            String sql = "INSERT INTO ordered_detail(ordered_id, product_id, total_price, product_quantity) VALUES(?, ?, ?, ?)";

            Map itemMap = cart.getItems();

            Collection<ItemBean> values = itemMap.values();

            for (ItemBean item : values) {
                statement = connection.prepareStatement(sql);
                item.calcTotalPrice();
                // プレースホルダーの設定
                statement.setInt(1, ordered_id);
                statement.setInt(2, item.getId());
                statement.setInt(3, item.getTotalPrice());
                statement.setInt(4, item.getQuantity());

                // SQLの実行
                statement.executeUpdate();
                resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
                if (resultSet != null && resultSet.next()) {
                    lastOrderedDetailId = resultSet.getInt("LAST");
                }
            }

            statement.close();
            return lastOrderedDetailId;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("レコードの操作に失敗しました。");
        } finally {
            try {
                // リソースの開放
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                close();
            } catch (Exception e) {
                throw new DAOException("リソースの開放に失敗しました。");
            }
        }
    }

    private void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

}
