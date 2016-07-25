package shan.com.util;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ThienDN on 8/28/2015.
 */
public class RestTemplates {
    private static RestTemplate restTemplate = null;
    public static RestTemplate getInstance(){
        if(restTemplate == null){
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        }
        return restTemplate;
    }
}
