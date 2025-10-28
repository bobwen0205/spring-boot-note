package com.bob.cruddemo;

import com.bob.cruddemo.dao.AppDAO;
import com.bob.cruddemo.entity.Instructor;
import com.bob.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createInstructor(appDAO);
            // findInstructor(appDAO);
            deleteInstructor(appDAO);
        };
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
        // Instructor tempInstructor = new Instructor("Bob", "Wen", "wenmingzhi10@gmail.com");

        // create the instructor detail
        // InstructorDetail tempInstructorDetail = new InstructorDetail("bob.wen", "code");

        // create the instructor
        Instructor tempInstructor = new Instructor("Cathy", "Wang", "wzx20000502@gmail.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("cathy.wang", "painting");


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
