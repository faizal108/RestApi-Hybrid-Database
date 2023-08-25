package com.faizal.HybridDatabase.controller;

import com.faizal.HybridDatabase.model.Course;
import com.faizal.HybridDatabase.service.CourseService;
import com.faizal.HybridDatabase.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/course"})
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long id){
        System.out.println("Fetching course with id "+ id);
        Optional<Course> optcourse = courseService.findById(id);
        Course course = optcourse.get();
        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/create", headers = "Accept=application/json")
    public ResponseEntity<Void> createcourse(@RequestBody Course course, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating course " + course.getName());
        courseService.createCourse(course);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri());
        return new ResponseEntity<Void> (headers, HttpStatus.CREATED);
    }
    @GetMapping(value = "/get", headers = "Accept=application/json")
    public List<Course> getAllcourse() {
        List<Course> tasks = courseService.getCourse();
        return tasks;
    }
    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Course> deletecourse(@PathVariable("id") long id) {
        Optional<Course> optcourse = courseService.findById(id);
        Course course = optcourse.get();
        if (course == null) {
            return new ResponseEntity<Course> (HttpStatus.NOT_FOUND);
        }
        courseService.deleteCourseById(id);
        return new ResponseEntity<Course> (HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Course> updatecoursePartially(@PathVariable("id") long id,
                                                        @RequestBody Course currentcourse) {
        Optional<Course> optcourse = courseService.findById(id);
        Course course = optcourse.get();
        if (course == null) {
            return new ResponseEntity<Course> (HttpStatus.NOT_FOUND);
        }
        Course usr = courseService.updatePartially(currentcourse, id);
        return new ResponseEntity<Course> (usr, HttpStatus.OK);
    }
}
