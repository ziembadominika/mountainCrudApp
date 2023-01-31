package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.UserRatingDto;
import com.crudApp.mountain.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mountainApp")
public class UserRatingController {

    @Autowired
    private UserRatingService userRatingService;

//    @GetMapping(name = "/getUserRatingList")
//    public List<UserRatingDto> getUserRatingList(){
//        return userRatingService.getAllUserRatings();
//    }
//
//    @GetMapping(name = "/getUserRating")
//    public UserRatingDto getUserRating(@RequestParam Long ratingId){
//        return userRatingService.getUserRating(ratingId);
//    }
//
//    @PostMapping(name = "/addUserRating")
//    public void addUserRating(@RequestBody UserRatingDto userRatingDto){
//        userRatingService.addUserRating(userRatingDto);
//    }
//
//    @PutMapping(name = "/updateUserRating")
//    public UserRatingDto updateUserRating(@RequestBody UserRatingDto userRatingDto){
//        return userRatingService.updateUserRating(userRatingDto);
//    }
//
//    @DeleteMapping(name = "/deleteUserRating")
//    public void deleteUserRating(@RequestParam Long ratingId){
//        userRatingService.deleteUserRating(ratingId);
//    }
}
