package data_access.model.route;

public class Town {
    public static final String TABLE_NAME = "town";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    private Long id;
    private String name;

    public Town() {

    }

    public Town(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "town[ id=" + this.id + ", name= " + this.name + "]";
    }
}
