package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoRoom {
    private int id;
    private String name;
    private String location;

    public static final String nameTable = "tbRooms";
    public static final String colName = "name";
    public static final String colLocation = "location";

    public DtoRoom() {
    }

    public DtoRoom(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
