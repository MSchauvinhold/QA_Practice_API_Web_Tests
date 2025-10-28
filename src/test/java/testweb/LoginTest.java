package testweb;

import testpage.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentFactory;

import java.time.Duration;

public class LoginTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte_Login.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void preconditions() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setup();
        loginPage.url("https://digital-booking-front.digitalhouse.com/login");
    }

    @Test
    @Tag("LOGIN")
    public void login_Exitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Inicio de sesión exitoso con credenciales válidas");
        test.log(Status.INFO, "Comienza el test de inicio de sesión");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.escribirCorreo("prueba159@gmail.com");
        loginPage.escribirContraseña("contraseña123");
        test.log(Status.PASS, "Se ingresaron credenciales válidas");

        loginPage.clickLogin();

        Assertions.assertEquals(loginPage.saludo(), "Hola,");
        Assertions.assertEquals(loginPage.loginNombre(), "Mateo Javier Schauvinhold");
        test.log(Status.PASS, "Se validó el inicio de sesión exitoso");
    }

    @Test
    @Tag("LOGIN")
    public void login_DatosVacios() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar iniciar sesión sin ingresar datos");
        test.log(Status.INFO, "Comienza el test de inicio de sesión sin datos");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.escribirContraseña("");
        test.log(Status.PASS, "No se ingresaron datos");

        loginPage.clickLogin();
        loginPage.correoObligatorio();
        loginPage.contraseñaObligatoria();

        test.log(Status.PASS, "Se validaron los mensajes de campos obligatorios");
    }

    @Test
    @Tag("LOGIN")
    public void login_CorreoInvalido() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar iniciar sesión con un correo inválido");
        test.log(Status.INFO, "Comienza el test de inicio de sesión con correo inválido");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.escribirCorreo("prueba159");
        loginPage.escribirContraseña("contraseña123");
        test.log(Status.PASS, "Se ingresó un correo con formato incorrecto");

        loginPage.clickLogin();
        loginPage.correoInvalido();
        test.log(Status.PASS, "Se validó el mensaje de correo inválido");
    }

    @Test
    @Tag("LOGIN")
    public void login_ContraseñaCorta() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar iniciar sesión con una contraseña demasiado corta");
        test.log(Status.INFO, "Comienza el test de inicio de sesión con contraseña corta");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.escribirCorreo("prueba159@gmail.com");
        loginPage.escribirContraseña("123");
        test.log(Status.PASS, "Se ingresó una contraseña con menos de 6 caracteres");

        loginPage.clickLogin();
        loginPage.contraseñaCorta();
        test.log(Status.PASS, "Se validó el mensaje de contraseña demasiado corta");
    }

    @Test
    @Tag("LOGIN")
    public void login_CredencialesInvalidas() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar iniciar sesión con credenciales inválidas");
        test.log(Status.INFO, "Comienza el test de inicio de sesión con credenciales erróneas");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.escribirCorreo("prueba4021@gmail.com");
        loginPage.escribirContraseña("cntrsñ12345");
        test.log(Status.PASS, "Se ingresaron credenciales incorrectas");

        loginPage.clickLogin();
        loginPage.credencialesInvalidas();
        test.log(Status.PASS, "Se validó el mensaje de correo o contraseña inválida");
    }

    @AfterEach
    public void close() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}




