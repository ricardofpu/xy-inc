package br.com.xy.inc.domain;

import br.com.xy.inc.domain.repository.IRepository;
import br.com.xy.inc.global.exception.BusinessException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintViolationException;

import static org.mockito.BDDMockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PoiDomainTest {

    private IRepository repository = mock(IRepository.class);

    @Test
    public void save() {
        when(repository.save(any())).thenReturn(1);

        Poi poi = create();

        poi.create(repository);
        Mockito.verify(repository).save(poi);
    }

    @Test(expected = ConstraintViolationException.class)
    public void failSaveWhenNameIsNull() {
        Poi poi = create();
        poi.setName(new Name(null));
    }

    @Test(expected = ConstraintViolationException.class)
    public void failSaveWhenCoordinateXIsNull() {
        Poi poi = create();
        poi.setCoordinateX(new Coordinate(null));
    }

    @Test(expected = ConstraintViolationException.class)
    public void failSaveWhenCoordinateYIsNull() {
        Poi poi = create();
        poi.setCoordinateY(new Coordinate(null));
    }

    @Test(expected = BusinessException.class)
    public void failSaveWhenExistsId() {
        when(repository.find(any())).thenReturn(create());

        Poi poi = create();

        poi.create(repository);
    }

    @Test
    public void update() {
        when(repository.update(any())).thenReturn(1);

        Poi poi = create();

        poi.update(new Name("Lanchonete"), new Coordinate(35), new Coordinate(30), repository);
        Mockito.verify(repository).update(poi);
        Assert.assertEquals("Lanchonete", poi.getName().getValue());
        Assert.assertEquals(35, poi.getCoordinateX().getValue().intValue());
        Assert.assertEquals(30, poi.getCoordinateY().getValue().intValue());
    }

    @Test(expected = ConstraintViolationException.class)
    public void failUpdateWhenNameIsNull() {
        Poi poi = create();

        poi.update(new Name(null), new Coordinate(35), new Coordinate(30), repository);
    }

    @Test(expected = ConstraintViolationException.class)
    public void failUpdateWhenCoordinateXIsNull() {
        Poi poi = create();

        poi.update(new Name("Lanchonete"), new Coordinate(null), new Coordinate(30), repository);
    }

    @Test(expected = ConstraintViolationException.class)
    public void failUpdateWhenCoordinateYIsNull() {
        Poi poi = create();
        poi.setCoordinateY(null);

        poi.update(new Name("Lanchonete"), new Coordinate(30), new Coordinate(null), repository);
    }

    @Test
    public void delete() {
        when(repository.delete(any())).thenReturn(1);

        Poi poi = create();

        poi.delete(repository);
        Mockito.verify(repository).delete(poi.getId());
    }

    @Test(expected = BusinessException.class)
    public void failDelete() {
        when(repository.delete(any())).thenReturn(0);

        Poi poi = create();

        poi.delete(repository);
    }

    private Poi create() {
        return new Poi(
                new Name("Pizzaria"),
                new Coordinate(30),
                new Coordinate(20)
        );
    }
}
