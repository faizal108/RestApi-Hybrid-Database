package com.faizal.HybridDatabase.service;

import com.faizal.HybridDatabase.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public void createCourse(Course course);
    public List<Course> getCourse();
    public Optional<Course> findById(long id);
    public Course update(Course course, long l);
    public void deleteCourseById(long id);
    public Course updatePartially(Course course, long id);
}
