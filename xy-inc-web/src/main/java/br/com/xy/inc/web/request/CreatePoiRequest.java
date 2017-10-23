package br.com.xy.inc.web.request;

import br.com.xy.inc.application.commands.Commands;
import br.com.xy.inc.domain.Coordinate;
import br.com.xy.inc.domain.Name;
import br.com.xy.inc.web.utils.SystemProperties;

import javax.validation.constraints.NotNull;

public class CreatePoiRequest {

    @NotNull(message = SystemProperties.FIELD_MAY_NOT_BE_NULL)
    private String name;

    @NotNull
    private Integer coordinateX;

    @NotNull
    private Integer coordinateY;

    public CreatePoiRequest() {
    }

    public CreatePoiRequest(String name, Integer coordinateX, Integer coordinateY) {
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

    public Commands.CreatePoi toCommand() {
        return new Commands.CreatePoi(
                new Name(this.name),
                new Coordinate(this.coordinateX),
                new Coordinate(this.coordinateY)
        );
    }
}
