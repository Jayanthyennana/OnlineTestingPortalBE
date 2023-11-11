package Automation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InstructorTest {

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/Automation/chrome-driver/chromedriver.exe");

        driver = new ChromeDriver();

        driver.get("http://localhost:3000");
    }

    @Test
    public void testOpenSignUpPage() {
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"basic-navbar-nav\"]/div/a[2]"));
        signUpButton.click();

        WebDriverWait waitForSignUpPage = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement firstNameElement = waitForSignUpPage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div[2]/form/div[1]/label")));

        Assert.assertEquals(firstNameElement.getText(), "First Name");
    }

    @Test(dependsOnMethods = {"testOpenSignUpPage"})
    public void testUserRegistrationInstructor() throws InterruptedException {
        String instructorFirstName = "Yashwith";
        String instructorLastName = "Varalabalaji";
        String instructorPhoneNumber = "999999999";
        String instructorEmail = "yash@gmail.com";
        String instructorPassword = "1234";

        WebElement firstNameElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput1\"]"));
        WebElement lastNameElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput2\"]"));
        WebElement emailElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput3\"]"));
        WebElement passWordElement = driver.findElement(By.xpath("//*[@id=\"formPlaintextPassword\"]"));
        WebElement passwordReEnterElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput5\"]"));
        WebElement phoneNumberElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput4\"]"));

        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput6\"]"));
        Select selectRole = new Select(dropdown);

        selectRole.selectByVisibleText("Instructor");



        firstNameElement.sendKeys(instructorFirstName);
        lastNameElement.sendKeys(instructorLastName);
        emailElement.sendKeys(instructorEmail);
        phoneNumberElement.sendKeys(instructorPhoneNumber);
        passWordElement.sendKeys(instructorPassword);
        passwordReEnterElement.sendKeys(instructorPassword);


        WebDriverWait waitForSubmitButton = new WebDriverWait(driver, Duration.ofMillis(100));
        WebElement submitButton = waitForSubmitButton.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div[2]/form/button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        Thread.sleep(100L);
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        Assert.assertEquals(alertText, "User registered");
    }

    @Test(dependsOnMethods = {"testOpenSignUpPage", "testUserRegistrationInstructor"})
    public void testValidLogin() {
        String email = "yash@gmail.com";
        String password = "1234";

        WebElement userNameElement = driver.findElement(By.xpath("//*[@id=\"formBasicEmail\"]"));
        WebElement passwordElement = driver.findElement(By.xpath("//*[@id=\"formBasicPassword\"]"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/button"));


        userNameElement.sendKeys(email);
        passwordElement.sendKeys(password);
        submitButton.click();



        WebDriverWait waitForHomePage = new WebDriverWait(driver, Duration.ofMillis(100));
        WebElement verifyLogin = waitForHomePage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/h1")));
        Assert.assertEquals(verifyLogin.getText(), "Welcome Yashwith!");
    }

    @Test(dependsOnMethods = {"testOpenSignUpPage", "testUserRegistrationInstructor", "testValidLogin"})
    public void testCreateQuiz() throws InterruptedException {
        WebElement createQuizButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/button"));

        createQuizButton.click();
        Thread.sleep(100L);

        WebElement quizDesc = driver.findElement(By.xpath("//*[@id=\"quizDesc\"]"));
        quizDesc.sendKeys("This is Quiz from Selenium");

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        WebElement fromDatePicker = driver.findElement(By.xpath("//*[@id=\"fromDate\"]"));
        fromDatePicker.sendKeys(formattedDate);


        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        WebElement startTime = driver.findElement(By.xpath("//*[@id=\"fromTime\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", startTime);
        startTime.sendKeys(formattedTime);


        LocalDate newDate = currentDate.plusDays(1);
        String formattedNewDate = newDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        WebElement endDatePicker = driver.findElement(By.xpath("//*[@id=\"toDate\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", endDatePicker);
        endDatePicker.sendKeys(formattedNewDate);


        WebElement endTime = driver.findElement(By.xpath("//*[@id=\"collapse-text\"]/div[2]/input[4]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", endTime);
        endTime.sendKeys(formattedTime);

        WebElement totalMarks = driver.findElement(By.xpath("//*[@id=\"totalMarks\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", totalMarks);
        totalMarks.sendKeys("100");


        WebElement selectStudentsElement = driver.findElement(By.xpath("//*[@id=\"assignedTo\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectStudentsElement);
        Select selectStudents = new Select(selectStudentsElement);

        for (int i = 0; i < selectStudents.getOptions().size(); i++) {
            selectStudents.selectByIndex(i);
        }


        WebElement createQuizSubmitButton = driver.findElement(By.xpath("//*[@id=\"collapse-text\"]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createQuizSubmitButton);
        createQuizSubmitButton.click();

    }
}
