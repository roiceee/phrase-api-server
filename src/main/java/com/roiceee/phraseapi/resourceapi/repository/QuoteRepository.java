package com.roiceee.phraseapi.resourceapi.repository;

import com.roiceee.phraseapi.resourceapi.model.QuoteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;


public interface QuoteRepository extends JpaRepository<QuoteModel, Long> {

    @Query(value =
            "SELECT * FROM public.quotes " +
                    "ORDER BY random() LIMIT  1",
            nativeQuery = true)
    QuoteModel getRandomQuote();

    @Query(value = "SELECT * FROM public.quotes " +
            "ORDER BY random() LIMIT :quantity;",
            nativeQuery = true)
    List<QuoteModel> getRandomQuoteList(int quantity);

    @Query(value = "SELECT * FROM public.quotes " +
            "WHERE lower(phrase) LIKE '%'||lower(:query)||'%' " +
            "ORDER BY random() LIMIT :quantity",
            nativeQuery = true)
    List<QuoteModel> getRandomQuoteListWithQuery(int quantity, String query);



    Page<QuoteModel> findAllByPhraseIsContaining(String phrase, Pageable pageable);
}
