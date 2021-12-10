package mz.co.exchange.api.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import mz.co.exchange.api.provider.domain.Provider;
import mz.co.exchange.api.provider.domain.ProviderMapper;
import mz.co.exchange.api.provider.domain.UpdateProviderCommand;
import mz.co.exchange.api.provider.domain.query.ProviderQuery;
import mz.co.exchange.api.provider.service.ProviderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProviderControllerTest extends BaseCompanyTest{
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ProviderServiceImpl companyService;

    @Test
    @WithMockUser
    void shouldListAllCompany() throws Exception {
        ProviderQuery providerQuery = new ProviderQuery();
        Pageable pageable = Pageable.unpaged();
        Mockito.when(companyService.fetchProviders(pageable, providerQuery)).thenReturn(getAnyCompanyPage());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/companies",pageable, providerQuery));
        result.andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldShowCompanyDetails() throws Exception {
        Provider provider = getAnyCompany();
        provider.setId(1L);
        given(companyService.fetchProvider(any())).willReturn(ProviderMapper.INSTANCE.mapToJson(provider));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/v1/companies/%s", ThreadLocalRandom.current().nextLong(10,100))));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("id").value(provider.getId().toString()));
    }

    @Test
    @WithMockUser
    void shouldCreateCompany() throws Exception {
        Provider provider = getAnyCompany();
        provider.setId(1L);
        given(companyService.create(any())).willReturn(ProviderMapper.INSTANCE.mapToJson(provider));
        String data = new ObjectMapper().writeValueAsString(getCreateCompanyCommand());
        ResultActions perform = mockMvc.perform(post("/api/v1/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));
        perform
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    @WithMockUser
    void shouldUpdateCompany () throws Exception {
        Provider provider = getAnyCompany();
        provider.setId(1L);
        UpdateProviderCommand command = getAnyUpdateCompanyCommand();
        String url = String.format("/api/v1/companies/%s", provider.getId());
        String data = new ObjectMapper().writeValueAsString(command);
        when(companyService.update(any(),any())).thenReturn(ProviderMapper.INSTANCE.mapToJson(provider));
        ResultActions result = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(data));
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    @WithMockUser
    void shouldDeleteCompany() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/v1/companies/%s", ThreadLocalRandom.current().nextLong(10,100))));
        result.andExpect(status().isOk());
    }
}
