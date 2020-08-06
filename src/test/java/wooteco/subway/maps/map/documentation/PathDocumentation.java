package wooteco.subway.maps.map.documentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.web.context.WebApplicationContext;

import wooteco.security.core.TokenResponse;
import wooteco.subway.common.documentation.Documentation;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.ui.MapController;

@WebMvcTest(controllers = {MapController.class})
public class PathDocumentation extends Documentation {
    @Autowired
    private MapController mapController;

    @MockBean
    private MapService mapService;

    protected TokenResponse tokenResponse;

    @BeforeEach
    public void setUp(
            WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
        tokenResponse = new TokenResponse("token");
    }

    @Test
    void findPath() {
        when(mapService.findPath(any(), any(), any(), any())).thenReturn(any());

        Map<String, Object> params = new HashMap<>();
        params.put("source", 1L);
        params.put("target", 2L);
        params.put("type", "distance");

        given().log().all()
                .header("Authorization", "Bearer " + tokenResponse.getAccessToken())
                .contentType(APPLICATION_JSON_VALUE)
                .params(params)
                .when()
                .get("/paths")
                .then().log().all()
                .apply(document("paths/",
                        requestParameters(
                                parameterWithName("source").description("출발역의 ID"),
                                parameterWithName("target").description("도착역의 ID"),
                                parameterWithName("type").description("최단경로 조회 타입")
                        ),
                        responseFields(
                                fieldWithPath("duration").type(JsonFieldType.NUMBER)
                                        .description("경로의 시간"),
                                fieldWithPath("distance").type(JsonFieldType.NUMBER)
                                        .description("경로의 거리"),
                                fieldWithPath("fare").type(JsonFieldType.NUMBER)
                                        .description("경로의 요금")
                        )))
                .extract();
    }
}
