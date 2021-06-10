package com.example.passwordvalidation;

import com.example.passwordvalidation.controller.ValidateController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.StringContains;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;



import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * this test is the integration test via MockMVC
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class PasswordValidationApplicationTests {

    @Autowired
    MockMvc mock;

    private HttpHeaders httpHeaders;

    String isEmpty="CANNOT be empty!";
    String pwd_length="length MUST between 5 to 12!";
    String pwd_oneEach= "MUST contains a numerical digital and lowercase letter!";
    String pwd_DigitAndLetter ="ONLY contain numerical digital and lowercase letter!";
    String pwd_Seq="CANNOT contain any sequence of characters immediately followed by the same sequence!";

    @Before
    public void init() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    }

    /**
     * Check numeric digit only and follow by same sequence
     * @throws Exception
     */
    @Test
    public void testInvalid1() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","123123");

        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());


        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_oneEach)));

    }



    /**
     * Check lowercase letter only and short len
     * @throws Exception
     */
    @Test
    public void testInvalid2() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","asdn");


        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_DigitAndLetter)));

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_length)));

    }

    /**
     * Check long length and one capital letter
     * @throws Exception
     */
    @Test
    public void testInvalid3() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","Asdn12334346sdf12fwfsdf12");

        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_DigitAndLetter)));

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_length)));

    }

    /**
     * Check one capital letter
     * @throws Exception
     */
    @Test
    public void testInvalid4() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","Asdn123asd");

        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_DigitAndLetter)));


    }

    /**
     * Check one special character
     * @throws Exception
     */
    @Test
    public void testInvalid5() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","asdn#123asd");

        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_DigitAndLetter)));


    }

    /**
     * Check followed by same sequence
     * @throws Exception
     */
    @Test
    public void testInvalid6() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","abc123123");
        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(pwd_Seq)));


    }

    /**
     * Check empty
     * @throws Exception
     */
    @Test
    public void testInvalid7() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","");

        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andExpect(content().string(containsString(isEmpty)));


    }

    /**
     * test valid
     * @throws Exception
     */
    @Test
    public void testvalid() throws Exception{

        JSONObject request = new JSONObject();
        request.put("password","123a123");
        JSONObject response = new JSONObject();
        response.put("message","valid");

        RequestBuilder requestBuilder =
                post("/signin")
                        .headers(httpHeaders)
                        .content(request.toString());

        mock.perform(requestBuilder)
                .andExpect(content().string(response.toString()));


    }

}
