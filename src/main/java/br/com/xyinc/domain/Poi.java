package br.com.xyinc.domain;

import br.com.xyinc.command.handler.commands.Commands;
import br.com.xyinc.domain.repository.IRepository;
import br.com.xyinc.utils.PoiRepresentation;

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


    public boolean isEmpty() {
        if (this.name == null || this.coordinateX == null || this.coordinateY == null) {
            return true;
        }
        return false;
    }

    public boolean isValid() {
        if (this.getCoordinateX().getValue() >= 0 && this.getCoordinateY().getValue() >= 0) {
            return true;
        }
        return false;
    }

    public PoiRepresentation toRepresentation() {
        return new PoiRepresentation(this.id.getValue(), this.name.getValue(), this.coordinateX.getValue(), this.coordinateY.getValue());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Poi [id=").append(id.getValue()).append(", name=").append(name.getValue()).append(", coordinateX=")
                .append(coordinateX.getValue()).append(", coordinateY=").append(coordinateY.getValue()).append("]");
        return builder.toString();
    }

    public void save(IRepository repository) {
        repository.save(this);
    }

    public void update(Commands.UpdatePoi command, IRepository repository) {
        this.name = command.getName();
        this.coordinateX = command.getCoordinateX();
        this.coordinateY = command.getCoordinateY();

        repository.update(this);
    }

    public void delete(IRepository repository) {
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
}
