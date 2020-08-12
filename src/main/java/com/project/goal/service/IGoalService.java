package com.project.goal.service;

import com.project.goal.dto.GoalDto;
import com.project.goal.model.GoalData;
import com.project.goal.response.Response;


public interface IGoalService {
    Response createTarget(GoalDto goalDto, String token);

    Response updateTarget(GoalDto goalDto, String token);

    Response getAllTargets(String token);


}
