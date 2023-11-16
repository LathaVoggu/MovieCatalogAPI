package com.amigo.ratedataservice.controller;

import com.amigo.ratedataservice.model.Rating;
import com.amigo.ratedataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

    @RequestMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
         List<Rating> rating = Arrays.asList(
                new Rating("2222",4),
                new Rating ("3333",5)
        );
         UserRating userRating = new UserRating();
         userRating.setUserRating(rating);
        return userRating;
    }
}
