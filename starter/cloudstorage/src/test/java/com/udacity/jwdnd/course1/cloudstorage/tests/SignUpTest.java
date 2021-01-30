package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SignUpTest {
    @LocalServerPort
    public int port;

    public static WebDriver driver;

    public String baseURL;

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
    public void beforeEach() {
        baseURL = baseURL = "http://localhost:" + port;
    }
    @Test
    public void userAttemptsToAccessHomePageRedirectedToLogin() throws InterruptedException {
        driver.get(baseURL + "/home");
        Thread.sleep(3000);
        assertNotEquals("Home", driver.getTitle());
    }


    @Test
    public void testUserSignupLoginThenLogout()  {
        String username = "admin";
        String password = "admin";

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("segun", "wahaab", username, password, driver);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.logout();


        assertNotEquals("Home", driver.getTitle());
    }
}
