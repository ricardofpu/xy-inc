package br.com.xyinc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.xyinc.entity.Poi;

@Repository
public interface IPoiRepository extends JpaRepository<Poi, Integer> {

	Poi findById(Integer id);

	@Query("SELECT p.nome from Poi p "
			+ "WHERE sqrt( pow((p.coordenadaX - :x), 2) + pow((p.coordenadaY - :y), 2)) <= :dMax")
	List<Poi> findByBetweenCoordinate(@Param("x") Integer x, @Param("y") Integer y, @Param("dMax") Double distance);

}
