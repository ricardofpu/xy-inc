package br.com.xyinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.xyinc.entity.Poi;

@Repository
public interface IPoiRepository extends JpaRepository<Poi, Integer> {

}
