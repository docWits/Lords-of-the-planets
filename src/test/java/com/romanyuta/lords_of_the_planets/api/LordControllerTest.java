package com.romanyuta.lords_of_the_planets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanyuta.lords_of_the_planets.model.Lord;
import com.romanyuta.lords_of_the_planets.model.LordForm;
import com.romanyuta.lords_of_the_planets.repository.LordRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@TestPropertySource("/application-test.properties")
@Sql(value = {"create-data-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"create-data-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class LordControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void lordList() throws Exception {
        this.mockMvc.perform(get("/lordList"))
                .andDo(print())
                .andExpect(xpath("//tr[@id='lord-list']/td[@class='lord-name']").nodeCount(12));
    }


    @Test
    void saveLord() throws  Exception{

        this.mockMvc.perform(post("/addLord")
                .param("id","13")
                .param("name", "Каан")
                .param("age", "400")
                .flashAttr("lordForm", new LordForm()));

        this.mockMvc.perform(get("/lordList"))
                .andDo(print())
                .andExpect(xpath("//tr[@id='lord-list']/td[@class='lord-name']").nodeCount(13));
    }

}