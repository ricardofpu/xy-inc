package br.com.xy.inc.application.handler;

import br.com.xy.inc.domain.Id;
import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.domain.repository.IRepository;
import br.com.xy.inc.global.exception.NotFoundException;
import br.com.xy.inc.global.exception.error.ResourceValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static br.com.xy.inc.application.commands.Commands.*;

@Component
public class PoiCommandHandler {

    private Logger LOG = LogManager.getLogger(this.getClass());

    private final IRepository repository;

    @Autowired
    public PoiCommandHandler(IRepository repository) {
        this.repository = repository;
    }


    public List<Poi> handler(GetAllPoi command) {
        LOG.debug("Received command to find all poi's: {}", command);
        return repository.findAll();
    }

    public Poi handler(GetPoi command) {
        LOG.debug("Received command to find a poi in data base with id: {}", command.getId().getValue());

        Poi poi = getPoi(command.getId());

        LOG.debug("Poi found: [{}]", poi);
        return poi;
    }

    public Poi handler(CreatePoi command) {
        LOG.debug("Received command to create a poi in data base with values: [{}]", command);

        Poi poi = new Poi(command.getName(), command.getCoordinateX(), command.getCoordinateY());

        poi.create(repository);

        LOG.debug("Created poi in data base with id: {}", poi.getId().getValue());
        return poi;
    }

    public Poi handler(UpdatePoi command) {
        LOG.debug("Received command to update a poi in data base with values: [{}]", command);

        Poi poi = getPoi(command.getId());

        poi.update(command.getName(), command.getCoordinateX(), command.getCoordinateY(), repository);

        LOG.debug("Updated poi in data base with id: {}", poi.getId().getValue());
        return poi;
    }

    public void handler(DeletePoi command) {
        LOG.debug("Received command to delete a poi in data base with id: {}", command.getId().getValue());

        Poi poi = getPoi(command.getId());

        poi.delete(repository);

        LOG.debug("Deleted poi in data base with id: {}", poi.getId().getValue());

    }

    public List<Poi> handler(SearchPoi command) {
        LOG.debug("Received command to find poi by between coordinates in data base with values: [{}]", command);

        List<Poi> list = repository.findByBetweenCoordinate(command.getCoordinateX(), command.getCoordinateY(), command.getDMax());

        LOG.debug("Search poi's completed in data base with values: {}", list);
        return list;
    }

    private Poi getPoi(Id id) {
        return Optional.ofNullable(repository.find(id)).orElseThrow(() ->
                new NotFoundException(new ResourceValue(Poi.class, id.getValue())));
    }

}
