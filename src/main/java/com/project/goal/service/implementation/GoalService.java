package com.project.goal.service.implementation;


import com.project.goal.dto.GoalDto;
import com.project.goal.exception.GoalException;
import com.project.goal.model.GoalData;
import com.project.goal.model.User;
import com.project.goal.repository.IGoalRepository;
import com.project.goal.repository.IUserRepository;
import com.project.goal.response.Response;
import com.project.goal.service.IGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService implements IGoalService {

    @Autowired
    IGoalRepository goalRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    JwtToken jwtToken;

    @Override
    public Response createTarget(GoalDto goalDto, String token) {
        if (token != null) {
            jwtToken.validateToken(token);
            int userId = jwtToken.getUserId();
            Optional<User> userid = userRepository.findUserById(userId);
            User user = userid.get();
            GoalData goalData = new GoalData(goalDto, user);
            Optional<GoalData> goal = goalRepository.findByFinancialGoal(goalDto.financialGoal);
            if (goal.isPresent()) {
                throw new GoalException("GOAL ALREADY EXISTS", GoalException.ExceptionType.GOAL_ALREADY_EXISTS);
            }
            goalRepository.save(goalData);
            Response response = new Response("Target Created Successfully", 200, "");
            return response;
        }
        throw new GoalException("Please Login ", GoalException.ExceptionType.PLEASE_LOGIN);

    }

    @Override
    public Response updateTarget(GoalDto goalDto, String token) {
        if (token != null) {

            Optional<GoalData> goal = goalRepository.findByFinancialGoal(goalDto.financialGoal);

            if (goal.isPresent()) {
                GoalData goalData1 = goal.get();
                goalData1.financialGoal = goalDto.financialGoal;
                goalData1.targetAmount = goalDto.targetAmount;
                goalRepository.save(goalData1);
                return new Response("Target updated Successfully", 200, "");
            }
            throw new GoalException("TARGET DOESNOT EXIT", GoalException.ExceptionType.TARGET_DOESNOT_EXIT);
        }
        throw new GoalException("Please Login ", GoalException.ExceptionType.PLEASE_LOGIN);

    }

    @Override
    public Response getAllTargets(String token) {
        if (token != null) {
            jwtToken.validateToken(token);
            int userId = jwtToken.getUserId();
            Optional<User> userid = userRepository.findUserById(userId);
            User user = userid.get();
            List<Integer> targetsID = goalRepository.getAllTargets(user.id);
            List<GoalData> targetData = new ArrayList<>();
            for (int i = 0; i < targetsID.size(); i++) {
                Optional<GoalData> data = goalRepository.findById(targetsID.get(i));
                GoalData goalData = data.get();
                targetData.add(goalData);
            }
            Response response = new Response("all target data", 200, targetData);
            return response;

        }
        throw new GoalException("Please Login ", GoalException.ExceptionType.PLEASE_LOGIN);

    }


}
