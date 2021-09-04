package model;

public class Contact {

    private String name;
    private String number;
    private String address;
    private boolean isBestFriend;

    public Contact(String name, String number, String address, boolean isBestFriend) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.isBestFriend = isBestFriend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Contact [address=" + address + ", isBestFriend=" + isBestFriend + ", name=" + name + ", number="
                + number + "]";
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBestFriend() {
        return isBestFriend;
    }

    public void setBestFriend(boolean isBestFriend) {
        this.isBestFriend = isBestFriend;
    }

}
