package com.project.goal.dto;


//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
public class GoalDto {
    //    @NotEmpty(message = "Book name should not be null")
    public String financialGoal;

    //    @Min(value = 100,message = "Price must be greater than 100")
//    @NotNull(message = "Price should not be empty")
    public Double targetAmount;

    public GoalDto() {
    }

    public GoalDto(String financialGoal, Double targetAmount) {
        this.financialGoal = financialGoal;
        this.targetAmount = targetAmount;
    }


}
