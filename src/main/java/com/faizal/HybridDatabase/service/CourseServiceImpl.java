package com.faizal.HybridDatabase.service;

import com.faizal.HybridDatabase.model.Course;
import com.faizal.HybridDatabase.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> getCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course update(Course course, long l) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course updatePartially(Course course, long id) {
        Optional<Course> crs = findById(id);
        Course newuser = crs.get();
        return courseRepository.save(newuser);
    }
}
