package br.com.xy.inc.jdbc.repository.config;

import br.com.xy.inc.domain.Coordinate;
import br.com.xy.inc.domain.Name;
import br.com.xy.inc.domain.Poi;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RepositoryTestConfig.class)
public abstract class RepositoryBaseTest {

    public Poi create() {
        return new Poi(
                new Name("Pizzaria"),
                new Coordinate(10),
                new Coordinate(5)
        );
    }
}
