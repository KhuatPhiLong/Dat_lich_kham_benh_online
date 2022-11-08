package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoDoctor {
    private int id;
    private int user_id;
    private String birthday;
    private int service_id;
    private int room_id;
    private String description;

    public static final String nameTable = "tbDoctor";
    public static final String colUser_id = "user_id";
    public static final String colBirthday = "birthday";
    public static final String colService_id = "service_id";
    public static final String colRoom_id = "room_id";
    public static final String colDescription = "description";

    public DtoDoctor() {
    }

    public DtoDoctor(int id, int user_id, String birthday, int service_id, int room_id, String description) {
        this.id = id;
        this.user_id = user_id;
        this.birthday = birthday;
        this.service_id = service_id;
        this.room_id = room_id;
        this.description = description;
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

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
