package com.roiceee.phraseapi;

import com.roiceee.phraseapi.apikeymanagement.model.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.repository.ApiKeyRepository;
import com.roiceee.phraseapi.resourceapi.model.RequestCountModel;
import com.roiceee.phraseapi.resourceapi.repository.RequestNumberRepository;
import com.roiceee.phraseapi.resourceapi.service.RequestCountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestCountServiceTest {

    @InjectMocks
    private RequestCountService requestCountService;

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Mock
    private RequestNumberRepository requestNumberRepository;

    @Test
    public void testAddCountHappyFlow() {
        String testApiKey = "c4b3d3a0-0b1a-4b9e-8b0a-0b1a4b9e8b0a";
        long initialCount = 1L;

        //test if the count is incremented by 1
        RequestCountModel testModel = new RequestCountModel(UUID.fromString(testApiKey), initialCount, "testOwner");

        when(requestNumberRepository.findByApiKey(UUID.fromString(testApiKey))).thenReturn(Optional.of(testModel));

        requestCountService.addCount(testApiKey);

        Assertions.assertEquals(initialCount + 1, testModel.getCount());


        //test if a new entry is created with count 1
        RequestCountModel testModel1 = new RequestCountModel(UUID.fromString(testApiKey), initialCount,
                "testOwner");

        when(requestNumberRepository.findByApiKey(UUID.fromString(testApiKey))).thenReturn(Optional.empty());
        when(apiKeyRepository.findByApiKey(testApiKey)).thenReturn(new UserApiKeyModel("testOwner", testApiKey));

        requestCountService.addCount(testApiKey);

        Assertions.assertEquals(1, testModel1.getCount());
        verify(requestNumberRepository).save(testModel1);



    }
}
