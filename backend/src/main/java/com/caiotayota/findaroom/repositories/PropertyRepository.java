package com.caiotayota.findaroom.repositories;

import com.caiotayota.findaroom.entities.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
