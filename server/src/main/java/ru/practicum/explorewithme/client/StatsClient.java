package ru.practicum.explorewithme.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.practicum.explorewithme.client.model.EndpointHit;

import java.util.Map;

@Service
public class StatsClient {
    private final String uriStats;
    private final RestTemplate rest = new RestTemplate();

    public StatsClient(@Value("${EXPLOREWITHME_STATS_URL}") String uriStats) {
        this.uriStats = uriStats;
    }

    public ResponseEntity<Object> sendStates(EndpointHit body) {
        return makeAndSendRequest(HttpMethod.POST, "/hit", null, body);
    }

    private ResponseEntity<Object> makeAndSendRequest(HttpMethod method, String path, Map<String, Object> parameters, EndpointHit body) {
        HttpEntity<EndpointHit> requestEntity = new HttpEntity<>(body, defaultHeaders());

        ResponseEntity<Object> exploreWithMeServerResponse;
        try {
            if (parameters != null) {
                exploreWithMeServerResponse = rest.exchange(
                        uriStats + path,
                        method,
                        requestEntity,
                        Object.class,
                        parameters
                );
            } else {
                exploreWithMeServerResponse = rest.exchange(uriStats + path, method, requestEntity, Object.class);
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponse(exploreWithMeServerResponse);
    }

    private static ResponseEntity<Object> prepareGatewayResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());

        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }

        return responseBuilder.build();
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
