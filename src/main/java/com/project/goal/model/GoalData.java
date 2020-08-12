package com.project.goal.model;

import com.project.goal.dto.GoalDto;

import javax.persistence.*;


@Entity
@Table(name = "goaldetails")
public class GoalData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public String financialGoal;
    public Double targetAmount;

    @ManyToOne()
    @JoinColumn(name = "userId", nullable = false)
    public User user;

    public GoalData(GoalDto goalDto, User user) {
        this.financialGoal = goalDto.financialGoal;
        this.targetAmount = goalDto.targetAmount;
        this.user = user;
    }

}
