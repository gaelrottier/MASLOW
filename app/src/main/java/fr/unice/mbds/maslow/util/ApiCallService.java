package fr.unice.mbds.maslow.util;

import android.util.Log;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    public ResponseEntity executeForJson(String url, HttpMethod methode, JSONObject entity, Class<T> typeRetour) {
        if (entity != null) {
            return execute(url, methode, entity, typeRetour);
        } else {
            return execute(url, methode, null, typeRetour);
        }
    }

    public HttpHeaders prepareHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return httpHeaders;
    }

    public RestTemplate prepareRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }

    public ResponseEntity execute(String url, HttpMethod methode, JSONObject entity, Class<?> typeRetour) {
        HttpHeaders httpHeaders = prepareHttpHeaders();

        RestTemplate restTemplate = prepareRestTemplate();

        HttpEntity<String> httpEntity = null;

        if (entity != null) {
            httpEntity = new HttpEntity<>(entity.toString(), httpHeaders);
        }

        ResponseEntity<?> result = null;

        try {
            result = restTemplate.exchange(url, methode, httpEntity, typeRetour);
        } catch (Exception e) {
            Log.e("requête http", e.getMessage());
        }

        return result;
    }

    public ResponseEntity executeForList(String url, ParameterizedTypeReference<List<T>> typeRetour) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<List<T>> result = null;

        try {
            result = restTemplate.exchange(url, HttpMethod.GET, null, typeRetour);
        } catch (RestClientException e) {
            Log.e("rest ", e.getMessage());
        }

        return result;
    }

    public ResponseEntity executeForEntity(String url, HttpMethod methode, T entity, Class<T> typeRetour) {
        if (entity != null) {
            return execute(url, methode, entity.toJson(), typeRetour);
        } else {
            return execute(url, methode, null, typeRetour);
        }
    }

}