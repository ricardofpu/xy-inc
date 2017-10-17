package br.com.xyinc.web.rest;

import br.com.xyinc.command.handler.PoiCommandHandler;
import br.com.xyinc.command.handler.commands.Commands;
import br.com.xyinc.domain.Id;
import br.com.xyinc.domain.Poi;
import br.com.xyinc.utils.CreatePoiRequest;
import br.com.xyinc.utils.PoiRepresentation;
import br.com.xyinc.utils.UpdatePoiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RequestMapping("/poi")
public class PoiWebService {

    @Autowired
    private PoiCommandHandler commandHandler;

    private static final Logger log = LoggerFactory.getLogger(PoiWebService.class);

    @GetMapping
    public List<Poi> getAll() {
        Commands.GetAllPoi command = new Commands.GetAllPoi();

        return commandHandler.handler(command);
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
        return new ResponseEntity(poi.toRepresentation(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/search", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<List<Poi>> search(@RequestParam(value = "x") Integer x,
//                                            @RequestParam(value = "y") Integer y,
//                                            @RequestParam(value = "dMax") Double dMax) {
//        return searchPois(x, y, dMax);
//    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PoiRepresentation> updatePoi(@PathVariable("id") String id, @RequestBody UpdatePoiRequest request) {
        Commands.UpdatePoi command = request.toCommand(id);
        Poi poi = commandHandler.handler(command);
        return new ResponseEntity(poi.toRepresentation(), HttpStatus.OK);
    }

//    private ResponseEntity<ResponseMessage> update(Poi old, Poi after) {
//        log.info("Atualizando ponto de interesse:\n" + "Atual: " + old.toString() + "  -  Novo: " + after.toString());
//        iPoiRepository.save(after);
//        return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.value(), MSG_UPDATED),
//                HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
//    public void delete(@PathVariable String id) {
//        getPoi(id).ifPresent(this::delete);
//    }
//
//    private ResponseEntity<List<Poi>> searchPois(Integer x, Integer y, Double dMax) {
//        return new ResponseEntity<List<Poi>>(iPoiRepository.findByBetweenCoordinate(x, y, dMax), HttpStatus.OK);
//    }

}
