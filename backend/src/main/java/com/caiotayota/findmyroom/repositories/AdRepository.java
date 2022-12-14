package com.caiotayota.findmyroom.repositories;

import com.caiotayota.findmyroom.entities.Ad;
import com.caiotayota.findmyroom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByUser(User user);
    List<Ad> findByParking(boolean parking);

    @Query("SELECT obj FROM Ad obj ORDER BY obj.createdAt DESC")
    List<Ad> findAds();
}
