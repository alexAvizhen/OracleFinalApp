package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 18.12.2017.
 */
@Repository
public interface UserRepository  extends PagingAndSortingRepository<User, Integer> {

    User findById(Integer id);

    @Query("from User")
    List<User> getAll();

    List<User> findByCurrentCourseId(Integer currentCourseId);

    @Query("select u from User u, UsersStudying us_st where u.id = us_st.userId and us_st.programPlaceId in (10, 11, 12)")
    List<User> findUsersStudyingInGreece();

}
