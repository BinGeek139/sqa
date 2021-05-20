package com.ptit.sqa.mark;

import com.ptit.sqa.config.jwt.JwtAuthenticationFilter;
import com.ptit.sqa.entity.CustomUserDetails;
import com.ptit.sqa.entity.User;
import com.ptit.sqa.repo.ClazzRepository;
import com.ptit.sqa.repo.MarkRepository;
import com.ptit.sqa.repo.SemesterRepository;
import com.ptit.sqa.repo.UserRepository;
import com.ptit.sqa.service.impl.ClassServiceImpl;
import com.ptit.sqa.service.impl.MarkFormServiceImpl;
import com.ptit.sqa.service.impl.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class MarkControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private SemesterRepository semesterRepository ;

    @Autowired
    private ClassServiceImpl classService;

    @Autowired
    private MarkFormServiceImpl markFormService;

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private WebApplicationContext context;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        classService = mock(ClassServiceImpl.class);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()) // enable security for the mock set up
                .build();
    }

    @Test
    public void getMark_shouldFind5Classes() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("giaovien");
        user.setPassword("giaovien");
        user.setRole("ROLE_TEACHER");
        CustomUserDetails userDetails = new CustomUserDetails(user);
        MockHttpServletRequestBuilder requestBuilder = get("https://localhost/" + "/mark")
                .with(user(userDetails));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("mark"))
                .andExpect(model().attribute("classes", hasSize(5)))
                .andExpect(model().attribute("classes", hasItem(
                        allOf(
                            hasEntry("cname", "dbcl161"),
                            hasEntry("sname", "Đảm bảo chất lượng phần mềm")
                        )
                )));
    }

    @Test
    public void getMark_shouldNotFindAnyClasses() throws Exception{
        User user = new User();
        user.setId(10001L);
        user.setUsername("giaovien");
        user.setPassword("giaovien");
        user.setRole("ROLE_TEACHER");
        CustomUserDetails userDetails = new CustomUserDetails(user);

        MockHttpServletRequestBuilder requestBuilder = get("https://localhost/" + "/mark")
                .with(user(userDetails));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("mark"))
                .andExpect(model().attribute("classes", hasSize(0)));
    }

    @Test
    public void getClassDetail_shouldFind99Students() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("giaovien");
        user.setPassword("giaovien");
        user.setRole("ROLE_TEACHER");
        CustomUserDetails userDetails = new CustomUserDetails(user);

        MockHttpServletRequestBuilder requestBuilder = get("https://localhost/" + "/mark/dbcl161")
                .with(user(userDetails));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("classdetail"))
                .andExpect(model().attribute("markForm", hasProperty("spoints",
                    everyItem( hasProperty("id", notNullValue()))
                )))
                .andExpect(model().attribute("markForm", hasProperty("markResponses",
                        hasSize(99)
                )))
                .andExpect(model().attribute("markForm", hasProperty("markResponses",
                        hasItem(
                                allOf(
                                        hasProperty("classStudentId", notNullValue()),
                                        hasProperty("student",
                                                hasProperty("fullName", notNullValue()))
                                )
                        )
                )));
    }

//    @Test
//    public void update() throws Exception{
//        MarkForm markForm = new MarkForm();
//        MarkResponse markResponse = new MarkResponse();
//        markResponse.addMark();
////        markForm.getMarkResponses().
//    }
}
