package com.dingweibing.user.service;

import com.dingweibing.user.model.Favorite;

import java.util.List;

public interface FavoritesQueryService {
    public List<Favorite> findByUsername(String username);
}
