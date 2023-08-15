package preonboard.preonboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import preonboard.preonboard.dto.MemberJoinRequest;
import preonboard.preonboard.dto.MemberLoginRequest;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    protected MockMvc mvc; // 통합 테스트를 위한 라이브러리


    @Autowired
    protected ObjectMapper objectMapper;


    @BeforeEach
    public void 회원가입() throws Exception {

        //given
        String email = "preon@naver.com";
        String password = "12345678";
        MemberJoinRequest memberJoinRequest = new MemberJoinRequest(email, password);


        //when
        ResultActions resultActions = mvc.perform(post("/app/member/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberJoinRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());


        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("회원 가입 완료"));

    }


    @Test
    public void 로그인() throws Exception {
        //given
        String email = "preon@naver.com";
        String password = "12345678";
        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(email, password);


        //when
        ResultActions resultActions = mvc.perform(get("/app/member/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberLoginRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());


        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(startsWith("Bearer ")));




    }



}