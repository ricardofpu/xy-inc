package br.com.xy.inc.web.request;

import br.com.xy.inc.application.commands.Commands;
import br.com.xy.inc.domain.Coordinate;
import br.com.xy.inc.domain.Id;
import br.com.xy.inc.domain.Name;

public class UpdatePoiRequest {

    private String name;
    private Integer coordinateX;
    private Integer coordinateY;

    public UpdatePoiRequest(){}

    public UpdatePoiRequest(String name, Integer coordinateX, Integer coordinateY) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
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

    public Commands.UpdatePoi toCommand(String id) {
        return new Commands.UpdatePoi(
                new Id(id),
                new Name(this.name),
                new Coordinate(this.coordinateX),
                new Coordinate(this.coordinateY)
        );
    }
}
