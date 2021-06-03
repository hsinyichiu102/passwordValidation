package com.example.passwordvalidation;

import com.example.passwordvalidation.constants.Constants;
import org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * this test is the integration test via MockMVC
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordValidationApplicationTests {

    @Autowired
    MockMvc mock;

    private HttpHeaders httpHeaders;

    @Before
    public void init() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }


    @Test
    public void testDuplicateDigitOnly() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","123123");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testDuplicateAlphaOnly() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","absabs");
        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testSameSeq1() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","absabs123123");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testSameSeq2() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","abs123123abs");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void testSameSeq3() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","ab123123");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void success() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","123a123");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdValid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void empty() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }


    @Test
    public void testShortLen() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","a123");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testLontLen() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","a1234567890da2wf");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testCapital() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","a123456A");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }


    @Test
    public void testSpeChar() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","a123456%#");

        JSONObject response = new JSONObject();
        response.put(Constants.checkPwd,Constants.pwdInvalid);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().string(response.toString()))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

    }
}
