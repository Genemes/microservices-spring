package com.gdev.poc.core.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.gdev.poc.core.model.ApplicationUser;


public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

}
