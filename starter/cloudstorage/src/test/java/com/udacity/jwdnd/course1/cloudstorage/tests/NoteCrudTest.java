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

public class NoteCrudTest {
    private static String username;
    private static String password;
    @LocalServerPort
    public  int port;

    public static WebDriver driver;

    public  String baseURL;
    private HomePage homePage;

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
    @AfterEach
    public  void afterEach() {
        homePage.logout();
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

        username = "userwithnotes";
        password = "userwithnotes";
        signupPage.signup("segun", "wahaab", username, password, driver);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);
        driver.get(baseURL+ "/home");


        String noteTitle="big title";
        String noteDescription="great description";
        homePage.createNote("SEE", "REAL", driver);
        driver.get(baseURL+ "/home");

        homePage.createNote(noteTitle, noteDescription, driver);
    }

    @Test
    public void testUserLoginThenCreateNoteThenVerifies()  {
        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "admin");

        HomePage homePage = new HomePage(driver);

        String noteTitle="big title";
        String noteDescription="great description";
        driver.get(baseURL+ "/home");

        homePage.createNote(noteTitle, noteDescription, driver);
        driver.get(baseURL+ "/home");


        assertEquals(noteTitle, homePage.getNoteTitle(driver));
        assertEquals(noteDescription, homePage.getNoteDescription(driver));

    }
    @Test
    public void editNote()  {
        username = "userwithnotes";
        password = "userwithnotes";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);


        String noteTitle="editednote";
        String noteDescription="editedDescription";
        driver.get(baseURL+ "/home");

        homePage.editNote(noteTitle, noteDescription, driver);
        driver.get(baseURL+ "/home");


        assertEquals(noteTitle, homePage.getNoteTitle(driver));
        assertEquals(noteDescription, homePage.getNoteDescription(driver));

    }
    @Test
    public void deleteNote() {
        username = "userwithnotes";
        password = "userwithnotes";

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        driver.get(baseURL+ "/home");

        String noteTitle=homePage.getNoteTitle(driver);
        String noteDescription=homePage.getNoteDescription(driver);

        homePage.deleteNote( driver);
        driver.get(baseURL+ "/home");


        assertNotEquals(noteTitle, homePage.getNoteTitle(driver));
        assertNotEquals(noteDescription, homePage.getNoteDescription(driver));

    }
}
