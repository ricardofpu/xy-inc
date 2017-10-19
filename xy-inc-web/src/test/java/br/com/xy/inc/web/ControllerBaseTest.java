package br.com.xy.inc.web;

import br.com.xy.inc.web.config.ApplicationTestConfig;
import br.com.xy.inc.web.representation.PoiRepresentation;
import br.com.xy.inc.web.request.CreatePoiRequest;
import br.com.xy.inc.web.request.UpdatePoiRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationTestConfig.class)
public abstract class ControllerBaseTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    protected RestDocumentationResultHandler documentationResultHandler;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        documentationResultHandler = document("{method-name}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.documentationResultHandler)
                .build();
    }

    protected List<Object> createPoi() throws Exception {
        CreatePoiRequest request = dummyCreatePoiRequestSuccess();
        MvcResult result = this.mockMvc.perform(post("/poi")
                .content(objectToJson((Object) request))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.notNullValue()))
                .andReturn();


        return singletonList(jsonToObject(result.getResponse().getContentAsString(), PoiRepresentation.class));
    }

    protected CreatePoiRequest dummyCreatePoiRequestSuccess() {
        return new CreatePoiRequest("Lanchonete", 20, 10);
    }

    protected UpdatePoiRequest dummyUpdatePoiRequestSuccess() {
        return new UpdatePoiRequest("Lanchonete Updated", 20, 15);
    }

    protected String objectToJson(Object c) throws JsonProcessingException {
        return objectMapper.writeValueAsString(c);
    }

    protected Object jsonToObject(String value, Class s) throws IOException {
        return objectMapper.readValue(value, s);
    }

}
