package br.com.xy.inc.web.request;

import br.com.xy.inc.application.commands.Commands;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

public class SearchPoiRequest {

    @Required
    private Integer coordinateX;
    @Required
    private Integer coordinateY;
    @Required
    private Double dMax;

    public SearchPoiRequest() {
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

    public Double getdMax() {
        return dMax;
    }

    public void setdMax(Double dMax) {
        this.dMax = dMax;
    }

    public Commands.SearchPoi toCommand() {
        return new Commands.SearchPoi(
                this.coordinateX,
                this.coordinateY,
                this.dMax
        );
    }
}
