package br.com.xy.inc.application.handler;

import br.com.xy.inc.application.commands.Commands;
import br.com.xy.inc.domain.Coordinate;
import br.com.xy.inc.domain.Id;
import br.com.xy.inc.domain.Name;
import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.domain.repository.IRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class PoiCommandHandlerTest {

    private IRepository repository = mock(IRepository.class);

    @Autowired
    PoiCommandHandler commandHandler = new PoiCommandHandler(repository);

    @Test
    public void findAll() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        Commands.GetAllPoi command = new Commands.GetAllPoi();

        List<Poi> result = commandHandler.handler(command);
        Assert.assertNotNull(result);
        verify(repository).findAll();
    }

    @Test
    public void findAllWithOneItem() {
        when(repository.findAll()).thenReturn(Collections.singletonList(create()));

        Commands.GetAllPoi command = new Commands.GetAllPoi();

        List<Poi> result = commandHandler.handler(command);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        verify(repository).findAll();
    }

    @Test
    public void getPoi() {
        Poi poi = create();
        when(repository.find(any())).thenReturn(create());

        Commands.GetPoi command = new Commands.GetPoi(poi.getId());

        Poi result = commandHandler.handler(command);
        Assert.assertNotNull(result);
        verify(repository).find(any());
    }

    @Test
    public void createPoi() {
        when(repository.save(any())).thenReturn(1);

        Commands.CreatePoi command = createPoiCommand();

        Poi result = commandHandler.handler(command);
        Assert.assertNotNull(result);
        verify(repository).save(any());
    }

    @Test
    public void updatePoi() {
        Poi poi = create();
        when(repository.update(any())).thenReturn(1);
        when(repository.find(any())).thenReturn(poi);

        Commands.UpdatePoi command = updatePoiCommand(poi.getId());

        Poi result = commandHandler.handler(command);
        verify(repository).update(any());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId().getValue(), command.getId().getValue());
        Assert.assertEquals(result.getName().getValue(), command.getName().getValue());
        Assert.assertEquals(result.getCoordinateX().getValue(), command.getCoordinateX().getValue());
        Assert.assertEquals(result.getCoordinateY().getValue(), command.getCoordinateY().getValue());
    }

    @Test
    public void deletePoi() {
        Poi poi = create();
        when(repository.delete(any())).thenReturn(1);
        when(repository.find(any())).thenReturn(poi);

        Commands.DeletePoi command = deletePoiCommand(poi.getId());

        commandHandler.handler(command);
        verify(repository).delete(any());
    }

    private Poi create() {
        return new Poi(
                new Name("Pizzaria"),
                new Coordinate(30),
                new Coordinate(20)
        );
    }

    private Commands.CreatePoi createPoiCommand() {
        return new Commands.CreatePoi(
                new Name("Lanchonete"),
                new Coordinate(20),
                new Coordinate(15)
        );
    }

    private Commands.UpdatePoi updatePoiCommand(Id id) {
        return new Commands.UpdatePoi(
                id,
                new Name("Lanchonete Updated"),
                new Coordinate(40),
                new Coordinate(20)
        );
    }

    private Commands.DeletePoi deletePoiCommand(Id id) {
        return new Commands.DeletePoi(id);
    }
}
