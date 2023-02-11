package com.roiceee.quotejokeapi;

import com.roiceee.quotejokeapi.util.ReqParamTypeValues;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
public class RequestResourceControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    @Description("Check status of getRandomResource with parameter 'type' value of 'joke'.")
    public void getRandomJokeResourceTestHappyFlowTestStatus() throws Exception {
        String type = ReqParamTypeValues.JOKE;
        mockMvc.perform(get("/api").param("type", type))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Description("Check status of getRandomJokeResource with wrong parameter 'type' value.")
    public void getRandomJokeResourceTestWrongTypeValueTestStatus() throws Exception {
        String type = "wrong type";
        mockMvc.perform(get("/api").param("type", type))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
