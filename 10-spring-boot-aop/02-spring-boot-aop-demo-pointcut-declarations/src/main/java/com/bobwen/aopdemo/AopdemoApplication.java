package com.bobwen.aopdemo;

import com.bobwen.aopdemo.dao.AccountDAO;
import com.bobwen.aopdemo.dao.MembershipDAO;
import com.bobwen.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
                                               MembershipDAO theMembershipDAO,
                                               TrafficFortuneService theTrafficFortuneService) {
        return runner -> {
            // demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
            // demoTheAfterReturningAdvice(theAccountDAO);
            // demoTheAfterThrowingAdvice(theAccountDAO);
            // demoTheAfterAdvice(theAccountDAO);
            // demoTheRoundAdvice(theTrafficFortuneService);
            // demoTheAroundAdviceHandleException(theTrafficFortuneService);
            demoTheAroundAdviceRethrowException(theTrafficFortuneService);
        };
    }

    private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune()");

        String data = null;
        try {
            boolean tripWire = true;
            data = theTrafficFortuneService.getFortune(tripWire);
        } catch (Exception exc) {
            System.out.println("\nMain Program: demoTheAroundAdviceRethrowException " + exc.getMessage());
        }

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune()");

        boolean tripWire = true;
        String data = theTrafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheRoundAdvice(TrafficFortuneService theTrafficFortuneService) {

        System.out.println("\nMain Program: demoTheRoundAdvice");
        System.out.println("Calling getFortune()");
        String data = theTrafficFortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
        // call method to find the accounts
        List<Account> theAccounts = null;
        try {
            // add a boolean flag to simulate exceptions
            boolean tripWire = false;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain Program ... caught an exception: " + exc);
        }


        // display theAccounts
        System.out.println("\n\nMain Program: demoTheAfterAdvice");
        System.out.println("-".repeat(88));
        System.out.println(theAccounts);
        System.out.println("\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
        // call method to find the accounts
        List<Account> theAccounts = null;
        try {
            // add a boolean flag to simulate exceptions
            boolean tripWire = true;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain Program ... caught an exception: " + exc);
        }


        // display theAccounts
        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
        System.out.println("-".repeat(88));
        System.out.println(theAccounts);
        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
        // call method to find the accounts
        List<Account> theAccounts = theAccountDAO.findAccounts();

        // display theAccounts
        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("-".repeat(88));
        System.out.println(theAccounts);
        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        // call the business method
        theAccountDAO.addAccount(new Account("Bob", "CEO"), true);

        theAccountDAO.doWork();

        // call the AccountDAO getter/setter methods
        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");
        String name = theAccountDAO.getName();
        String code = theAccountDAO.getServiceCode();

        // call the membership business method
        theMembershipDAO.addAccount();
        theMembershipDAO.goToSleep();

    }

}
