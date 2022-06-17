package com.dingweibing.user.api.controller.command;

import com.dingweibing.user.api.FavoritesCommandApi;
import com.dingweibing.user.service.FavoritesCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-06-01T09:20:51.512Z")

@Controller
public class FavoritesCommandController implements FavoritesCommandApi {
    private final FavoritesCommandService favoritesCommandService;

    @Inject
    public FavoritesCommandController(FavoritesCommandService favoritesCommandService) {
        this.favoritesCommandService = favoritesCommandService;
    }

    @Override
    public ResponseEntity<Void> favoritesMovieIdPost(String movieId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        favoritesCommandService.addFavoriteMovieToUser(userDetails.getUsername(), movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
