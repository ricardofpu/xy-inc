package br.com.xyinc.test;

import static br.com.xyinc.utils.SystemProperties.MSG_SAVED;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xyinc.domain.Poi;
import br.com.xyinc.utils.ResponseMessage;
import br.com.xyinc.web.rest.PoiWebService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PoiWebServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PoiWebService poiService;

	@Test
	public void list() throws Exception {
		Poi p = createPoi();
		List<Poi> result = new ArrayList<Poi>();
		result.add(p);
		given(poiService.getAll()).willReturn(result);

		mockMvc.perform(get("/poi").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void create() throws Exception {
		Poi p = createPoi();

		when(poiService.createPoi(any(Poi.class))).thenReturn(sendResponse());
		
		
		 mockMvc.perform(post("/poi").contentType(MediaType.APPLICATION_JSON)
					.content(this.convertObjectToJsonBytes(p)))
						.andExpect(status().isCreated());

	}
	
	@Test
	public void search() throws Exception {
		
		mockMvc.perform(get("/poi/search")
				.param("x", "20")
				.param("y", "10")
				.param("dMax", "10"))
				.andExpect(status().isOk());
	}

	public ResponseEntity<ResponseMessage> sendResponse() {
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(201, MSG_SAVED), CREATED);
	}

	public Poi createPoi() {
		return new Poi(1, "Padaria", 18, 10);
	}
	
	public byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

}
