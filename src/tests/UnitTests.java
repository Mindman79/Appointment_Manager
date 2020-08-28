package tests;

import controller.CustomerEditScreenController;
import controller.LoginScreenController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnitTest {

    @Test
    public void loginButtonHandlerTest() throws SQLException {


        LoginScreenController test = new LoginScreenController();

        boolean result = test.loginTester("test", "test");

        assertTrue(result);




    }


    @Test
    public void alertGeneratorTest() {

        CustomerEditScreenController testCES = new CustomerEditScreenController();

        //boolean result = testCES.alertGenerator("Test message");

     testCES.alertGenerator("Test");


    }

    @Test
    public void countrySelectTest() {


        CustomerEditScreenController testCES = new CustomerEditScreenController();

        //boolean result = testCES.alertGenerator("Test message");

        testCES.getCountryNameFromID(1);


        String result = "";

        assertEquals("US", result);

    }



}