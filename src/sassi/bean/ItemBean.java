package sassi.bean;


import sassi.dao.DAOException;
import sassi.dao.ItemDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemBean {
    private Integer id;
    private String title;
    private Integer price;
    private String players;
    private String directors;
    private String description;
    private Timestamp updated;
    private Timestamp created;
    private Map<String, String> validation;
    private Integer quantity;
    private Integer totalPrice;

    public ArrayList<Integer> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Integer> pages) {
        this.pages = pages;
    }

    private ArrayList<Integer> pages;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void calcTotalPrice() {
        this.totalPrice = quantity * price;
    }

    public ItemBean() {
    }

    public ItemBean(ResultSet rs) throws SQLException {
        this.id = rs.getInt("product_id");
        this.title = rs.getString("product_title");
        this.price = rs.getInt("product_price");
        this.players = rs.getString("cast_name_list");
        this.directors = rs.getString("director_name");
        this.description = rs.getString("description");
        this.updated = rs.getTimestamp("updated_date");
        this.created = rs.getTimestamp("created_date");

    }

    public ItemBean(int id, String title, int price, String players, String directors, String description, Timestamp updated, Timestamp created) throws DAOException {
        this.id = id;
        this.title = title;
        this.price = price;
        this.players = players;
        this.directors = directors;
        this.description = description;
        this.updated = updated;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPlayers() {
        return players;
    }

    public String getDirectors() {
        return directors;
    }

    public String getDescription() { return description; }

    public Timestamp getUpdated() {
        return updated;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public void setDescription(String description) { this.description = description; }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}