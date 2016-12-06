package com.simplerest;

import com.simplerest.config.AppBoot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppBoot.class})
public class SimpleRestControllerTest {

    private static Logger log = LogManager.getLogger(SimpleRestControllerTest.class);

    private final static String CONTENT_TYPE_TEXT_UTF8 = "text/plain;charset=UTF-8";
    private final static String CONTENT_TYPE_UTF8 = "application/json;charset=UTF-8";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void test_get() throws Exception {
        mvc.perform(get("/employee/MonaLisa"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_post() throws Exception{
        mvc.perform(post("/employee")
                .content("{\"name\": \"MonaLisa\", \"surname\" : \"Jose\",\"age\": \"30\", \"startdate\": \"1503-01-01\" }")
                .contentType(CONTENT_TYPE_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_delete() throws Exception{
        mvc.perform(delete("/employee/MonaLisa")
                .contentType(CONTENT_TYPE_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_put() throws Exception{
        mvc.perform(put("/employee")
                .content("{\"name\": \"MonaLisa\", \"surname\" : \"ChangeSurname\",\"age\": \"30\", \"startdate\": \"1503-01-01\" }")
                .contentType(CONTENT_TYPE_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
