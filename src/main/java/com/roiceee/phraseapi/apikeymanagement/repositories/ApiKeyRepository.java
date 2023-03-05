package com.roiceee.phraseapi.apikeymanagement.repositories;

import com.roiceee.phraseapi.apikeymanagement.models.UserApiKeyModel;
import org.springframework.data.repository.CrudRepository;

public interface ApiKeyRepository extends CrudRepository <UserApiKeyModel, Long> {
}
