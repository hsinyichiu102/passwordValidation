package com.example.passwordvalidation;

import com.example.passwordvalidation.service.ValidateService;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;



/**
 * a simple junit unit test to ensure the class validationService works
 */
public class PasswordValidationUnitTest {

    ValidateService service= new ValidateService();

    @Test
    public void testDuplicateDigit(){
        assertFalse(service.validatePwd("123123"));
    }

    @Test
    public void testDuplicateAlpha(){
        assertFalse(service.validatePwd("absabs"));
    }

    @Test
    public void testSameSeq1(){
        assertFalse(service.validatePwd("abc123123"));
    }

    @Test
    public void testSameSeq2(){
        assertFalse(service.validatePwd("123abcabc"));
    }

    @Test
    public void testSameSeq3(){
        assertFalse(service.validatePwd("abc123abcabc"));
    }

    @Test
    public void testCapital(){
        assertFalse(service.validatePwd("123Abc"));
    }

    @Test
    public void testEmpty(){
        assertFalse(service.validatePwd(""));
    }

    @Test
    public void testSuccess(){
        assertTrue(service.validatePwd("123a123"));
    }

    @Test
    public void shortLen(){
        assertFalse(service.validatePwd("a123"));
    }

    @Test
    public void LonLen(){
        assertFalse(service.validatePwd("a123asd123wffae121s3f"));
    }

    @Test
    public void specialChar(){
        assertFalse(service.validatePwd("a123a$@"));
    }



}
