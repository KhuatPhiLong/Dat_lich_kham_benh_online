package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoTimeWork {
    private int id;
    private String session;

    public static final String nameTable = "tbTimeWork";
    public static final String colId = "id";
    public static final String colSession = "session";

    public DtoTimeWork() {
    }

    public DtoTimeWork(int id, String session) {
        this.id = id;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
