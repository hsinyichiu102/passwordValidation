package com.example.passwordvalidation;

import com.example.passwordvalidation.entity.UserInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;

import javax.validation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@Import(ValidationAutoConfiguration.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordValidationUnitTest {


    private static Validator validator;
    String isEmpty="{pwd.isEmpty}";
    String pwd_length="{pwd.length}";
    String pwd_oneEach= "{pwd.oneEach}";
    String pwd_DigitAndLetter ="{pwd.DigitAndLetter}";
    String pwd_Seq="{pwd.Seq}";


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     *
     */
    @Test
    public void testInvalid() {
        UserInfo usr = new UserInfo("test","123123");
        Set<ConstraintViolation<UserInfo>> violations = validator.validate(usr);
        List<String> error = new ArrayList<>();
        for (ConstraintViolation cv : violations) {
            /*System.out.println("ValidatationConstraint_getAnnotation: " + cv.getConstraintDescriptor().getAnnotation());
            System.out.println("ValidatationConstraint_getConstraintDescriptor: " + cv.getConstraintDescriptor());
            System.out.println("ValidatationConstraint_getMessageTemplate: " + cv.getMessageTemplate());
            System.out.println("ValidatationConstraint_getInvalidValue: " + cv.getInvalidValue());
            System.out.println("ValidatationConstraint_getLeafBean: " + cv.getLeafBean());
            System.out.println("ValidatationConstraint_getRootBeanClass: " + cv.getRootBeanClass());
            System.out.println("ValidatationConstraint_getPropertyPath().toString: " + cv.getPropertyPath().toString());*/
            System.out.println("ValidatationConstraint_getMessage: " + cv.getMessage());
            error.add(cv.getMessageTemplate());
        }
        assertTrue(!error.isEmpty());
        assertTrue(error.contains(pwd_Seq));
        assertTrue(error.contains(pwd_oneEach));

        usr = new UserInfo("test","123123a");
        violations=validator.validate(usr);
        error = new ArrayList<>();
        for (ConstraintViolation cv : violations) {
            error.add(cv.getMessageTemplate());
        }
        assertTrue(!error.isEmpty());
        assertTrue(error.contains(pwd_Seq));


        usr = new UserInfo("test","asd");
        violations=validator.validate(usr);
        error = new ArrayList<>();
        for (ConstraintViolation cv : violations) {
            error.add(cv.getMessageTemplate());
        }
        assertTrue(!error.isEmpty());
        assertTrue(error.contains(pwd_length));

        usr = new UserInfo("test","asdAD123");
        violations=validator.validate(usr);
        error = new ArrayList<>();
        for (ConstraintViolation cv : violations) {
            error.add(cv.getMessageTemplate());
        }
        assertTrue(!error.isEmpty());
        assertTrue(error.contains(pwd_DigitAndLetter));

        usr = new UserInfo("test","");
        violations=validator.validate(usr);
        error = new ArrayList<>();
        for (ConstraintViolation cv : violations) {
            error.add(cv.getMessageTemplate());
        }
        assertTrue(!error.isEmpty());
        assertTrue(error.contains(isEmpty));
    }
    @Test
    public void testValid() {
        UserInfo usr = new UserInfo("test","123a123");
        Set<ConstraintViolation<UserInfo>> violations = validator.validate(usr);
        List<String> error = new ArrayList<>();
        for (ConstraintViolation cv : violations) {
            error.add(cv.getMessageTemplate());
        }
        assertTrue(error.isEmpty());
    }

}
