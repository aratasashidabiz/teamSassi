package sassi.bean;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

public class ItemBean {
    private Integer id;
    private String title;
    private Integer price;
    private String players;
    private String directors;
    private Timestamp updated;
    private Timestamp created;
    private Map<String, String> validation;

    public ItemBean() {
    }

    public ItemBean(HttpServletRequest req){
        //TODO: リクエストから、データ挿入 by Doi
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

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
