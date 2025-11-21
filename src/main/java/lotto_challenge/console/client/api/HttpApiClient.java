package lotto_challenge.console.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lotto_challenge.core.controller.dto.request.GenerateLottosRequestDto;
import lotto_challenge.core.controller.dto.request.LottoStatisticRequestDto;
import lotto_challenge.core.controller.dto.response.GenerateLottosResponseDto;
import lotto_challenge.core.controller.dto.response.LottoStatisticResponseDto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpApiClient implements ApiClient {

    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public HttpApiClient() {
    }

    @Override
    public GenerateLottosResponseDto generateLottosApi(final GenerateLottosRequestDto request) throws Exception {
        final String url = "http://localhost:8080/api/lotto/generate";
        final String requestBody = mapper.writeValueAsString(request);

        final HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), GenerateLottosResponseDto.class);
    }

    @Override
    public LottoStatisticResponseDto getLottoStatisticsApi(final LottoStatisticRequestDto request) throws Exception {
        final String url = "http://localhost:8080/api/lotto/statistic";
        final String requestBody = mapper.writeValueAsString(request);

        final HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), LottoStatisticResponseDto.class);
    }
}
