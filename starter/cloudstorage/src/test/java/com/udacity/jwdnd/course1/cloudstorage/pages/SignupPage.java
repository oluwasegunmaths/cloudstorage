package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    @FindBy(css = "#inputFirstName")
    private WebElement firstNameField;

    @FindBy(css = "#inputLastName")
    private WebElement lastNameField;

    @FindBy(css = "#inputUsername")
    private WebElement usernameField;

    @FindBy(css = "#inputPassword")
    private WebElement passwordField;

    @FindBy(css = "#submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password, WebDriver driver)  {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.cssSelector("#inputFirstName")));
        marker.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

}
