package br.com.xyinc.command.handler;

import br.com.xyinc.command.handler.commands.Commands;
import br.com.xyinc.domain.Id;
import br.com.xyinc.domain.Poi;
import br.com.xyinc.domain.repository.IRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PoiCommandHandler {

    private Logger LOG = LogManager.getLogger(this.getClass());

    @Autowired
    private IRepository repository;


    public List<Poi> handler(Commands.GetAllPoi command) {
        LOG.debug("Received command to find all poi's: {}", command);
        return repository.findAll();
    }

    public Poi handler(Commands.GetPoi command) {
        LOG.debug("Received command to find a poi in data base with id: {}", command.getId().getValue());

        Poi poi = getPoi(command.getId());

        LOG.debug("Poi found: [{}]", poi);
        return poi;
    }

    public Poi handler(Commands.CreatePoi command) {
        LOG.debug("Received command to create a poi in data base with values: [{}]", command);

        Poi poi = new Poi(command.getName(), command.getCoordinateX(), command.getCoordinateY());

        poi.save(repository);

        LOG.debug("Created poi in data base with id: {}", poi.getId().getValue());
        return poi;
    }

    public Poi handler(Commands.UpdatePoi command) {
        LOG.debug("Received command to update a poi in data base with values: [{}]", command);

        Poi poi = getPoi(command.getId());

        poi.update(command, repository);

        LOG.debug("Poi updated in data base with id: {}", poi.getId().getValue());
        return poi;
    }

    public void handler(Commands.DeletePoi command) {
        LOG.debug("Received command to delete a poi in data base with id: {}", command.getId().getValue());

        Poi poi = getPoi(command.getId());

        poi.delete(repository);

        LOG.debug("Poi deleted in data base with id: {}", poi.getId().getValue());

    }

    public List<Poi> handler(Commands.SearchPoi command) {
        return null;
    }

    private Poi getPoi(Id id) {
        return Optional.ofNullable(repository.find(id)).orElse(null);
    }

}
