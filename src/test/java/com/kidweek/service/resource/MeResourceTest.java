package com.kidweek.service.resource;

import com.kidweek.service.KidweekApplication;
import com.kidweek.service.model.User;
import com.kidweek.service.service.FacebookService;
import com.kidweek.service.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.emptySet;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = KidweekApplication.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class MeResourceTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository userRepository;
    @MockBean
    FacebookService facebookService;

    @Test
    public void should_register_user() throws Exception {
        User user = new User("123456", "Ted Tester", emptySet(), emptySet());
        when(facebookService.getUser(eq("xyz"))).thenReturn(user);

        mvc.perform(
                post("/api/v1/me/register?access_token=xyz").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("123456")))
                .andExpect(jsonPath("$.name", is("Ted Tester")));

        verify(facebookService).getUser("xyz");
        assertThat(userRepository.findOne("123456"), notNullValue());
    }

    @Test
    public void should_get_info() throws Exception {
        User user = new User("123456", "Ted Tester", emptySet(), emptySet());
        userRepository.save(user);

        when(facebookService.getUser(eq("xyz"))).thenReturn(user);

        mvc.perform(
                get("/api/v1/me/info?access_token=xyz").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123456")))
                .andExpect(jsonPath("$.name", is("Ted Tester")));

        verify(facebookService).getUser("xyz");
    }
}