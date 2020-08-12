package com.project.goal.repository;

import com.project.goal.model.GoalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IGoalRepository extends JpaRepository<GoalData, Integer> {

    Optional<GoalData> findByFinancialGoal(String financialGoal);

    @Query(value = "select id from GoalData where user_id=:userId", nativeQuery = true)
    List<Integer> getAllTargets(@Param("userId") int id);


}


