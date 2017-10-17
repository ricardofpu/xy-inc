package br.com.xyinc.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.xyinc.domain.Poi;
import br.com.xyinc.repositoryTwo.IPoiRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PoiRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	private IPoiRepository iPoiRepository;
	
	
	@Test
	public void testFindAll() {
		entityManager.persist(new Poi("Padaria", 30, 15));
		List<Poi> list  = iPoiRepository.findAll();
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testFindById() {
		entityManager.persist(new Poi("Lanchonete", 20, 30));
		Optional<Poi> poi = Optional.ofNullable(iPoiRepository.findById(1));
		assertEquals("Lanchonete", poi.get().getName());
	}

}
