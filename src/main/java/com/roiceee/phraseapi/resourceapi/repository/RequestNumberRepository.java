package com.roiceee.phraseapi.resourceapi.repository;

import com.roiceee.phraseapi.resourceapi.model.RequestCountModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RequestNumberRepository extends CrudRepository<RequestCountModel, Long> {

    @Modifying
    @Query(value = "INSERT INTO public.request_count (api_Key) values (:apiKey);",
            nativeQuery =
            true)
    void addRequestCount(String apiKey);
}
