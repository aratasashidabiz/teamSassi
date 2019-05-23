package sassi.bean;

import java.util.HashMap;
import java.util.Map;

public class CartBean {
    private Map<Integer, ItemBean> items = new HashMap<Integer, ItemBean>();
    private Integer cartNum = 0;
    private Integer totalPrice = 0;

    public CartBean() {
    }

    public CartBean(Map<Integer, ItemBean> items, Integer cartNum, Integer totalPrice) {
        this.items = items;
        this.cartNum = cartNum;
        this.totalPrice = totalPrice;
    }

    public Map<Integer, ItemBean> getItems() {
        return items;
    }

    public Integer getCartNum() {
        return cartNum;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setItems(Map<Integer, ItemBean> items) {
        this.items = items;
    }

    public void setCartNum(Integer cartNum) {
        this.cartNum = cartNum;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
