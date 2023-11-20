package Automation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
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
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"basic-navbar-nav\"]/div/a"));
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
        String instructorPassword = "Test@1234";

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


        WebDriverWait waitForSubmitButton = new WebDriverWait(driver, Duration.ofMillis(1000));
        Thread.sleep(1000);
        WebElement subButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div[2]/form/button"));
//        WebElement submitButton = waitForSubmitButton.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div[2]/form/button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subButton);
        Thread.sleep(100L);
        subButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        Assert.assertEquals(alertText, "User registered");
    }

    @Test(dependsOnMethods = {"testOpenSignUpPage", "testUserRegistrationInstructor"})
    public void testValidLogin() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
        String email = "yash@gmail.com";
        String password = "Test@1234";

        WebElement userNameElement = driver.findElement(By.xpath("//*[@id=\"formBasicEmail\"]"));
        WebElement passwordElement = driver.findElement(By.xpath("//*[@id=\"formBasicPassword\"]"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/button"));


        userNameElement.sendKeys(email);
        passwordElement.sendKeys(password);
        submitButton.click();



        WebDriverWait waitForHomePage = new WebDriverWait(driver, Duration.ofMillis(100));
        WebElement verifyLogin = waitForHomePage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"basic-navbar-nav\"]/div/div/p")));
        Assert.assertEquals(verifyLogin.getText(), "Yashwith");
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


        WebDriverWait waitForQuizSubmitButton = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement quizSubmitButton = waitForQuizSubmitButton.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"collapse-text\"]/button")));
//        WebElement createQuizSubmitButton = driver.findElement(By.xpath("//*[@id=\"collapse-text\"]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quizSubmitButton);
        Thread.sleep(1000);
        quizSubmitButton.click();



        Thread.sleep(1000);
        WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"collapse-text\"]/p"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", successMessage);

        Assert.assertEquals(successMessage.getText(), "Quiz created!");

    }

    @Test(dependsOnMethods = {"testOpenSignUpPage", "testUserRegistrationInstructor", "testValidLogin", "testCreateQuiz"})
    public void testAddQuestions() throws InterruptedException {
        WebElement addQuestionsButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div/div/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addQuestionsButton);
        Thread.sleep(1000);
        addQuestionsButton.click();

        WebElement description = driver.findElement(By.xpath("//*[@id=\"question\"]"));
        WebElement optionA = driver.findElement(By.xpath("//*[@id=\"choice1\"]"));
        WebElement optionB = driver.findElement(By.xpath("//*[@id=\"choice2\"]"));
        WebElement optionC = driver.findElement(By.xpath("//*[@id=\"choice3\"]"));
        WebElement optionD = driver.findElement(By.xpath("//*[@id=\"choice4\"]"));


        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));

        description.sendKeys("Is this from selenium Testing ?");
        optionA.sendKeys("Yes");
        optionB.sendKeys("No");
        optionC.sendKeys("How Would I Know ?");
        optionD.sendKeys("I don't know");

        submitButton.click();


    }

    @AfterSuite
    public void terminate() {
        driver.quit();
    }
}
