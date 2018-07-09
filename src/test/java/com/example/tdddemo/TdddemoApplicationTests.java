package com.example.tdddemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TdddemoApplicationTests {

    @Test
    public void testFilteredNews() {
        ObjectMapper objectMapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            String source = "bbc-news";

            String url = "http://localhost:8090/filteredNews?source=" + source;

            HttpGet httpACGet = new HttpGet(url);

            httpACGet.setHeader("Content-type", "application/json");
            CloseableHttpResponse getACRes = client.execute(httpACGet);
            HttpEntity getRCResEnt = getACRes.getEntity();
            String responseRCStringGET = EntityUtils.toString(getRCResEnt, "UTF-8");
            Integer getRCstatus = getACRes.getStatusLine().getStatusCode();

            httpACGet.releaseConnection();
            Assert.assertEquals(200, getRCstatus.intValue());

            Result result = objectMapper.readValue(responseRCStringGET, new TypeReference<Result>() {
            });

            Assert.assertNotNull(result);
            Assert.assertNotNull(result.getArticles());

            // test that it only got our requested sources
            result.getArticles().forEach(article -> {
                 Assert.assertTrue(article.getSource().getName().toLowerCase().contains("bbc"));
                System.out.println(article.getSource().getName());
            });

        } catch (IOException ex) {
            System.out.println("********************************ERROR********************************************");
            System.out.println(getStackTrace(ex));
            System.out.println("********************************/ERROR********************************************");
            // assertion here assures failure when there's an exception, otherwise the test will pass despite errs
            Assert.assertTrue(false);
        }
    }

    String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

}
