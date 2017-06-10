package br.com.xyinc.web.rest;

import static br.com.xyinc.utils.SystemProperties.MSG_COORDINATE_NULL;
import static br.com.xyinc.utils.SystemProperties.MSG_ERROR;
import static br.com.xyinc.utils.SystemProperties.MSG_ERROR_NOME_NULL;
import static br.com.xyinc.utils.SystemProperties.MSG_ERROR_NULL;
import static br.com.xyinc.utils.SystemProperties.MSG_SAVED;
import static br.com.xyinc.utils.SystemProperties.MSG_UPDATED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.entity.Poi;
import br.com.xyinc.repository.IPoiRepository;
import br.com.xyinc.utils.ResponseMessage;

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

	@RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Poi> get(@PathVariable Integer id) {
		return Optional.ofNullable(iPoiRepository.findById(id))
				.map(result -> new ResponseEntity<Poi>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> createPoi(@RequestBody Poi poi) {
		if (poi.isEmpty()) {
			log.error("Erro ao criar POI: " + MSG_ERROR_NULL);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(BAD_REQUEST.value(), MSG_ERROR_NULL),
					BAD_REQUEST);
		} else if (poi.getNome().isEmpty()) {
			log.error("Erro ao criar POI: " + MSG_ERROR_NOME_NULL);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(BAD_REQUEST.value(), MSG_ERROR_NOME_NULL),
					BAD_REQUEST);
		}
		return savePoi(poi);
	}

	@RequestMapping(value = "/search", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Poi>> search(@RequestParam(value = "x", required = true) Integer x,
			@RequestParam(value = "y", required = true) Integer y,
			@RequestParam(value = "dMax", required = true) Double dMax) {
		return searchPois(x, y, dMax);
	}

	@RequestMapping(method = PUT, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> updatePoi(@RequestBody Poi poi) {
		return getPoi(poi).map(old -> update(old, poi))
				.orElse(new ResponseEntity<ResponseMessage>(HttpStatus.BAD_REQUEST));
	}

	private ResponseEntity<ResponseMessage> update(Poi old, Poi after) {
		log.info("Atualizando ponto de interesse:\n" + "Atual: " + old.toString() + "  -  Novo: " + after.toString());
		iPoiRepository.save(after);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.value(), MSG_UPDATED),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		getPoi(id).ifPresent(this::delete);
	}

	private ResponseEntity<List<Poi>> searchPois(Integer x, Integer y, Double dMax) {
		return new ResponseEntity<List<Poi>>(iPoiRepository.findByBetweenCoordinate(x, y, dMax), HttpStatus.OK);
	}

	private ResponseEntity<ResponseMessage> savePoi(Poi poi) {
		try {
			if (!poi.isValid()) {
				log.error("Erro: " + MSG_COORDINATE_NULL);
				return new ResponseEntity<ResponseMessage>(
						new ResponseMessage(BAD_REQUEST.value(), MSG_COORDINATE_NULL), BAD_REQUEST);
			} else {
				iPoiRepository.save(poi);
				log.info(MSG_SAVED + " " + poi.toString());
				return new ResponseEntity<ResponseMessage>(new ResponseMessage(CREATED.value(), MSG_SAVED), CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(BAD_REQUEST.value(), MSG_ERROR), BAD_REQUEST);
	}

	private Optional<Poi> getPoi(Poi poi) {
		return Optional.ofNullable(iPoiRepository.findById(poi.getId()));
	}

	private Optional<Poi> getPoi(Integer id) {
		return Optional.ofNullable(iPoiRepository.findById(id));
	}

	private void delete(Poi poi) {
		iPoiRepository.delete(poi);
	}

}
