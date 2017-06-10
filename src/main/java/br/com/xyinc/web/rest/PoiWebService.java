package br.com.xyinc.web.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.entity.Poi;
import br.com.xyinc.repository.IPoiRepository;

@RestController
@RequestMapping("/poi")
public class PoiWebService {

	@Autowired
	IPoiRepository iPoiRepository;

	private static final Logger log = LoggerFactory.getLogger(PoiWebService.class);

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	public Iterable<Poi> getAll() {
		return iPoiRepository.findAll();
	}

	@RequestMapping(method = POST)
	public ResponseEntity<String> createPoi(@RequestBody Poi poi) {
		if (poi.isEmpty()) {
			log.error("Erro ao criar POI:  Nenhum valor pode ser nulo.");
			return new ResponseEntity<String>("Erro: Nenhum valor pode ser nulo.", HttpStatus.BAD_REQUEST);
		} else if (poi.getNome().isEmpty()) {
			log.error("Erro ao criar POI: Campo 'NOME' não pode ser vazio.");
			return new ResponseEntity<String>("Erro: Campo 'NOME' não pode ser vazio.", HttpStatus.BAD_REQUEST);
		}
		return savePoi(poi);
	}

	@RequestMapping(value = "/search", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Poi>> search(@RequestParam(value = "x", required = true) Integer x,
			@RequestParam(value = "y", required = true) Integer y,
			@RequestParam(value = "dMax", required = true) Double dMax) {
		return searchPois(x, y, dMax);
	}

	private ResponseEntity<List<Poi>> searchPois(Integer x, Integer y, Double dMax) {
		return new ResponseEntity<List<Poi>>(iPoiRepository.findByBetweenCoordinate(x, y, dMax), HttpStatus.OK);
	}

	private ResponseEntity<String> savePoi(Poi poi) {
		try {
			if (!poi.isValid()) {
				log.error("Erro: Coordenadas não podem ter valores negativos.");
				return new ResponseEntity<String>("Erro: Coordenadas não podem ter valores negativos.",
						HttpStatus.BAD_REQUEST);
			} else {
				iPoiRepository.save(poi);
				log.info("Registro salvo com sucesso. " + poi.toString());
				return new ResponseEntity<String>("Registro salvo com sucesso.", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Erro. Contacte o administrador.", HttpStatus.BAD_REQUEST);
	}

}
