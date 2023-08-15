package preonboard.preonboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import preonboard.preonboard.dto.EditPostRequest;
import preonboard.preonboard.dto.MemberJoinRequest;
import preonboard.preonboard.dto.MemberLoginRequest;
import preonboard.preonboard.dto.WritePostRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostControllerTest {

    @Autowired
    protected MockMvc mvc; // 통합 테스트를 위한 라이브러리

    @Autowired
    protected ObjectMapper objectMapper;


    @BeforeEach
    void writePost() throws Exception {
        // 회원 가입
        String email = "user@example.com";
        String password = "12345678"; // Hashed password or actual password
        MemberJoinRequest memberJoinRequest = new MemberJoinRequest(email, password);

        ResultActions signUpResult = mvc.perform(post("/app/member/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberJoinRequest))
                .accept(MediaType.APPLICATION_JSON));

        // 로그인
        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(email, password);

        ResultActions loginResult = mvc.perform(get("/app/member/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberLoginRequest))
                .accept(MediaType.APPLICATION_JSON));

        MvcResult loginMvcResult = loginResult.andReturn();
        String response = loginMvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(response);
        String jwtToken = jsonObject.getString("result");


        // 작성한 글
        String title = "Test Title";
        String content = "Test Content";
        WritePostRequest writePostRequest = new WritePostRequest(title, content);

        ResultActions resultActions = mvc.perform(post("/app/post/write-post")
                .header("Authorization",  jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(writePostRequest))
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("글 작성 성공"));
    }

    @Test
    void getAllPost() throws Exception {
        //given
        // When
        ResultActions resultActions = mvc.perform(get("/app/post/getAll")
                .accept(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(status().isOk());

    }

    @Test
    void getOnePost() throws Exception {
        //given
        Long id = 1L;

        //when
        ResultActions resultActions = mvc.perform(get("/app/post/getOne/{id}", id)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username="user@example.com")
    void editPost() throws Exception {
        // Given
        Long postId = 1L;
        String updatedTitle = "Updated Title";
        String updatedContent = "Updated Content";
        EditPostRequest editPostRequest = new EditPostRequest(postId,updatedTitle, updatedContent);

        // When
        ResultActions resultActions = mvc.perform(put("/app/post/editPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(editPostRequest))
                .accept(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username="user@example.com")
    void deletePost() throws Exception {
        // Given
        Long id = 1L;

        // When
        ResultActions resultActions = mvc.perform(delete("/app/post/deletePost/{id}", id)
                .accept(MediaType.APPLICATION_JSON));

        // Then
        resultActions.andExpect(status().isOk());

    }




}