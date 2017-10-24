package br.com.xy.inc.domain;

import br.com.xy.inc.domain.repository.IRepository;
import br.com.xy.inc.global.exception.BusinessException;
import br.com.xy.inc.infrastructure.errors.PoiErrors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Poi {


    private @Valid @NotNull Id id;
    private @Valid @NotNull Name name;
    private @Valid @NotNull Coordinate coordinateX;
    private @Valid @NotNull Coordinate coordinateY;

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

    public void create(IRepository repository) {
        this.validatePoi(repository);
        repository.save(this);
    }

    public void update(Name name, Coordinate coordinateX, Coordinate coordinateY, IRepository repository) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        repository.update(this);
    }

    public void delete(IRepository repository) {
        Integer updated = repository.delete(this.id);
        if (updated != 1) {
            throw new BusinessException(PoiErrors.PoiErrorCode.getDeleteNotAllowed());
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

    private void validatePoi(IRepository repository) {
        if (repository.find(id) != null)
            throw new BusinessException(PoiErrors.PoiErrorCode.getExistsPoiId());
    }

}
