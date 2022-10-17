package com.caiotayota.findaroom.repositories;

import com.caiotayota.findaroom.entities.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, String> {
}
