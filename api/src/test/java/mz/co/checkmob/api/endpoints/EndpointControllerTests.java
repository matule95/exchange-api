package mz.co.checkmob.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import mz.co.checkmob.api.AbstractTest;
import mz.co.checkmob.api.endpoint.domain.CreateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.EndpointMapper;
import mz.co.checkmob.api.endpoint.domain.UpdateEndpointCommand;
import mz.co.checkmob.api.endpoint.service.EndpointService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class EndpointControllerTests extends AbstractTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    EndpointService endpointService;

    @Test
    void itShouldCreateEndpoint() throws Exception {
        // given
        CreateEndpointCommand command = anyCreateEndpointCommand();
        String data = new ObjectMapper().writeValueAsString(command);

        // when
        ResultActions response = mvc.perform(post("/api/v1/endpoints")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));

        // then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void itShouldUpdateEndpoint() throws Exception {
        // given
        UpdateEndpointCommand command = anyUpdateEndpointCommand();
        String data = new ObjectMapper().writeValueAsString(command);

        // when
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

        Endpoint endpoint = anyEndpoint();
        Mockito.when(endpointService.findById(any())).thenReturn(endpoint);

        // when
        ResultActions response = mvc.perform(get("/api/v1/endpoints/1")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(endpoint.getId()));
    }

    @Test
    void  itShouldGetPageOfEndpoints() throws Exception {

        List<Endpoint> list = new ArrayList();
        list.add(anyEndpoint());
        list.add(anyEndpoint());
        list.add(anyEndpoint());
        list.add(anyEndpoint());
        Page<Endpoint> page = new PageImpl<>(list,Pageable.unpaged(),list.size());
        Mockito.when(endpointService.findAll(any())).thenReturn(page);
        // when
        ResultActions response = mvc.perform(get("/api/v1/endpoints/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @Test
    void itShouldDeleteASingleEndpoint() throws Exception {
        // when
        ResultActions result = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/endpoints/1"));

        // then
        result.andExpect(status().isNoContent());
    }
}
