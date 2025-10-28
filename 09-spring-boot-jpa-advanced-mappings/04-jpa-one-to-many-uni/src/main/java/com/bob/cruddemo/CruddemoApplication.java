package com.bob.cruddemo;

import com.bob.cruddemo.dao.AppDAO;
import com.bob.cruddemo.entity.Course;
import com.bob.cruddemo.entity.Instructor;
import com.bob.cruddemo.entity.InstructorDetail;
import com.bob.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createCourseAndReviews(appDAO);
            // retrieveCourseAndReviews(appDAO);
            deleteCourseAndReviews(appDAO);
        };
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Deleting course id: " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
        // print the course
        System.out.println(tempCourse);
        // print the reviews
        System.out.println(tempCourse.getReviews());
        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("NextJS: from 0 to master");

        // add some reviews
        tempCourse.addReview(new Review("Great course"));
        tempCourse.addReview(new Review("Cool course"));
        tempCourse.addReview(new Review("What a dumb course"));

        // save the course ... and leverage the cascade all
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
        appDAO.save(tempCourse);
        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 11;
        System.out.println("Deleting course with id " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;
        // find the course
        System.out.println("Finding course by id " + theId);
        Course tempCourse = appDAO.findCourseById(theId);
        // update the course
        System.out.println("Updating course id: " + theId);
        tempCourse.setTitle("Enjoy coding");
        appDAO.update(tempCourse);
        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        // find the instructor
        System.out.println("Finding instructor id " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        // update the instructor
        System.out.println("Updating instructor id: " + theId);
        tempInstructor.setFirstName("Mingzhi");
        appDAO.update(tempInstructor);
        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        // find the instructor
        System.out.println("Finding instructor with id " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        // find instructor
        System.out.println("Finding instructor with id " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        // find courses for instructor
        System.out.println("Finding courses for instructor with id " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        // associate the objects
        tempInstructor.setCourses(courses);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor with id " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor = new Instructor("Bob", "Wen", "wenmingzhi10@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("bob.wen", "code");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Java");
        Course tempCourse2 = new Course("Java Script");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        //
        // NOTE: this will ALSO save the courses
        // because of CascadeType.PERSIST
        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Deleting InstructorDetail id: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        // get the instructor detail object
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
        // print the instructor detail
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        // print the associated instructor
        System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());
        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting Instructor with id: " + theId);
        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Finding instructor id " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("Found instructor " + tempInstructor);
        System.out.println("The associate instructorDetail only: " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor = new Instructor("Bob", "Wen", "wenmingzhi10@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("bob.wen", "code");

        // create the instructor
        // Instructor tempInstructor = new Instructor("Cathy", "Wang", "wzx20000502@gmail.com");

        // create the instructor detail
        // InstructorDetail tempInstructorDetail = new InstructorDetail("cathy.wang", "painting");


        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        //
        // NOTE: this will ALSO save the details object
        // because of CascadeType.ALL
        //
        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }

}
