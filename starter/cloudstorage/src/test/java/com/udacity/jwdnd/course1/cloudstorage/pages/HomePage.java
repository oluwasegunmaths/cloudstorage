package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id="messageText")
    private WebElement textField;

    @FindBy(id="submitMessage")
    private WebElement submitButton;

    @FindBy(className = "chatMessageUsername")
    private WebElement firstMessageUsername;

    @FindBy(className = "chatMessageText")
    private WebElement firstMessageText;

    @FindBy(css="#logout-button")
    private WebElement logoutButton;

    @FindBy(css="#nav-notes-tab")
    private WebElement noteTab;

    @FindBy(css="#add-new-note-button")
    private WebElement newNoteButton;

    @FindBy(css="#note-title")
    private WebElement noteTitleInput;

    @FindBy(css="#note-description")
    private WebElement noteDescriptionInput;

    @FindBy(css="#note-submit-button")
    private WebElement noteSubmitButton;

    @FindBy(css = "#deldutton")
    private WebElement firstNoteDeleteButton;

    @FindBy(className = "notetitle")
    private WebElement firstNoteTitle;

    @FindBy(className = "notedescription")
    private WebElement firstNoteDescription;

    @FindBy(css="#myid")
    private WebElement firstNoteEditButton;


    @FindBy(css="#nav-credentials-tab")
    private WebElement credTab;

    @FindBy(css="#addNewCredential")
    private WebElement newCredButton;

    @FindBy(css="#credential-url")
    private WebElement credUrlInput;

    @FindBy(css="#credential-username")
    private WebElement credUsernameInput;

    @FindBy(css="#credential-password")
    private WebElement credPasswordInput;

    @FindBy(css="#credSubmitButton")
    private WebElement credSubmitButton;

    @FindBy(css = "#deleteCred")
    private WebElement firstCredDeleteButton;

    @FindBy(css = "#credUrl")
    private WebElement firstCredUrl;

    @FindBy(css = "#credUsername")
    private WebElement firstCredUsername;

    @FindBy(css = "#credPassword")
    private WebElement firstCredPassword;

    @FindBy(css="#editCred")
    private WebElement firstCredEditButton;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutButton.click();

    }

    public void createNote(String noteTitle, String noteDescription, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.cssSelector("#nav-notes-tab")));
        marker.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(newNoteButton)).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(noteSubmitButton));

        this.noteTitleInput.sendKeys(noteTitle);
        this.noteDescriptionInput.sendKeys(noteDescription);
        this.noteSubmitButton.click();


    }

    public String getNoteTitle(WebDriver driver) {
        noteTab.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(newNoteButton));

        return firstNoteTitle.getText();
    }

    public String getNoteDescription(WebDriver driver) {
        noteTab.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(newNoteButton));

        return firstNoteDescription.getText();
    }

    public void editNote(String noteTitle, String noteDescription, WebDriver driver) {
        noteTab.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(firstNoteEditButton)).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(noteSubmitButton));
        noteTitleInput.clear();
        noteDescriptionInput.clear();
        this.noteTitleInput.sendKeys(noteTitle);
        this.noteDescriptionInput.sendKeys(noteDescription);
        this.noteSubmitButton.click();

    }

    public void deleteNote(WebDriver driver) {
        noteTab.click();
        firstNoteDeleteButton.click();

    }

    public void createCredential(String credentialUrl, String credentialUsername, String credentialRealPassword, WebDriver driver) {
        credTab.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(newCredButton)).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(credSubmitButton));

        this.credUrlInput.sendKeys(credentialUrl);
        this.credUsernameInput.sendKeys(credentialUsername);
        this.credPasswordInput.sendKeys(credentialRealPassword);

        this.credSubmitButton.click();
    }

    public String getCredUrl(WebDriver driver) {
          credTab.click();
          new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(newCredButton));

        return firstCredUrl.getText();
    }

    public String getCredUsername(WebDriver driver) {
        credTab.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(newCredButton));

        return firstCredUsername.getText();

    }

    public String getCredPassword(WebDriver driver) {
        credTab.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(newCredButton));

        return firstCredPassword.getText();

    }

    public void deleteCred(WebDriver driver) {
        credTab.click();
        firstCredDeleteButton.click();
    }

    public void editCred(String credUrl, String credUsername, String credPassword, WebDriver driver) {
        credTab.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(firstCredEditButton)).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(credSubmitButton));

        credUrlInput.clear();
        credUsernameInput.clear();
        credPasswordInput.clear();

        this.credUrlInput.sendKeys(credUrl);
        this.credUsernameInput.sendKeys(credUsername);
        this.credPasswordInput.sendKeys(credPassword);

        this.credSubmitButton.click();
    }

}

