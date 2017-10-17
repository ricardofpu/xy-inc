package br.com.xy.inc.domain;

import br.com.xy.inc.domain.repository.IRepository;

public class Poi {

    private Id id;

    private Name name;

    private Coordinate coordinateX;

    private Coordinate coordinateY;

    public Poi() {
    }

    public Poi(Name name, Coordinate coordinateX, Coordinate coordinateY) {
        super();
        this.id = new Id();
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Poi(Id id, Name name, Coordinate coordinateX, Coordinate coordinateY) {
        super();
        this.id = id;
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Coordinate getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Coordinate coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Coordinate getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Coordinate coordinateY) {
        this.coordinateY = coordinateY;
    }


    public boolean isEmpty() {
        return this.name == null || this.coordinateX == null || this.coordinateY == null;
    }

    public boolean isValid() {
        return this.getCoordinateX().getValue() >= 0 && this.getCoordinateY().getValue() >= 0;
    }

    public void save(IRepository repository) {
        repository.save(this);
    }

    public void update(Name name, Coordinate coordinateX, Coordinate coordinateY, IRepository repository) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;

        repository.update(this);
    }

    public void delete(IRepository repository) {
    }


}
