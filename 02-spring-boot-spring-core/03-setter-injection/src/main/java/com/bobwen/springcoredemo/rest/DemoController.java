package com.bobwen.springcoredemo.rest;

import com.bobwen.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // define a private field for the dependency
    private Coach coach;

    // define a setter injection
//    @Autowired
//    public void setCoach(Coach coach) {
//        this.coach = coach;
//    }

    // no matter what name you have on setter function
    @Autowired
    public void doSomeStuff(Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }
}
