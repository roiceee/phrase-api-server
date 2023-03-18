package com.roiceee.phraseapi.mainapi.repositories;

import com.roiceee.phraseapi.mainapi.models.RequestCountModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RequestNumberRepository extends CrudRepository<RequestCountModel, Long> {


    @Query(value = "INSERT INTO request_count (request_number, api_Key) values (null, :apiKey);", nativeQuery =
            true)
    void addRequestCount(String apiKey);
}
