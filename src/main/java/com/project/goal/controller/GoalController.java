package com.project.goal.controller;


import com.project.goal.dto.GoalDto;
import com.project.goal.model.GoalData;
import com.project.goal.response.Response;
import com.project.goal.service.implementation.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/admin")
@RestController
public class GoalController {
    @Autowired
    GoalService goalService;

    @PostMapping(value = "/goal")
    public Response createTarget(@RequestBody GoalDto goalDto, @RequestHeader String token) {
        Response response = goalService.createTarget(goalDto, token);
        return response;
    }

    @PutMapping("/goal")
    public Response editTarget(@RequestBody GoalDto goalDto, @RequestHeader String token) {
        Response response = goalService.updateTarget(goalDto, token);
        return response;
    }

    @GetMapping("/goal")
    public Response showTarget(@RequestHeader String token) {
        Response response = goalService.getAllTargets(token);
        return response;
    }

}

