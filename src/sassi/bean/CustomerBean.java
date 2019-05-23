package sassi.bean;

public class CustomerBean {
    private String name;
    private String postal;
    private String address;
    private String phone;

    public CustomerBean(String name, String postal, String address, String phone) {
        this.name = name;
        this.postal = postal;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPostal() {
        return postal;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
