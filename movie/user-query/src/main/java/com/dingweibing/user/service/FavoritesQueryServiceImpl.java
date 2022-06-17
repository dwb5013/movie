package com.dingweibing.user.service;

import com.dingweibing.user.model.Favorite;
import com.dingweibing.user.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesQueryServiceImpl implements FavoritesQueryService {
    private final FavoriteRepository favoriteRepository;

    public FavoritesQueryServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public List<Favorite> findByUsername(String username) {
        return favoriteRepository.findByUsername(username);
    }
}
