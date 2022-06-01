package com.dingweibing.user.repository;

import com.dingweibing.user.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    public List<Favorite> findByUsername(String username);
}
