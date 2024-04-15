package com.mysite.core.services.impl;

import com.mysite.core.services.FetchWeatherDataService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;

@Component
public class FetchWeatherDataServiceImpl implements FetchWeatherDataService {
    @Reference
    private HttpClientBuilderFactory clientBuilderFactory;

    @Override
    public String getData(String url) {
        CloseableHttpClient client = clientBuilderFactory.newBuilder().build();
        HttpGet getMethod = new HttpGet("https://dummy.restapiexample.com/api/v1/employee/1");
        CloseableHttpResponse httpClientResponse = client.execute(getMethod);
        String responseBody = EntityUtils.toString(httpClientResponse.getEntity());
        JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);

        return "jsonObject";
    }

    public static @interface Config {

        @AttributeDefinition(name = "Weather API endpoint")
        String endPoint() default "http://api.weatherapi.com/v1/current.json?key=e6f2d1dd14a14a889bf145621241504&q=iata:CGN&lang=en&aqi=yes";

        @AttributeDefinition(name = "API Key",
                description = "Weather API key")
        String apiKey() default "e6f2d1dd14a14a889bf145621241504";

        @AttributeDefinition(name = "City IATA code",
                description = "City IATA code")
        String[] locationS();
    }
}
