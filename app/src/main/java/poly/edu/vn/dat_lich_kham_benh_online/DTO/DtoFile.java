package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoFile {
    private int id;
    private int user_id;
    private String birthday;
    private String cccd;
    private String country;
    private String bhyt;
    private String job;
    private String email;
    private String address;

    public static final String nameTable = "tbFile";
    public static final String colUserId = "user_id";
    public static final String colBirthday= "birthday";
    public static final String colCountry = "country";
    public static final String colCccd = "cccd";
    public static final String colBhyt = "bhyt";
    public static final String colJob = "job";
    public static final String colEmail = "email";
    public static final String colAddress = "address";

    public DtoFile() {
    }

    public DtoFile(int id, int user_id, String birthday, String cccd, String country, String bhyt, String job, String email, String address) {
        this.id = id;
        this.user_id = user_id;
        this.birthday = birthday;
        this.cccd = cccd;
        this.country = country;
        this.bhyt = bhyt;
        this.job = job;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBhyt() {
        return bhyt;
    }

    public void setBhyt(String bhyt) {
        this.bhyt = bhyt;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DtoFile{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", birthday='" + birthday + '\'' +
                ", cccd='" + cccd + '\'' +
                ", country='" + country + '\'' +
                ", bhyt='" + bhyt + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
