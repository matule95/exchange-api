package mz.co.checkmob.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import mz.co.checkmob.api.AbstractTest;
import mz.co.checkmob.api.endpoint.domain.CreateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.EndpointMapper;
import mz.co.checkmob.api.endpoint.domain.UpdateEndpointCommand;
import mz.co.checkmob.api.endpoint.service.EndpointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@Transactional
public class EndpointControllerTests extends AbstractTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EndpointService endpointService;

    @Test
    void itShouldCreateEndpoint() throws Exception {
        // given
        CreateEndpointCommand command = anyCreateEndpointCommand();
        String data = new ObjectMapper().writeValueAsString(command);

        // when
        when(endpointService.create(command)).thenReturn(EndpointMapper.INSTANCE.mapToModel(command));
        ResultActions response = mvc.perform(post("/api/v1/endpoints")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));

        // then
        response.andExpect(status().isCreated());
    }

    @Test
    void itShouldUpdateEndpoint() throws Exception {
        // given
        UpdateEndpointCommand command = anyUpdateEndpointCommand();
        String data = new ObjectMapper().writeValueAsString(command);
        Endpoint endpoint = new Endpoint();
        endpoint.setId(command.getId());
        endpoint.setDataReader(command.getDataReader());
        endpoint.setUrl(command.getUrl());

        // when
        when(endpointService.update(command)).thenReturn(endpoint);
        ResultActions response = mvc.perform(put("/api/v1/endpoints")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void  itShouldGetEndpointById() throws Exception {
        //given
        Endpoint endpoint = anyEndpoint();
        String url = String.format("/api/v1/endpoints/%s",endpoint.getId());

        // when
        when(endpointService.findById(endpoint.getId())).thenReturn(endpoint);
        ResultActions response = mvc.perform(get(url));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(endpoint.getId()));
    }

    @Test
    void  itShouldGetPageOfEndpoints() throws Exception {
        List<Endpoint> endpoints = new ArrayList<>();
        endpoints.add(anyEndpoint());
        endpoints.add(anyEndpoint());
        endpoints.add(anyEndpoint());
        Page<Endpoint> page = new PageImpl<>(endpoints);

        // when
        when(endpointService.findAll(any())).thenReturn(page);
        ResultActions response = mvc.perform(get("/api/v1/endpoints"));
        // then
        response.andExpect(status().isOk());
    }

    @Test
    void itShouldDeleteASingleEndpoint() throws Exception {
        // when
        ResultActions result = mvc.perform(MockMvcRequestBuilders.delete(String.format("/api/v1/endpoints/%s", anyEndpoint().getId())));

        // then
        result.andExpect(status().isNoContent());
    }
}
