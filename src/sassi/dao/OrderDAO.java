package sassi.dao;

import sassi.bean.CartBean;
import sassi.bean.CustomerBean;
import sassi.bean.ItemBean;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
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
                // プレースホルダーの設定
                statement.setInt(1, ordered_id);
                statement.setInt(2, item.getId());
                statement.setInt(3, cart.getTotalPrice()); // TODO ItemBeanが出来次第修正予定
                statement.setInt(4, cart.getCartNum()); // TODO ItemBeanが出来次第修正予定

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

// test
//class TestOrderDAO {
//    public static void main(String[] args) throws DAOException {
//        OrderDAO dao = new OrderDAO();
//        Map itemMap = new HashMap<Integer, ItemBean>();
//        ItemBean item1 = new ItemBean(1, "インターステラー", 1200, "マシュー・マコノヒー", "クリストファーノーラン", "めっちゃいい", Timestamp.valueOf("2019-05-24 09:43:14"), Timestamp.valueOf("2019-05-24 09:43:14"));
//        ItemBean item2 = new ItemBean(2, "インターステラー2", 9999, "マシュー・マコノヒー2", "クリストファーノーラン2", "めっちゃいい2", Timestamp.valueOf("2019-05-24 09:43:14"), Timestamp.valueOf("2019-05-24 09:43:14"));
//        itemMap.put(1, item1);
//        itemMap.put(2, item2);
//        CartBean cart = new CartBean();
//        cart.setItems(itemMap);
////        cart.setCartNum(1);
////        cart.setTotalPrice(1000);
//        CustomerBean customer = new CustomerBean("first", "111-1111", "八王子市", "111-1111-1111");
//        dao.saveOrdered(customer, cart);
//    }
//}
