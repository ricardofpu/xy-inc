package br.com.xyinc.domain.repository;

import br.com.xyinc.domain.Id;
import br.com.xyinc.domain.Poi;

import java.util.List;

public interface IRepository {

    Integer save(Poi poi);

    Integer update(Poi poi);

    Poi find(Id id);

    List<Poi> findAll();

    Integer delete(Id id);

    List<Poi> findByBetweenCoordinate(Integer x, Integer y, Double dMax);
}
