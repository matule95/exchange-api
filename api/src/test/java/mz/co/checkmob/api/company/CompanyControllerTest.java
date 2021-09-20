package mz.co.checkmob.api.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import mz.co.checkmob.api.company.domain.Company;
import mz.co.checkmob.api.company.domain.CompanyMapper;
import mz.co.checkmob.api.company.domain.UpdateCompanyCommand;
import mz.co.checkmob.api.company.domain.query.CompanyQuery;
import mz.co.checkmob.api.company.service.CompanyServiceImpl;
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
public class CompanyControllerTest extends BaseCompanyTest{
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CompanyServiceImpl companyService;

    @Test
    @WithMockUser
    void shouldListAllCompany() throws Exception {
        CompanyQuery companyQuery = new CompanyQuery();
        Pageable pageable = Pageable.unpaged();
        Mockito.when(companyService.fetchCompanies(pageable,companyQuery)).thenReturn(getAnyCompanyPage());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/companies",pageable,companyQuery));
        result.andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldShowCompanyDetails() throws Exception {
        Company company = getAnyCompany();
        company.setId(1L);
        given(companyService.fetchCompany(any())).willReturn(CompanyMapper.INSTANCE.mapToJson(company));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/v1/companies/%s", ThreadLocalRandom.current().nextLong(10,100))));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("id").value(company.getId().toString()));
    }

    @Test
    @WithMockUser
    void shouldCreateCompany() throws Exception {
        Company company = getAnyCompany();
        company.setId(1L);
        given(companyService.create(any())).willReturn(CompanyMapper.INSTANCE.mapToJson(company));
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
        Company company = getAnyCompany();
        company.setId(1L);
        UpdateCompanyCommand command = getAnyUpdateCompanyCommand();
        String url = String.format("/api/v1/companies/%s", company.getId());
        String data = new ObjectMapper().writeValueAsString(command);
        when(companyService.update(any(),any())).thenReturn(CompanyMapper.INSTANCE.mapToJson(company));
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