package br.com.xyinc.controller;

import br.com.xyinc.command.handler.PoiCommandHandler;
import br.com.xyinc.command.handler.commands.Commands;
import br.com.xyinc.domain.Id;
import br.com.xyinc.domain.Poi;
import br.com.xyinc.utils.CreatePoiRequest;
import br.com.xyinc.utils.PoiRepresentation;
import br.com.xyinc.utils.SearchPoiRequest;
import br.com.xyinc.utils.UpdatePoiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/poi")
public class PoiController {

    @Autowired
    private PoiCommandHandler commandHandler;

    @GetMapping
    public ResponseEntity getAll() {
        Commands.GetAllPoi command = new Commands.GetAllPoi();
        List<Poi> result = commandHandler.handler(command);
        return new ResponseEntity(result.stream().map(p -> p.toRepresentation()).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        Commands.GetPoi command = new Commands.GetPoi(new Id(id));

        return Optional.ofNullable(commandHandler.handler(command))
                .map(result -> new ResponseEntity(result.toRepresentation(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createPoi(@RequestBody CreatePoiRequest request) {
        Commands.CreatePoi command = request.toCommand();
        Poi poi = commandHandler.handler(command);
        return new ResponseEntity(poi.toRepresentation(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/search")
    public ResponseEntity search(@RequestBody @Valid SearchPoiRequest request) {
        Commands.SearchPoi command = request.toCommand();
        List<Poi> pois = commandHandler.handler(command);
        return new ResponseEntity(pois, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PoiRepresentation> updatePoi(@PathVariable("id") String id, @RequestBody UpdatePoiRequest request) {
        Commands.UpdatePoi command = request.toCommand(id);
        Poi poi = commandHandler.handler(command);
        return new ResponseEntity(poi.toRepresentation(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        Commands.DeletePoi command = new Commands.DeletePoi(new Id(id));
        commandHandler.handler(command);
        return new ResponseEntity(HttpStatus.OK);
    }

}
