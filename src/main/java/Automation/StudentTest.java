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
public class StudentTest {

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/Automation/chrome-driver/chromedriver.exe");

        driver = new ChromeDriver();
//        driver = new DriverManager().SetUpDriver(new EdgeConfig(), VersionResolveStrategy.MatchingBrowser);

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
    public void testUserRegistrationStudent() throws InterruptedException {
        String studentFirstName = "Rakshith";
        String studentLastName = "Thakur";
        String studentMobile = "999999999";
        String studentEmail = "rakshith@gmail.com";
        String studentPassword = "1234";

        WebElement firstNameElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput1\"]"));
        WebElement lastNameElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput2\"]"));
        WebElement emailElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput3\"]"));
        WebElement passWordElement = driver.findElement(By.xpath("//*[@id=\"formPlaintextPassword\"]"));
        WebElement passwordReEnterElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput5\"]"));
        WebElement phoneNumberElement = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput4\"]"));
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"signupform.ControlInput6\"]"));
        Select selectRole = new Select(dropdown);

        selectRole.selectByVisibleText("Student");



        firstNameElement.sendKeys(studentFirstName);
        lastNameElement.sendKeys(studentLastName);
        emailElement.sendKeys(studentEmail);
        phoneNumberElement.sendKeys(studentMobile);
        passWordElement.sendKeys(studentPassword);
        passwordReEnterElement.sendKeys(studentPassword);


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



    @Test(dependsOnMethods = {"testOpenSignUpPage", "testUserRegistrationStudent"})
    public void testLoginStudent() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
        String email = "rakshith@gmail.com";
        String password = "1234";

        WebElement userNameElement = driver.findElement(By.xpath("//*[@id=\"formBasicEmail\"]"));
        WebElement passwordElement = driver.findElement(By.xpath("//*[@id=\"formBasicPassword\"]"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/button"));


        userNameElement.sendKeys(email);
        passwordElement.sendKeys(password);
        submitButton.click();



        WebDriverWait waitForHomePage = new WebDriverWait(driver, Duration.ofMillis(100));
        WebElement verifyLogin = waitForHomePage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/h1")));
        Assert.assertEquals(verifyLogin.getText(), "Welcome Rakshith!");
    }

    @Test(dependsOnMethods = {"testOpenSignUpPage", "testUserRegistrationStudent", "testLoginStudent"})
    public void takeTest() throws InterruptedException {
//        WebElement startQuizButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div/div/button"));
//        Thread.sleep(1000);
//        startQuizButton.click();
//
//        WebElement selectOption = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/span[1]/div"));
//        selectOption.click();
//
//        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/button"));
//        submitButton.click();
    }


    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}
