package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialCRUDTest {
    private static String username;
    private static String password;
    @LocalServerPort
    public  int port;

    public static WebDriver driver;

    public  String baseURL;
    private HomePage homePage;
    private String usernamewithcreds;
    private String passwordwithcreds;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


    }
    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;

    }

    @BeforeEach
    public void beforeEach()  {
        baseURL = "http://localhost:" + port;
        username = "admin";
        password = "admin";
        homePage = new HomePage(driver);

        driver.get(baseURL+ "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("segun", "wahaab", username, password, driver);
        driver.get(baseURL+ "/signup");

        usernamewithcreds = "userwithnotes";
        passwordwithcreds = "userwithnotes";
        signupPage.signup("segun", "wahaab", usernamewithcreds, passwordwithcreds, driver);
        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(usernamewithcreds, passwordwithcreds);
        driver.get(baseURL+ "/home");

        String credentialUrl="big lie";
        String credentialUsername="great description";
        String credentialRealPassword="great password";

        homePage.createCredential("SEE", "REAL","password", driver);
        driver.get(baseURL+ "/home");

        homePage.createCredential(credentialUrl, credentialUsername,credentialRealPassword, driver);

    }
    @AfterEach
    public  void afterEach() {
        homePage.logout();
    }
        @Test
    public void testUserLoginThenCreateCredentialThenVerifies() {
        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin");

        HomePage homePage = new HomePage(driver);

        String credUrl="big lie";
        String credUsername="great description";
            String credPassword=" description";

            driver.get(baseURL+ "/home");

        homePage.createCredential(credUrl, credUsername,credPassword, driver);

        driver.get(baseURL+ "/home");


        assertEquals(credUrl, homePage.getCredUrl(driver));
        assertEquals(credUsername, homePage.getCredUsername(driver));
        //verifies user only sees encrypted password
            assertNotEquals(credPassword, homePage.getCredPassword(driver));

    }
    @Test
    public void editCred() {
        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(usernamewithcreds, passwordwithcreds);

        String credUrl="editedcredurl";
        String credUsername="editedcredusername";
        String credPassword="editedcredpassword";

        driver.get(baseURL+ "/home");

        homePage.editCred(credUrl, credUsername,credPassword, driver);
        driver.get(baseURL+ "/home");


        assertEquals(credUrl, homePage.getCredUrl(driver));
        assertEquals(credUsername, homePage.getCredUsername(driver));
        assertNotEquals(credPassword, homePage.getCredPassword(driver));

    }
    @Test
    public void deleteCred()  {
        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(usernamewithcreds, passwordwithcreds);
        driver.get(baseURL+ "/home");

        String credUrl=homePage.getCredUrl(driver);
        String credUsername=homePage.getCredUsername(driver);
        String credPassword=homePage.getCredPassword(driver);


        homePage.deleteCred( driver);

        driver.get(baseURL+ "/home");

        assertNotEquals(credUrl, homePage.getCredUrl(driver));
        assertNotEquals(credUsername, homePage.getCredUsername(driver));
        assertNotEquals(credPassword, homePage.getCredPassword(driver));

    }

}
