package br.com.xy.inc.web.representation;

public class PoiRepresentation {

    private String id;
    private String name;
    private Integer coordinateX;
    private Integer coordinateY;

    public PoiRepresentation() {
    }

    public PoiRepresentation(String id, String name, Integer coordinateX, Integer coordinateY) {
        this.id = id;
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Integer coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Integer getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Integer coordinateY) {
        this.coordinateY = coordinateY;
    }

}
