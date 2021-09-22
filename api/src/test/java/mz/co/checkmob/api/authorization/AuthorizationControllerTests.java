package mz.co.checkmob.api.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import mz.co.checkmob.api.AbstractTest;
import mz.co.checkmob.api.authorization.domain.Authorization;
import mz.co.checkmob.api.authorization.domain.AuthorizationCommand;
import mz.co.checkmob.api.authorization.domain.AuthorizationMapper;
import mz.co.checkmob.api.authorization.service.AuthorizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class AuthorizationControllerTests extends AbstractTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorizationService authorizationService;

    @Test
    void itShouldCreateAuthorization() throws Exception {
        // given
        AuthorizationCommand command = anyAuthorizationCommand();
        String data = new ObjectMapper().writeValueAsString(command);

        // when
        when(authorizationService.create(command)).thenReturn(AuthorizationMapper.INSTANCE.mapToModel(command));
        ResultActions response = mvc.perform(post("/api/v1/authorizations")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));

        // then
        response.andExpect(status().isCreated());
    }

    @Test
    void itShouldUpdateAuthorization() throws Exception {
        // given
        AuthorizationCommand command = anyAuthorizationCommand();
        String data = new ObjectMapper().writeValueAsString(command);
        Authorization authorization = anyAuthorization();
        authorization.setAuthJson(command.getAuthJson());
        authorization.setType(command.getType());
        String url = String.format("/api/v1/authorizations/%s", authorization.getId());

        // when
        when(authorizationService.update(authorization.getId(), command)).thenReturn(authorization);
        ResultActions response = mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void  itShouldGetAuthorizationById() throws Exception {
        //given
        Authorization authorization = anyAuthorization();
        String url = String.format("/api/v1/authorizations/%s", authorization.getId());

        // when
        when(authorizationService.findById(authorization.getId())).thenReturn(authorization);
        ResultActions response = mvc.perform(get(url));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(authorization.getId()));
    }

    @Test
    void  itShouldGetPageOfAuthorizations() throws Exception {
        List<Authorization> authorizations = new ArrayList<>();
        authorizations.add(anyAuthorization());
        authorizations.add(anyAuthorization());
        authorizations.add(anyAuthorization());
        Page<Authorization> page = new PageImpl<>(authorizations);

        // when
        when(authorizationService.findAll(any())).thenReturn(page);
        ResultActions response = mvc.perform(get("/api/v1/authorizations"));
        // then
        response.andExpect(status().isOk());
    }

    @Test
    void itShouldDeleteASingleAuthorization() throws Exception {
        // when
        ResultActions result = mvc.perform(MockMvcRequestBuilders.delete(String.format("/api/v1/authorizations/%s", anyAuthorization().getId())));

        // then
        result.andExpect(status().isNoContent());
    }
}
