package com.example.springframework.repositories;

import com.example.springframework.domain.BlogEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BlogRepository extends CrudRepository<BlogEntry, Integer>{
}
