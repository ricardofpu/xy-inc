package br.com.xy.inc.jdbc.repository;

import br.com.xy.inc.domain.Coordinate;
import br.com.xy.inc.domain.Name;
import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.domain.repository.IRepository;
import br.com.xy.inc.jdbc.repository.config.RepositoryBaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class JdbcPoiRepositoryTest extends RepositoryBaseTest {

    @Autowired
    IRepository repository;

    @Test
    public void createPoi() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(1, saved.intValue());
    }

    @Test
    public void findPoi() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(1, saved.intValue());

        Poi selected = repository.find(poi.getId());
        Assert.assertEquals(poi.getId().getValue(), selected.getId().getValue());
        Assert.assertEquals(poi.getName().getValue(), selected.getName().getValue());
        Assert.assertEquals(poi.getCoordinateX().getValue(), selected.getCoordinateX().getValue());
        Assert.assertEquals(poi.getCoordinateY().getValue(), selected.getCoordinateY().getValue());
    }

    @Test
    public void findAll() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(1, saved.intValue());

        List<Poi> list = repository.findAll();
        Assert.assertNotNull(list);

        Poi selected = list.stream().filter(p -> Objects.equals(p.getId().getValue(), poi.getId().getValue())).findFirst().get();
        Assert.assertEquals(poi.getId().getValue(), selected.getId().getValue());
        Assert.assertEquals(poi.getName().getValue(), selected.getName().getValue());
        Assert.assertEquals(poi.getCoordinateX().getValue(), selected.getCoordinateX().getValue());
        Assert.assertEquals(poi.getCoordinateY().getValue(), selected.getCoordinateY().getValue());
    }

    @Test
    public void updatePoi() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(1, saved.intValue());

        poi.setName(new Name("Lunch Updated"));
        poi.setCoordinateX(new Coordinate(30));
        poi.setCoordinateY(new Coordinate(25));

        Integer updated = repository.update(poi);
        Assert.assertEquals(1, updated.intValue());

        Poi selected = repository.find(poi.getId());
        Assert.assertEquals(poi.getId().getValue(), selected.getId().getValue());
        Assert.assertEquals(poi.getName().getValue(), selected.getName().getValue());
        Assert.assertEquals(poi.getCoordinateX().getValue(), selected.getCoordinateX().getValue());
        Assert.assertEquals(poi.getCoordinateY().getValue(), selected.getCoordinateY().getValue());

    }

    @Test
    public void deletePoi() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(1, saved.intValue());

        Integer deleted = repository.delete(poi.getId());
        Assert.assertEquals(1, deleted.intValue());

        Poi selected = repository.find(poi.getId());
        Assert.assertNull(selected);
    }
}
