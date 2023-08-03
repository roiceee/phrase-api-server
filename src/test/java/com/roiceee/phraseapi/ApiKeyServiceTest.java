package com.roiceee.phraseapi;

import com.roiceee.phraseapi.apikeymanagement.dto.UserApiKeyDTO;
import com.roiceee.phraseapi.apikeymanagement.exception.ApiKeyNotFoundException;
import com.roiceee.phraseapi.apikeymanagement.exception.UserHasApiKeyAlreadyException;
import com.roiceee.phraseapi.apikeymanagement.model.UserApiKeyModel;
import com.roiceee.phraseapi.apikeymanagement.repository.ApiKeyRepository;
import com.roiceee.phraseapi.apikeymanagement.service.ApiKeyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiKeyServiceTest {
    @InjectMocks
    private ApiKeyService apiKeyService;

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testCreateNewApiKeyHappyFlow() {
        String sampleID = "sampleID";
        String sampleApiKey = UUID.randomUUID().toString();

        when(apiKeyRepository.save(any(UserApiKeyModel.class))).thenReturn(new UserApiKeyModel(sampleID, sampleApiKey));

        when(modelMapper.map(any(UserApiKeyModel.class), eq(UserApiKeyDTO.class))).thenReturn(new UserApiKeyDTO(sampleApiKey));

        UserApiKeyDTO res = apiKeyService.createNewApiKey(sampleID);

        assertEquals(new UserApiKeyDTO(sampleApiKey), res);
    }

    @Test
    public void testCreateNewApiKeyError() {
        String sampleID = "sampleID";

        when(apiKeyRepository.existsById(sampleID)).thenReturn(true);

        Assertions.assertThrows(UserHasApiKeyAlreadyException.class, () -> {
            apiKeyService.createNewApiKey(sampleID);
        });
    }

    @Test
    public void testGetApiKeyHappyFlow() {
        String sampleID = "sampleID";
        String sampleApiKey = UUID.randomUUID().toString();

        when(apiKeyRepository.findById(anyString())).thenReturn(java.util.Optional.of(new UserApiKeyModel(sampleID, sampleApiKey)));
        when(modelMapper.map(any(UserApiKeyModel.class), eq(UserApiKeyDTO.class))).thenReturn(new UserApiKeyDTO(sampleApiKey));

        UserApiKeyDTO res = apiKeyService.getApiKey(sampleID);
        Assertions.assertEquals(new UserApiKeyDTO(sampleApiKey), res);
    }

    @Test
    public void testGetApiKeyError() {
        String sampleID = "sampleID";

        when(apiKeyRepository.findById(anyString())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(ApiKeyNotFoundException.class, () -> {
            apiKeyService.getApiKey(sampleID);
        });
    }

    @Test
    public void testDeleteApiKeyHappyFlow() {
        String sampleID = "sampleID";

        when(apiKeyRepository.existsById(sampleID)).thenReturn(true);

        apiKeyService.deleteApiKey(sampleID);

        verify(apiKeyRepository, times(1)).deleteById(sampleID);
    }

    @Test
    public void testDeleteApiKeyError() {
        String sampleID = "sampleID";

        when(apiKeyRepository.existsById(sampleID)).thenReturn(false);

        Assertions.assertThrows(ApiKeyNotFoundException.class, () -> {
            apiKeyService.deleteApiKey(sampleID);
        });
    }

    @Test
    public void testUserAlreadyHasKey() {
        String sampleID = "sampleID";

        when(apiKeyRepository.existsById(sampleID)).thenReturn(true);

        boolean res = apiKeyService.userAlreadyHasKey(sampleID);

        Assertions.assertTrue(res);
    }

    @Test
    public void testUserDoesNotHaveKey() {
        String sampleID = "sampleID";

        when(apiKeyRepository.existsById(sampleID)).thenReturn(false);

        boolean res = apiKeyService.userAlreadyHasKey(sampleID);

        Assertions.assertFalse(res);
    }
}
