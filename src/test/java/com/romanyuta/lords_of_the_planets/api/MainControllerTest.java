package com.romanyuta.lords_of_the_planets.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"create-data-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"create-data-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @Test
    void lazyLordListTest() throws Exception{
        this.mockMvc.perform(get("/lazyLord"))
                .andDo(print())
                .andExpect(xpath("//tr[@id='lord-id']/*").nodeCount(10));

    }

    @Test
    void youngLordListTest() throws Exception{
        this.mockMvc.perform(get("/youngLord"))
                .andDo(print())
                .andExpect(xpath("//tr[@id='lord-id']/td[@class='lord-name']").nodeCount(10))
                .andExpect(xpath("//tr[@id='lord-id']/td/text()").string("Адипоулз"));

    }
}