package br.com.xyinc.command.handler.commands;

import br.com.xyinc.domain.Coordinate;
import br.com.xyinc.domain.Id;
import br.com.xyinc.domain.Name;

public class Commands {

    public static class GetAllPoi {
    }

    public static class GetPoi {

        private Id id;

        public GetPoi(Id id) {
            this.id = id;
        }

        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }
    }

    public static class CreatePoi {

        private Name name;
        private Coordinate coordinateX;
        private Coordinate coordinateY;

        public CreatePoi(Name name, Coordinate coordinateX, Coordinate coordinateY) {
            this.name = name;
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
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

        public Coordinate getCoordinateY() {
            return coordinateY;
        }
    }

    public static class UpdatePoi {

        private Id id;
        private Name name;
        private Coordinate coordinateX;
        private Coordinate coordinateY;

        public UpdatePoi(Id id, Name name, Coordinate coordinateX, Coordinate coordinateY) {
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

        public Coordinate getCoordinateY() {
            return coordinateY;
        }
    }

    public static class DeletePoi {

        private Id id;

        public DeletePoi(Id id) {
            this.id = id;
        }

        public Id getId() {
            return id;
        }

    }

    public static class SearchPoi {

        private Integer coordinateX;
        private Integer coordinateY;
        private Double dMax;

        public SearchPoi(Integer coordinateX, Integer coordinateY, Double dMax) {
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
            this.dMax = dMax;
        }

        public Integer getCoordinateX() {
            return coordinateX;
        }

        public Integer getCoordinateY() {
            return coordinateY;
        }

        public Double getdMax() {
            return dMax;
        }
    }
}
