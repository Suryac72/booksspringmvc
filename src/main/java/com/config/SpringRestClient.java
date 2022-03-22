package com.config;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.entity.Books;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class SpringRestClient {

    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:9090/books";
    private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:9090/books/{bookId}";
    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:9090/books";
    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:9090/books/{bookId}";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:9090/books/{bookId}";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();


    }


    public void create(Books bk) {
        RestTemplate restTemplate = new RestTemplate();
        Books result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, bk, Books.class);

        System.out.println(result);
    }

    public void update(Books bk,String bookId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL,bk,bookId);
    }

    public void delete(long bookId) {
  
       RestTemplate restTemplate = new RestTemplate();
       restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL,bookId);
   }
    
    
}