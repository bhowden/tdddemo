package com.example.tdddemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Async
    public CompletableFuture<Result> getNewsStoriesBySource(String source) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            String url = "https://newsapi.org/v2/everything?sources=" + source + "&apiKey=ef82757b17e94ba28187c75125488dc8";

            HttpGet httpACGet = new HttpGet(url);

            httpACGet.setHeader("Content-type", "application/json");
            CloseableHttpResponse getACRes = client.execute(httpACGet);
            HttpEntity getRCResEnt = getACRes.getEntity();
            String responseRCStringGET = EntityUtils.toString(getRCResEnt, "UTF-8");
            httpACGet.releaseConnection();

            ObjectMapper objectMapper = new ObjectMapper();

            Result result = objectMapper.readValue(responseRCStringGET, new TypeReference<Result>() {
            });

            return CompletableFuture.completedFuture(result);

        }
    }

}
