package br.com.xy.inc.jdbc.repository;

import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.domain.repository.IRepository;
import br.com.xy.inc.jdbc.repository.config.RepositoryBaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JdbcPoiRepositoryTest extends RepositoryBaseTest {

    @Autowired
    IRepository repository;

    @Test
    public void createPoi() {
        Poi poi = create();

        Integer saved = repository.save(poi);
        Assert.assertEquals(saved.intValue(), 1);
    }
}
