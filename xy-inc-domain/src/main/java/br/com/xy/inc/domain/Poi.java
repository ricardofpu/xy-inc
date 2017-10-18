package br.com.xy.inc.domain;

import br.com.xy.inc.domain.repository.IRepository;

public class Poi {

    private Id id;
    private Name name;
    private Coordinate coordinateX;
    private Coordinate coordinateY;

    public Poi() {}

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

    public void create(IRepository repository) {
        this.validatePoi(repository);

        repository.save(this);
    }

    public void update(Name name, Coordinate coordinateX, Coordinate coordinateY, IRepository repository) {
        this.validateUpdate();

        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;

        repository.update(this);
    }

    public void delete(IRepository repository) {
        Integer updated = repository.delete(this.id);
        if(updated != 1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    private boolean isEmpty() {
        return this.name == null || this.coordinateX == null || this.coordinateY == null;
    }

    private boolean isValid() {
        return this.getCoordinateX().getValue() >= 0 && this.getCoordinateY().getValue() >= 0;
    }


    private void validatePoi(IRepository repository) {
        try {
            if(repository.find(id) != null) throw new Exception();
            if(isEmpty()) throw new Exception();
            if(!isValid()) throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateUpdate() {
        try {
            if(isEmpty()) throw new Exception();
            if(!isValid()) throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
