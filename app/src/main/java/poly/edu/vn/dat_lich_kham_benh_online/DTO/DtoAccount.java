package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoAccount {
    private int id;
    private String userName;
    private String passWord;
    private String phone;
    private String fullName;
    private String gender;
    private String role;
    private String img;

    public static final String nameTable = "tbAccount";
    public static final String colUserName = "userName";
    public static final String colPassWord = "passWord";
    public static final String colPhone = "phone";
    public static final String colFullName = "fullName";
    public static final String colGender = "gender";
    public static final String colRole = "role";
    public static final String colImg = "img";

    public DtoAccount(int id, String userName, String passWord, String phone, String fullName, String gender, String role, String img) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.fullName = fullName;
        this.gender = gender;
        this.role = role;
        this.img = img;
    }

    public DtoAccount(String userName, String phone, String fullName, String img) {
        this.userName = userName;
        this.phone = phone;
        this.fullName = fullName;
        this.img = img;
    }

    public DtoAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
