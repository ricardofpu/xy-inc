package br.com.xyinc.utils;

import br.com.xyinc.command.handler.commands.Commands;
import br.com.xyinc.domain.Coordinate;
import br.com.xyinc.domain.Name;

public class CreatePoiRequest {

    private String name;
    private Integer coordinateX;
    private Integer coordinateY;

    public CreatePoiRequest(String name, Integer coordinateX, Integer coordinateY) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Commands.CreatePoi toCommand() {
        return new Commands.CreatePoi(new Name(this.name), new Coordinate(this.coordinateX), new Coordinate(this.coordinateY));
    }
}
