package com.faizal.HybridDatabase.repository;

import com.faizal.HybridDatabase.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {
}
