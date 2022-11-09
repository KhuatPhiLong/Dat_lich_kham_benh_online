package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoTimeWorkDetail {
    private int id;
    private int timework_id;
    private String time;

    public static final String nameTable = "tbTimeWorkDetail";
    public static final String colTimework_id = "timework_id";
    public static final String colTime = "time";

    public DtoTimeWorkDetail() {
    }

    public DtoTimeWorkDetail(int id, int timework_id, String time) {
        this.id = id;
        this.timework_id = timework_id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimework_id() {
        return timework_id;
    }

    public void setTimework_id(int timework_id) {
        this.timework_id = timework_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
