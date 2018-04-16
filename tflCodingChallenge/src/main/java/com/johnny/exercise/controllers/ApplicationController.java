package com.johnny.exercise.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.johnny.exercise.models.errors.RoadErrorResponseModel;
import com.johnny.exercise.models.requests.RoadStatusRequestModel;
import com.johnny.exercise.models.responses.RoadResponseModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by johnn on 15/04/2018.
 */
@RequestMapping("/roadStatus")
@RestController
public class ApplicationController {

    @Value("${api_url}")
    private String apiUrl;
    @Value("${app_id}")
    private String appId;
    @Value("${app_key}")
    private String appKey;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String post(@RequestBody RoadStatusRequestModel roadStatusRequestModel) throws IOException {

        Map<String, String> resultMap = new HashMap<>();
        String result = "";

        //use GET method to get the result
        String url = apiUrl + roadStatusRequestModel.getRoadName();

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        if (response.getStatusLine().getStatusCode() == 200) {

            List<RoadResponseModel> roadResponseModelList;
            Type listType = new TypeToken<List<RoadResponseModel>>() {
            }.getType();
            roadResponseModelList = new Gson().fromJson(bufferedReader, listType);

            resultMap.put("displayName", roadResponseModelList.get(0).getDisplayName());
            resultMap.put("Road Status", roadResponseModelList.get(0).getStatusSeverity());
            resultMap.put("Road Status Description", roadResponseModelList.get(0).getStatusSeverityDescription());

            result = gson.toJson(resultMap);

        }
        else {
            RoadErrorResponseModel roadErrorResponseModel = gson.fromJson(bufferedReader, RoadErrorResponseModel.class);
            result = gson.toJson(roadErrorResponseModel);
        }

        return result;
    }
}
