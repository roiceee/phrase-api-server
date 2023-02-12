package com.roiceee.quotejokeapi;

import com.roiceee.quotejokeapi.util.ReqParamNames;
import com.roiceee.quotejokeapi.util.ReqParamQtyValues;
import com.roiceee.quotejokeapi.util.ReqParamTypeValues;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RequestResourceControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Description("Check status of getRandomResource with parameter 'type' value of 'joke'.")
    public void getRandomJokeResourceTestHappyFlowTestStatus() throws Exception {
        String type = ReqParamTypeValues.JOKE;
        mockMvc.perform(get("/api").param(ReqParamNames.TYPE, type))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Description("Check status of getRandomJokeResource with wrong parameter 'type' value.")
    public void getRandomJokeResourceTestWrongTypeValueTestStatus() throws Exception {
        String type = "wrong type";
        mockMvc.perform(get("/api").param(ReqParamNames.TYPE, type))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
    @Test
    @Description("Check status of getRandomResourceList with " +
            "parameter type = joke and quantity = 3.")
    public void getRandomJokeResourceListHappyFlowTestStatus() throws Exception {
        String type = ReqParamTypeValues.JOKE;
        int qty = 3;
        mockMvc.perform(get("/api").param(ReqParamNames.TYPE, type)
                        .param(ReqParamNames.QTY, String.valueOf(qty)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Description("Check status of getRandomResourceList with" +
            "parameter type = joke and wrong quantity")
    public void getRandomJokeResourceListWrongTypeValueTestStatus() throws Exception {
        String type = ReqParamTypeValues.JOKE;
        int qty = ReqParamQtyValues.MAXQTY + 1;
        mockMvc.perform(get("/api").param(ReqParamNames.TYPE, type)
                        .param(ReqParamNames.QTY, String.valueOf(qty)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Description("Check status of getResourcesWithPagination")
    public void getResourcesWithPaginationHappyFlowTestStatus() throws Exception {
        String type = ReqParamTypeValues.JOKE;
        mockMvc.perform(get("/api").param(ReqParamNames.TYPE, type)
                        .param(ReqParamNames.PAGE, "2")
                        .param(ReqParamNames.QTY, "4"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Description("Check status of getResourcesWithPagination with wrong parameters")
    public void getResourcesWithPaginationWrongParamsStatus() throws Exception {
        String type = ReqParamTypeValues.JOKE;

        mockMvc.perform(get("/api")
                        .param(ReqParamNames.TYPE, type)
                        .param(ReqParamNames.PAGE, "-1")
                        .param(ReqParamNames.QTY, "4"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(get("/api")
                        .param(ReqParamNames.TYPE, type)
                        .param(ReqParamNames.PAGE, "2")
                        .param(ReqParamNames.QTY, "34"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}
