package com.bob.cruddemo.dao;

import com.bob.cruddemo.entity.Course;
import com.bob.cruddemo.entity.Instructor;
import com.bob.cruddemo.entity.InstructorDetail;
import com.bob.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor theInstructor);

    void update(Course theCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCourseByStudentId(int theId);

    void update(Student theStudent);

    void deleteStudentById(int theId);
}
