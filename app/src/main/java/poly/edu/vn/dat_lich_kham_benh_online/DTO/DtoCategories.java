package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoCategories {
    private int id;
    private String name;

    public static final String nameTable = "tbCategories";
    public static final String colName = "name";

    public DtoCategories() {
    }

    public DtoCategories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
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
}
