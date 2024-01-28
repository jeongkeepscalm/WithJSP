package practice.itemService.usingJsp.login.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;
import practice.itemService.usingJsp.login.dto.SaveUserRequest;
import practice.itemService.usingJsp.login.dto.User;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // 컨트롤러, API 테스트
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void signUp() throws Exception {
        ResultActions result = mockMvc.perform(post("/login/signUp.cm")
                .param("id", "spring2")
                .param("password", "spring1234")
                .param("name", "spring")
                .param("age", "33")
                .param("bloodType", "A")
                .param("sex", "M")
                .param("email", "ojg@naver.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());

//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Expected Response"));

    }

    @Test
    public void testApi() throws Exception {

//        ObjectMapper objectMapper = new ObjectMapper();
//
//        User user = new User();
//        user.setId("test");
//
//        String userStringified = objectMapper.writeValueAsString(user);

        String requestBody = "{\"id\": \"test\"}";

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/login/selectDetail.cm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        String responseString = result.andReturn().getResponse().getContentAsString();
        System.out.println("API 응답: " + responseString);

    }

}