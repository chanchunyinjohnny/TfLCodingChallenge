package com.johnny.exercise;

import com.google.gson.Gson;
import com.johnny.exercise.models.requests.RoadStatusRequestModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by johnn on 16/04/2018.
 */
public class ApplicationControllerTestes extends TflCodingChallengeApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testA22() throws Exception {

        RoadStatusRequestModel input = new RoadStatusRequestModel();
        input.setRoadName("A22");
        Gson gson = new Gson();

        mockMvc.perform(post("/roadStatus").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(input))).andExpect(status().is(200)).andExpect(jsonPath("$.message").value("The following road id is not recognised: A22"));
    }

    @Test
    public void testA2() throws Exception {
        RoadStatusRequestModel input = new RoadStatusRequestModel();
        input.setRoadName("A2");
        Gson gson = new Gson();

        mockMvc.perform(post("/roadStatus").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(input))).andExpect(status().is(200)).andExpect(jsonPath("$.displayName").value("A2"));
    }
}
