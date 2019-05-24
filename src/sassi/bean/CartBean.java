package sassi.bean;

import java.util.Collection;
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

    public void addItem(ItemBean newItem, Integer quantity){
        ItemBean existItem = items.get(newItem.getId()); // ここが不安

        if (existItem == null) {
            newItem.setQuantity(quantity);
            items.put(newItem.getId(), newItem);
        } else { // 書品一覧に戻り、もう一度、カートに入れるボタンが押された時の処理
            existItem.setQuantity(quantity + existItem.getQuantity());
        }
        recalcTotal();
        cartNum += quantity;
    }

    public void changeQuantity(Integer itemId, Integer quantity){

        ItemBean itemBean = items.get(itemId);

        if (itemBean != null) {
            Integer prevQuantity = itemBean.getQuantity();
            itemBean.setQuantity(quantity);
            recalcTotal();
            cartNum += (quantity - prevQuantity);
        }else{
            // TODO: ここでエラー処理したい
        }

    }

    public void deleteCart(Integer itemId){
        ItemBean itemBean = items.get(itemId);
        cartNum -= itemBean.getQuantity();
        items.remove(itemId);
        recalcTotal();
    }

    private void recalcTotal() {
        totalPrice = 0;
        Collection<ItemBean> list = items.values();
        for (ItemBean item : list) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }

}

class Tester{
    public static void main(String[] args) {

        ItemBean itemBean = new ItemBean();
        itemBean.setId(1);
        itemBean.setPrice(100);
        itemBean.setQuantity(2);

        ItemBean itemBean2 = new ItemBean();
        itemBean2.setId(2);
        itemBean2.setPrice(300);
        itemBean2.setQuantity(2);

        CartBean cartBean = new CartBean();

        System.out.println("+++addItem 初期投入");

        cartBean.addItem(itemBean, 3 );
        cartBean.addItem(itemBean2, 3 );

        show(cartBean);


        System.out.println("+++もう一度「カートに入れるボタン」を押す++");

        cartBean.addItem(itemBean, 1 );
        show(cartBean);


        System.out.println("+++changeQuantity keyを間違った場合++");

        cartBean.changeQuantity(3, 1);
        show(cartBean);


        System.out.println("+++changeQuantity 小さい方に修正++");

        cartBean.changeQuantity(1, 1);
        show(cartBean);


        System.out.println("+++changeQuantity 大きい方に修正++");

        cartBean.changeQuantity(1, 5);
        show(cartBean);


        System.out.println("+++deleteCart++");

        cartBean.deleteCart(1);
        show(cartBean);

        System.out.println("+++++");
    }

    static void show(CartBean cartBean){

        Map<Integer, ItemBean> items = cartBean.getItems();
        for (Map.Entry<Integer, ItemBean> integerItemBeanEntry : items.entrySet()) {
            System.out.print(integerItemBeanEntry.getKey());
            System.out.print(" : ");
            System.out.print(integerItemBeanEntry.getValue().getPrice());
            System.out.print(" 、 ");
            System.out.println(integerItemBeanEntry.getValue().getQuantity());
        }
        System.out.println(cartBean.getTotalPrice());
        System.out.println(cartBean.getCartNum());
    }
}
