package poly.edu.vn.dat_lich_kham_benh_online.DTO;

public class DtoService {
    private int id;
    private String name;
    private float price;
    private int categories_id;
    private String img;

    public final static String nameTable = "tbServices";
    public final static String colName = "name";
    public final static String colPrice = "price";
    public final static String colCategories_id = "categories_id";
    public final  static String colImg = "img";

    public DtoService() {
    }

    public DtoService(int id, String name, float price, int categories_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories_id = categories_id;
    }

    public DtoService(int id, String name, float price, int categories_id, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories_id = categories_id;
        this.img = img;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
