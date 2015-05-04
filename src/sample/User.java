package sample;

/**
 * Created by Nameless on 4/24/2015.
 */
public class User {
    User(){};

    public User(int id, String info, String phone) {
        this.id = id;
        this.info = info;
        this.phone = phone;
    }

    private String info;
    private String phone;
    private int id;

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (phone != user.phone) return false;
        return !(info != null ? !info.equals(user.info) : user.info != null);

    }
}
