package com.amigo.moviecatalogservice.controller;

import com.amigo.moviecatalogservice.model.CatalogItem;
import com.amigo.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.amigo.moviecatalogservice.model.Rating;
import com.amigo.moviecatalogservice.model.Movie;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
//        List<Rating> ratings = Arrays.asList(
//                new Rating("2222",4),
//                new Rating ("3333",5)
//        );

        //Instead of hardcoding ew are going to add rest to call to get list of ratings for user
        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
        return userRating.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(),"TheFirstAvenger",rating.getRating());
        }).collect(Collectors.toList());

    }
}
