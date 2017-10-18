package br.com.xy.inc.jdbc.repository;


import br.com.xy.inc.domain.Id;
import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.domain.repository.IRepository;
import br.com.xy.inc.jdbc.repository.extractor.PoiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPoiRepository implements IRepository {

    public static final String TABLE_NAME = "poi";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String COORDINATE_X_COLUMN = "coordinate_x";
    public static final String COORDINATE_Y_COLUMN = "coordinate_y";
    public static final String CREATED_AT_COLUMN = "created_at";
    public static final String UPDATED_AT_COLUMN = "updated_at";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer save(Poi poi) {
        String sql = String.format("insert into %s (%s, %s, %s, %s, %s) values (?, ?, ?, ?, now())",
                TABLE_NAME, ID_COLUMN, NAME_COLUMN, COORDINATE_X_COLUMN, COORDINATE_Y_COLUMN, CREATED_AT_COLUMN);

        return jdbcTemplate.update(sql, poi.getId().getValue(), poi.getName().getValue(),
                poi.getCoordinateX().getValue(), poi.getCoordinateY().getValue());
    }

    @Override
    public Integer update(Poi poi) {
        String sql = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?",
                TABLE_NAME, NAME_COLUMN, COORDINATE_X_COLUMN, COORDINATE_Y_COLUMN, ID_COLUMN);

        return jdbcTemplate.update(sql, poi.getName().getValue(),
                poi.getCoordinateX().getValue(), poi.getCoordinateY().getValue(), poi.getId().getValue());

    }

    @Override
    public Poi find(Id id) {
        String sql = String.format("select * from %s where %s = ?", TABLE_NAME, ID_COLUMN);

        Poi poi = null;
        try {
            poi = jdbcTemplate.queryForObject(sql, new PoiMapper(), id.getValue());
        } catch (EmptyResultDataAccessException ignored) {
        }

        return poi;
    }

    @Override
    public List<Poi> findAll() {
        String sql = String.format("select * from %s order by %s asc", TABLE_NAME, NAME_COLUMN);

        return jdbcTemplate.query(sql, new PoiMapper());
    }

    @Override
    public Integer delete(Id id) {
        String sql = String.format("delete from %s where %s = ?", TABLE_NAME, ID_COLUMN);

        return jdbcTemplate.update(sql, id.getValue());
    }

    @Override
    public List<Poi> findByBetweenCoordinate(Integer x, Integer y, Double dMax) {
        String sql = String.format("select * from %s p WHERE sqrt( pow((p.%s - ?), 2) + pow((p.%s - ?), 2)) <= ?", TABLE_NAME, COORDINATE_X_COLUMN, COORDINATE_Y_COLUMN);

        return jdbcTemplate.query(sql, new PoiMapper(), x, y, dMax);
    }
}
