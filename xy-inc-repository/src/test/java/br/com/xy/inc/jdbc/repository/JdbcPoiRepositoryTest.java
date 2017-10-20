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
        Assert.assertEquals(saved.intValue(), 1);
    }

    @Test
    public void findPoi() {
        Poi poi  = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(saved.intValue(), 1);

        Poi selected = repository.find(poi.getId());
        Assert.assertEquals(selected.getId().getValue(), poi.getId().getValue());
        Assert.assertEquals(selected.getName().getValue(), poi.getName().getValue());
        Assert.assertEquals(selected.getCoordinateX().getValue(), poi.getCoordinateX().getValue());
        Assert.assertEquals(selected.getCoordinateY().getValue(), poi.getCoordinateY().getValue());
    }

    @Test
    public void findAll() {
        Poi poi  = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(saved.intValue(), 1);

        List<Poi> list = repository.findAll();
        Assert.assertNotNull(list);

        Poi selected = list.stream().filter(p -> Objects.equals(p.getId().getValue(), poi.getId().getValue())).findFirst().get();
        Assert.assertEquals(selected.getId().getValue(), poi.getId().getValue());
        Assert.assertEquals(selected.getName().getValue(), poi.getName().getValue());
        Assert.assertEquals(selected.getCoordinateX().getValue(), poi.getCoordinateX().getValue());
        Assert.assertEquals(selected.getCoordinateY().getValue(), poi.getCoordinateY().getValue());
    }

    @Test
    public void updatePoi() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(saved.intValue(), 1);

        poi.setName(new Name("Lunch Updated"));
        poi.setCoordinateX(new Coordinate(30));
        poi.setCoordinateY(new Coordinate(25));

        Integer updated = repository.update(poi);
        Assert.assertEquals(updated.intValue(), 1);

        Poi selected = repository.find(poi.getId());
        Assert.assertEquals(selected.getId().getValue(), poi.getId().getValue());
        Assert.assertEquals(selected.getName().getValue(), poi.getName().getValue());
        Assert.assertEquals(selected.getCoordinateX().getValue(), poi.getCoordinateX().getValue());
        Assert.assertEquals(selected.getCoordinateY().getValue(), poi.getCoordinateY().getValue());

    }

    @Test
    public void deletePoi() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(saved.intValue(), 1);

        Integer deleted = repository.delete(poi.getId());
        Assert.assertEquals(deleted.intValue(), 1);

        Poi selected = repository.find(poi.getId());
        Assert.assertNull(selected);
    }
}
