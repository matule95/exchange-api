package mz.co.checkmob.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import mz.co.checkmob.api.user.domain.CreateUserCommand;
import mz.co.checkmob.api.user.persistence.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class UserIntegrationTests extends BaseUserTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    static UserRepository userRepository;

    @AfterAll
    static void afterAll() {
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    void itShouldCreateUser() throws Exception {
        // given
        CreateUserCommand command = getCreateUserCommand();
        String data = new ObjectMapper().writeValueAsString(command);

        // when
        ResultActions response = mvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));

        // then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.username").value(command.getUsername()));
    }

    @Test
    @Order(2)
    void  itShouldGetUserById() throws Exception {
        // when
        ResultActions response = mvc.perform(get("/api/v1/users/1" )
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("admin"));
    }

    @Test
    void  itShouldGetPageOfUsers() throws Exception {
        // when
        ResultActions response = mvc.perform(get("/api/v1/users/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @Test
    void itShouldDeleteASingleUser() throws Exception {
        // when
        ResultActions result = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1"));

        // then
        result.andExpect(status().isGone());
    }

    @Test
    void itShouldGiveAnErrorMessageWhenTryingToDeleteAnNonexistentUser() throws Exception {
        // when
        ResultActions result = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1000"));

        // then
        result.andExpect(status().isBadRequest());
    }
}
