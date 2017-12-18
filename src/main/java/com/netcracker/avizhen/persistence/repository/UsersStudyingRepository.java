package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.UsersStudying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Александр on 18.12.2017.
 */
public interface UsersStudyingRepository extends PagingAndSortingRepository<UsersStudying, Integer> {

    UsersStudying findById(Integer id);

    @Query("from UsersStudying")
    List<UsersStudying> getAll();

}