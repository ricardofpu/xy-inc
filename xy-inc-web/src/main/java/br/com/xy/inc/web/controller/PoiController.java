package br.com.xy.inc.web.controller;

import br.com.xy.inc.application.handler.PoiCommandHandler;
import br.com.xy.inc.domain.Id;
import br.com.xy.inc.domain.Poi;
import br.com.xy.inc.web.representation.PoiRepresentation;
import br.com.xy.inc.web.request.CreatePoiRequest;
import br.com.xy.inc.web.request.UpdatePoiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.xy.inc.application.commands.Commands.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/poi")
public class PoiController {

    @Autowired
    public PoiCommandHandler commandHandler;

    @GetMapping
    public ResponseEntity<List<PoiRepresentation>> getAll() {
        GetAllPoi command = new GetAllPoi();
        List<Poi> result = commandHandler.handler(command);
        return new ResponseEntity<>(toRepresentation(result), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoiRepresentation> get(@PathVariable("id") String id) {
        GetPoi command = new GetPoi(new Id(id));

        return Optional.ofNullable(commandHandler.handler(command))
                .map(result -> new ResponseEntity<>(toRepresentation(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PoiRepresentation> createPoi(@RequestBody @Valid CreatePoiRequest request) {
        CreatePoi command = request.toCommand();
        Poi poi = commandHandler.handler(command);
        return new ResponseEntity<>(toRepresentation(poi), HttpStatus.CREATED);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<PoiRepresentation>> search(@RequestParam(value = "coordinateX") String coordinateX,
                                                          @RequestParam(value = "coordinateY") String coordinateY,
                                                          @RequestParam(value = "dMax") String dMax) {
        SearchPoi command = new SearchPoi(Integer.parseInt(coordinateX), Integer.parseInt(coordinateY),
                Double.parseDouble(dMax));
        List<Poi> pois = commandHandler.handler(command);
        return new ResponseEntity<>(toRepresentation(pois), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PoiRepresentation> updatePoi(@PathVariable("id") String id, @RequestBody @Valid UpdatePoiRequest request) {
        UpdatePoi command = request.toCommand(id);
        Poi poi = commandHandler.handler(command);
        return new ResponseEntity<>(toRepresentation(poi), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        DeletePoi command = new DeletePoi(new Id(id));
        commandHandler.handler(command);
        return new ResponseEntity(HttpStatus.OK);
    }

    private PoiRepresentation toRepresentation(Poi poi) {
        return new PoiRepresentation(poi.getId().getValue(), poi.getName().getValue(), poi.getCoordinateX().getValue(), poi.getCoordinateY().getValue());
    }

    private List<PoiRepresentation> toRepresentation(List<Poi> pois) {
        return pois.stream().map(this::toRepresentation).collect(Collectors.toList());
    }

}
