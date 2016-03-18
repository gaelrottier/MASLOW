package fr.unice.mbds.maslow.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Nicolas on 09/12/2015.
 */
public class ApiCallService<T extends IEntity> {

    private static ApiCallService instance = null;

    public static ApiCallService getInstance() {
        if (instance == null) {
            synchronized (ApiCallService.class) {
                if (instance == null) {
                    instance = new ApiCallService();
                }
            }
        }

        return instance;
    }

    public ResponseEntity execute(String url, HttpMethod methode, T entity, Class<T> typeRetour) throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = null;

        if (entity != null) {
            httpEntity = new HttpEntity<>(entity.toJson().toString(), httpHeaders);
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            return restTemplate.exchange(url, methode, httpEntity, typeRetour);
        } catch (Exception e) {
            throw e;
        }
    }

}