package br.com.xy.inc.web.controller;

import br.com.xy.inc.application.commands.Commands;
import br.com.xy.inc.application.handler.PoiCommandHandler;
import br.com.xy.inc.domain.Id;
import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.web.representation.PoiRepresentation;
import br.com.xy.inc.web.request.CreatePoiRequest;
import br.com.xy.inc.web.request.SearchPoiRequest;
import br.com.xy.inc.web.request.UpdatePoiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/poi")
public class PoiController {

    @Autowired
    public PoiCommandHandler commandHandler;

    @GetMapping
    public ResponseEntity<List<PoiRepresentation>> getAll() {
        Commands.GetAllPoi command = new Commands.GetAllPoi();
        List<Poi> result = commandHandler.handler(command);
        return new ResponseEntity<>(result.stream().map(this::toRepresentation).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoiRepresentation> get(@PathVariable("id") String id) {
        Commands.GetPoi command = new Commands.GetPoi(new Id(id));

        return Optional.ofNullable(commandHandler.handler(command))
                .map(result -> new ResponseEntity<>(toRepresentation(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PoiRepresentation> createPoi(@RequestBody CreatePoiRequest request) {
        Commands.CreatePoi command = request.toCommand();
        Poi poi = commandHandler.handler(command);
        return new ResponseEntity<>(toRepresentation(poi), HttpStatus.CREATED);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Poi>> search(@RequestBody @Valid SearchPoiRequest request) {
        Commands.SearchPoi command = request.toCommand();
        List<Poi> pois = commandHandler.handler(command);
        return new ResponseEntity<>(pois, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PoiRepresentation> updatePoi(@PathVariable("id") String id, @RequestBody UpdatePoiRequest request) {
        Commands.UpdatePoi command = request.toCommand(id);
        Poi poi = commandHandler.handler(command);
        return new ResponseEntity<>(toRepresentation(poi), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        Commands.DeletePoi command = new Commands.DeletePoi(new Id(id));
        commandHandler.handler(command);
        return new ResponseEntity(HttpStatus.OK);
    }

    private PoiRepresentation toRepresentation(Poi poi) {
        return new PoiRepresentation(poi.getId().getValue(), poi.getName().getValue(), poi.getCoordinateX().getValue(), poi.getCoordinateY().getValue());
    }

}
