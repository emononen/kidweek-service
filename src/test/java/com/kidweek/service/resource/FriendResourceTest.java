package com.kidweek.service.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kidweek.service.model.Pattern;
import com.kidweek.service.model.Status;
import com.kidweek.service.service.FacebookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static com.kidweek.service.model.Status.AWAY;
import static com.kidweek.service.model.Status.PRESENT;
import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FriendResourceTest {


    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void should_return_calendar() throws Exception {
        Pattern pattern = new Pattern(LocalDate.now(), Arrays.asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, PRESENT));
        System.out.println(mapper.writeValueAsString(pattern));

/*
        mvc.perform(
                get("/api/v1/friends/xx/calendar/2011-12").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

*/

    }

}