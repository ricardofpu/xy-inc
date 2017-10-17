package br.com.xyinc.command.handler.commands;

import br.com.xyinc.domain.Coordinate;
import br.com.xyinc.domain.Id;
import br.com.xyinc.domain.Name;

public class Commands {

    public static class GetAllPoi {
    }

    public static class GetPoi {

        protected Id id;

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

        protected Name name;
        protected Coordinate coordinateX;
        protected Coordinate coordinateY;

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

    public static class UpdatePoi {

        protected Id id;
        protected Name name;
        protected Coordinate coordinateX;
        protected Coordinate coordinateY;

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
}
