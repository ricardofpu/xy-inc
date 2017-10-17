package br.com.xyinc.jdbc.repository.extractor;

import br.com.xyinc.domain.Coordinate;
import br.com.xyinc.domain.Id;
import br.com.xyinc.domain.Name;
import br.com.xyinc.domain.Poi;
import br.com.xyinc.jdbc.repository.JdbcPoiRepository;
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
