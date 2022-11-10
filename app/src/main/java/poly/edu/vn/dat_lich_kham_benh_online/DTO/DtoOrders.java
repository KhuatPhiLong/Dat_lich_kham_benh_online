package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoOrders {
    private int id;
    private int file_id;
    private String order_time;
    private String order_date;

    public static final String nameTable = "tbOrders";
    public static final String colFileId = "file_id";
    public static final String colOrderTime = "order_time";
    public static final String colOrderDate = "order_date";

    public DtoOrders() {
    }

    public DtoOrders(int id, int file_id, String order_time, String order_date) {
        this.id = id;
        this.file_id = file_id;
        this.order_time = order_time;
        this.order_date = order_date;
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

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}
