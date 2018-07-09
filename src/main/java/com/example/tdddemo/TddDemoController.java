package com.example.tdddemo;

import com.example.tdddemo.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TddDemoController {

    // Get All Expenses
    @GetMapping("/filteredNews")
    public ResponseEntity getNewsBySource(@RequestParam(value = "source", required = true) String source) {

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

            return new ResponseEntity<Result>(result, HttpStatus.OK);

        } catch (IOException ex) {
            // return 500
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
