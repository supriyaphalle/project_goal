package com.project.goal;


import com.project.goal.controller.GoalController;
import com.project.goal.dto.GoalDto;
import com.project.goal.model.GoalData;
import com.project.goal.service.implementation.GoalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = GoalController.class)
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @MockBean
    GoalService goalService;

    GoalDto goalDto;

    @Autowired
    MockMvc mockMvc;

    @Test
    void givenBookData_WhenInserted_ReturnProperMessage() throws Exception {

        GoalDto dto = new GoalDto();
        GoalData  data = new GoalData(dto);
        String goalDetailsDto= new Gson().toJson(data);
        Mockito.when(goalService.createTarget(any())).thenReturn("Created Successful");


    }

//    String bookStoreDto=new Gson().toJson(this.bookDto);
//        Mockito.when(adminService.addBook(any())).thenReturn("Inserted Successful");
//    MvcResult result = this.mockMvc.perform(post("/admin/book")
//            .content(bookStoreDto)
//            .contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andReturn();
//        Assert.assertEquals(200,result.getResponse().getStatus());
//        Assert.assertEquals("Book Added Successfully",
//                new Gson().fromJson(result.getResponse().getContentAsString(), Response.class).message);
//}

}
