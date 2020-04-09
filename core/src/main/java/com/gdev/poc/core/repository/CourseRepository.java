package com.gdev.poc.core.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.gdev.poc.core.model.Course;


public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
