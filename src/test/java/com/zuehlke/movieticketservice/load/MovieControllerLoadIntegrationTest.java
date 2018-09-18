package com.zuehlke.movieticketservice.load;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the integration from controller level to the real peripheral systems
 * under load. Observe the returned ratings (should be [] if the fallback is triggered).
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerLoadIntegrationTest {

    private static final int NUMBER_OF_REQUESTS = 30;
    private static final int THREADS = 5;

    @LocalServerPort
    private int port;

    @Test
    public void getMovieByIdLoadTest() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        List<Future<ResponseEntity<String>>> futureResults = new LinkedList<>();

        for(int i = 0; i < NUMBER_OF_REQUESTS; i++) {
            futureResults.add(executor.submit(this::getMovieById));
        }

        List<ResponseEntity<String>> results = futureResults.stream().map(r -> {
            try {
                return r.get();
            } catch (Exception e) {
                return error();
            }
        }).collect(toList());

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        printResults(results);

        assertThat(results.stream().allMatch(r -> r.getStatusCode().is2xxSuccessful()), is(true));
    }

    private void printResults(List<ResponseEntity<String>> results) {
        System.out.println("Result Summary");
        results.forEach(r -> System.out.printf("HttpStatus=%d HttpResponse=%s \n", r.getStatusCodeValue(), r.getBody()));
    }

    private ResponseEntity<String> getMovieById() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:" + port + "/api/v1/movies/1";
            return restTemplate.getForEntity(url, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return error();
    }

    private ResponseEntity<String> error() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}