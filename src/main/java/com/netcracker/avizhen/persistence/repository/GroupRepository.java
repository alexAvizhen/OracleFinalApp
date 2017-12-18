package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 18.12.2017.
 */
@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Integer> {

    Group findById(Integer id);

    @Query("from Group")
    List<Group> getAll();

}