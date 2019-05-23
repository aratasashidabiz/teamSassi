package sassi.dao;

import sassi.bean.CartBean;
import sassi.bean.CustomerBean;
import sassi.bean.ItemBean;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderDAO {
    public static void main(String[] args) throws DAOException {
        OrderDAO dao = new OrderDAO();
        Map itemMap = new HashMap<Integer, ItemBean>();
        ItemBean item1 = new ItemBean(3);
        itemMap.put(1, item1);
        ItemBean item2 = new ItemBean(4);
        itemMap.put(2, item2);

        CartBean cart = new CartBean();
        cart.setItems(itemMap);
        cart.setCartNum(1);
        cart.setTotalPrice(1000);
        CustomerBean customer = new CustomerBean("yea", "123-1223", "渋谷区", "090-0987-8765");
//        ItemBean item = new ItemBean(3);
//        item.setId(4);
        dao.saveOrder(customer, cart);
//        dao.saveOrder(customer, cart, item);
    }

    private Connection connection;

    public OrderDAO() throws DAOException {
        getConnection();
    }

    public int saveOrder(CustomerBean customer, CartBean cart)
            throws DAOException {
        if (connection == null)
            getConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int lastInsertId = 0;

        try {
            // 注文先の顧客情報の追加
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

            // 注文情報のOrderedテーブルへの追加
            sql = "INSERT INTO ordered_detail(ordered_id, product_id, total_price, product_quantity) VALUES(?, ?, ?, ?)";

            Map itemMap = cart.getItems();

            Collection<ItemBean> values = itemMap.values();
            for(ItemBean item: values) { // TODO 修正
                statement = connection.prepareStatement(sql);
                // プレースホルダーの設定
                statemenxt.setInt(1, lastInsertId);
                statement.setInt(2, item.getId());
                statement.setInt(3, cart.getTotalPrice());
                statement.setInt(4, cart.getCartNum());

                // SQLの実行
                statement.executeUpdate();
            }


//            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
//            if (resultSet != null && resultSet.next()) {
//                lastInsertId = resultSet.getInt("LAST");
//            }
            statement.close();

            // 注文明細情報のOrderedDetailテーブルへの追加
            // 商品ごとに複数レコード追加
//            sql = "INSERT INTO ordered_detail(order_code, item_code, num) VALUES(?, ?, ?)";
//            statement = connection.prepareStatement(sql);
//            Map<Integer, ItemBean> items = cart.getItems();
//            Collection<ItemBean> list = items.values();
//            for (ItemBean item : list) {
//                statement.setInt(1, lastInsertId); // XXXXXXX
//                statement.setInt(2, item.getCode());
//                statement.setInt(3, item.getQuantity());
//                statement.executeUpdate();
//            }
//            statement.close();
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
            String pass = "himitu";
            // データベースへの接続
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("接続に失敗しました。");
        }
    }

    private void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}

