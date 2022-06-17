package com.dingweibing.user.api.controller.query;

import com.dingweibing.user.api.FavoritesQueryApi;
import com.dingweibing.user.model.Favorite;
import com.dingweibing.user.model.Movie;
import com.dingweibing.user.service.FavoritesQueryService;
import com.dingweibing.user.service.MovieQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-06-01T09:20:51.512Z")

@Controller
public class FavoritesQueryController implements FavoritesQueryApi {
    private final FavoritesQueryService favoritesQueryService;
    private final MovieQueryService movieQueryService;

    @Inject
    public FavoritesQueryController(FavoritesQueryService favoritesQueryService, MovieQueryService movieQueryService) {
        this.favoritesQueryService = favoritesQueryService;
        this.movieQueryService = movieQueryService;
    }

    @Override
    public ResponseEntity<List<Movie>> listFavorites() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Favorite> byUsername = favoritesQueryService.findByUsername(userDetails.getUsername());
        if (byUsername == null || byUsername.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<String> movieIds = byUsername.stream().map(Favorite::getMovieId).collect(Collectors.toList());
        Optional<List<Movie>> movieById = movieQueryService.getMovieByIds(movieIds);
        if (movieById.isPresent()) {
            return new ResponseEntity<>(movieById.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
