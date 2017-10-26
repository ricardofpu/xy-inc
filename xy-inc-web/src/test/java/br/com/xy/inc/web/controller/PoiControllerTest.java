package br.com.xy.inc.web.controller;

import br.com.xy.inc.web.ControllerBaseTest;
import br.com.xy.inc.web.representation.PoiRepresentation;
import br.com.xy.inc.web.request.CreatePoiRequest;
import br.com.xy.inc.web.request.UpdatePoiRequest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PoiControllerTest extends ControllerBaseTest {

    private static final String ID_FIELD = "Identify unique to a Poi";
    private static final String NAME_FIELD = "Identify a name to a Poi";
    private static final String CODE_STATUS_FIELD = "Identify a http status error code";
    private static final String COORDINATE_X_FIELD = "Number of coordinate x";
    private static final String COORDINATE_Y_FIELD = "Number of coordinate y";
    private static final String RESOURCE_POI_FIELD = "Resource not founded by application";
    private static final String VALUE_FIELD = "Value from resource not founded";
    private static final String STRING_TYPE = "Type of string";


    @Test
    public void createPoiSuccess() throws Exception {
        CreatePoiRequest request = dummyCreatePoiRequestSuccess();
        this.mockMvc.perform(post("/poi")
                .content(objectToJson(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.notNullValue()))
                .andDo(
                        documentationResultHandler.document(
                                requestFields(
                                        fieldWithPath("name").description(NAME_FIELD).type(STRING_TYPE),
                                        fieldWithPath("coordinateX").description(COORDINATE_X_FIELD),
                                        fieldWithPath("coordinateY").description(COORDINATE_Y_FIELD)
                                ),
                                responseFields(
                                        fieldWithPath("id").description(ID_FIELD).type(STRING_TYPE),
                                        fieldWithPath("name").description(NAME_FIELD).type(STRING_TYPE),
                                        fieldWithPath("coordinateX").description(COORDINATE_X_FIELD),
                                        fieldWithPath("coordinateY").description(COORDINATE_Y_FIELD)
                                )
                        )
                );
    }

    @Test
    public void failCreatePoiWithoutMandatoryFields() throws Exception {
        CreatePoiRequest request = dummyCreatePoiRequestWithoutMandatoryFields();
        this.mockMvc.perform(post("/poi")
                .content(objectToJson(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andDo(
                        documentationResultHandler.document(
                                responseFields(
                                        fieldWithPath("code").description(CODE_STATUS_FIELD),
                                        fieldWithPath("fields.name").description(NAME_FIELD).type(STRING_TYPE),
                                        fieldWithPath("fields.coordinateX").description(COORDINATE_X_FIELD),
                                        fieldWithPath("fields.coordinateY").description(COORDINATE_Y_FIELD)
                                )
                        )
                );
    }

    @Test
    public void updatePoiSuccess() throws Exception {
        PoiRepresentation representation = (PoiRepresentation) createPoi().get(0);
        UpdatePoiRequest request = dummyUpdatePoiRequestSuccess();
        this.mockMvc.perform(put("/poi/{id}", representation.getId())
                .content(objectToJson(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(representation.getId())))
                .andDo(
                        documentationResultHandler.document(
                                pathParameters(
                                        parameterWithName("id").description(ID_FIELD)
                                ),
                                requestFields(
                                        fieldWithPath("name").description(NAME_FIELD).type(STRING_TYPE),
                                        fieldWithPath("coordinateX").description(COORDINATE_X_FIELD),
                                        fieldWithPath("coordinateY").description(COORDINATE_Y_FIELD)
                                ),
                                responseFields(
                                        fieldWithPath("id").description(ID_FIELD).type(STRING_TYPE),
                                        fieldWithPath("name").description(NAME_FIELD).type(STRING_TYPE),
                                        fieldWithPath("coordinateX").description(COORDINATE_X_FIELD),
                                        fieldWithPath("coordinateY").description(COORDINATE_Y_FIELD)
                                )
                        )
                );
    }

    @Test
    public void failUpdateWhenPoiNotFound() throws Exception {
        UpdatePoiRequest request = dummyUpdatePoiRequestSuccess();
        String id = UUID.randomUUID().toString();
        this.mockMvc.perform(put("/poi/{id}", id)
                .content(objectToJson(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andDo(
                        documentationResultHandler.document(
                                pathParameters(
                                        parameterWithName("id").description(ID_FIELD)
                                ),
                                responseFields(
                                        fieldWithPath("resource").description(RESOURCE_POI_FIELD),
                                        fieldWithPath("value").description(VALUE_FIELD)
                                )
                        )
                );
    }

    @Test
    public void deletePoiSuccess() throws Exception {
        PoiRepresentation representation = (PoiRepresentation) createPoi().get(0);
        this.mockMvc.perform(delete("/poi/{id}", representation.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(
                        documentationResultHandler.document(
                                pathParameters(
                                        parameterWithName("id").description(ID_FIELD)
                                )
                        )
                );
    }

    @Test
    public void failDeleteWhenPoiNotFound() throws Exception {
        String id = UUID.randomUUID().toString();
        this.mockMvc.perform(delete("/poi/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andDo(
                        documentationResultHandler.document(
                                pathParameters(
                                        parameterWithName("id").description(ID_FIELD)
                                )
                        )
                );
    }
}
