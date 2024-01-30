package practice.itemService.usingJsp.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest 어노테이션으로 api 테스트를 해봤지만 service 에 빈이 주입되어있지 않다는 오류를 계속 내뱉음.
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    // Controller
    @Test
    void signUp() throws Exception {
        ResultActions result = mockMvc.perform(post("/signUp")
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
    }

    // ResponseBody
    @Test
    public void testApi() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "test");
        String userStringified = objectMapper.writeValueAsString(map);

        ResultActions result = mockMvc.perform(post("/selectDetail.cm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userStringified));

        String responseString = result.andReturn().getResponse().getContentAsString();
        System.out.println("API response: " + responseString);

    }

}