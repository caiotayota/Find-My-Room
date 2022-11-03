package com.caiotayota.findaroom.repositories;

import com.caiotayota.findaroom.entities.Ad;
import com.caiotayota.findaroom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findByUser(User user);

    List<Ad> findByParking(boolean parking);
}
