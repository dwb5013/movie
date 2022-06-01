package com.dingweibing.user.service;

import com.dingweibing.user.model.Favorite;
import com.dingweibing.user.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class FavoritesCommandServiceImpl implements FavoritesCommandService {
    private final FavoriteRepository favoriteRepository;

    @Inject
    public FavoritesCommandServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public void addFavoriteMovieToUser(String username, String movieId) {
        Favorite favorite = new Favorite();
        favorite.setUsername(username);
        favorite.setMovieId(movieId);
        favoriteRepository.save(favorite);
    }
}
