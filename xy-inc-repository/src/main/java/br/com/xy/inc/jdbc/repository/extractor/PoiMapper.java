package br.com.xy.inc.jdbc.repository.extractor;


import br.com.xy.inc.domain.Coordinate;
import br.com.xy.inc.domain.Id;
import br.com.xy.inc.domain.Name;
import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.jdbc.repository.JdbcPoiRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PoiMapper implements RowMapper<Poi> {

    @Override
    public Poi mapRow(ResultSet rs, int i) throws SQLException {
        String id = rs.getString(JdbcPoiRepository.ID_COLUMN);
        String name = rs.getString(JdbcPoiRepository.NAME_COLUMN);
        Integer coordinateX = rs.getInt(JdbcPoiRepository.COORDINATE_X_COLUMN);
        Integer coordinateY = rs.getInt(JdbcPoiRepository.COORDINATE_Y_COLUMN);

        return new Poi(new Id(id), new Name(name), new Coordinate(coordinateX), new Coordinate(coordinateY));
    }
}
