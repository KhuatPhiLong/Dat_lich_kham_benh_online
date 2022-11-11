package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoOrderDoctor {
    private int id;
    private int file_id;
    private int doctor_id;
    private String start_time;
    private String start_date;
    private float total;

    public static final String nameTable ="tbOrderDoctor";
    public static final String colFile_id ="file_id";
    public static final String colDoctor_id ="doctor_id";
    public static final String colStart_time ="start_time";
    public static final String colStart_date= "start_date";
    public static final String colTotal= "total";
    public static final String colId= "id";

    public DtoOrderDoctor() {
    }

    public DtoOrderDoctor(int id, int file_id, int doctor_id, String start_time, String start_date) {
        this.id = id;
        this.file_id = file_id;
        this.doctor_id = doctor_id;
        this.start_time = start_time;
        this.start_date = start_date;
    }

    public DtoOrderDoctor(int id, int file_id, int doctor_id, String start_time, String start_date, float total) {
        this.id = id;
        this.file_id = file_id;
        this.doctor_id = doctor_id;
        this.start_time = start_time;
        this.start_date = start_date;
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "DtoOrderDoctor{" +
                "id=" + id +
                ", file_id=" + file_id +
                ", doctor_id=" + doctor_id +
                ", start_time='" + start_time + '\'' +
                ", start_date='" + start_date + '\'' +
                ", total=" + total +
                '}';
    }
}
