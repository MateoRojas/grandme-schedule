package org.grandma.schedule.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Service for consuming rest services.
 *
 * @author mateo
 * @since 1.0.0
 */
@Component
public class CommonRestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Makes a POST to the provided
     * url with a form-urlencoded format.
     *
     * @param url the url of the REST service to be consumed
     * @param map the form values to be send
     * @param clazz the class of the response
     * @param <T> type of the response
     * @return the response from the consumed REST service
     */
    public <T> T postFormUrlEncoded(String url, MultiValueMap<String, String> map, Class<T> clazz) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(map, headers), clazz).getBody();
    }
}
