package br.com.xy.inc.domain;

import br.com.xy.inc.domain.repository.IRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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

    @Test(expected = RuntimeException.class)
    public void failSaveWhenNameIsNull() {
        Poi poi = create();
        poi.setName(null);

        poi.create(repository);
    }

    @Test(expected = RuntimeException.class)
    public void failSaveWhenCoordinateXIsNull() {
        Poi poi = create();
        poi.setCoordinateX(null);

        poi.create(repository);
    }

    @Test(expected = RuntimeException.class)
    public void failSaveWhenCoordinateYIsNull() {
        Poi poi = create();
        poi.setCoordinateY(null);

        poi.create(repository);
    }

    @Test(expected = RuntimeException.class)
    public void failSaveWhenExistsId() {
        when(repository.find(any())).thenReturn(create());

        Poi poi = create();

        poi.create(repository);
    }

    @Test
    public void update() {
        when(repository.save(any())).thenReturn(1);
        when(repository.update(any())).thenReturn(1);

        Poi poi = create();

        poi.update(new Name("Lanchonete"), new Coordinate(35), new Coordinate(30), repository);
        Mockito.verify(repository).update(poi);
        Assert.assertEquals(poi.getName().getValue(), "Lanchonete");
        Assert.assertEquals(poi.getCoordinateX().getValue().intValue(), 35);
        Assert.assertEquals(poi.getCoordinateY().getValue().intValue(), 30);
    }

    @Test(expected = RuntimeException.class)
    public void failUpdateWhenNameIsNull() {
        Poi poi = create();

        poi.update(new Name(null), new Coordinate(35), new Coordinate(30), repository);
    }

    @Test(expected = RuntimeException.class)
    public void failUpdateWhenCoordinateXIsNull() {
        Poi poi = create();

        poi.update(new Name("Lanchonete"), new Coordinate(null), new Coordinate(30), repository);
    }

    @Test(expected = RuntimeException.class)
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

    @Test(expected = RuntimeException.class)
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
