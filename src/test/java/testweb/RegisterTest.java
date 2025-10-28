package testweb;

import testpage.RegisterPage;
import testpage.SearchPage;
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

public class RegisterTest {

    public WebDriver driver;
    public WebDriverWait wait;

    // Configuración del reporte de registro
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte_Registro.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        // Inicializa el reporte antes de ejecutar los tests
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        // Configuración inicial antes de cada prueba
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.url("https://digital-booking-front.digitalhouse.com/");
    }

    @Test
    @Tag("REGISTRO")
    public void RegistroExitoso() throws InterruptedException {
        // Caso de prueba: registro exitoso con datos válidos
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Inicio del test de registro exitoso");

        SearchPage searchPage = new SearchPage(driver, wait);
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickCrearCuenta();
            test.log(Status.PASS, "Ingreso al formulario de registro");

            registerPage.escribirNombre("Mateo");
            registerPage.escribirApellido("Schauvinhold");
            registerPage.escribirCorreo("prueba4095@gmail.com");
            registerPage.escribirContraseña("123456");
            registerPage.repetirContraseña("123456");
            test.log(Status.PASS, "Ingreso correcto de todos los datos");

            registerPage.clickRegistrarse();

            if (registerPage.cuentaCreadaGracias().equals("¡Cuenta registrada con éxito!")) {
                test.log(Status.PASS, "Validación del mensaje de registro exitoso");
            } else {
                test.log(Status.FAIL, "Fallo en el mensaje de registro exitoso");
            }

            if (registerPage.cuentaCreadaExito().equals("Te enviamos un email para confirmar tu cuenta")) {
                test.log(Status.PASS, "Validación del mensaje de confirmación por correo");
            } else {
                test.log(Status.FAIL, "Fallo en el mensaje de confirmación por correo");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    @Test
    @Tag("REGISTRO")
    public void RegistroFallidoMailRepetido() throws InterruptedException {
        // Caso de prueba: intento de registro con un correo ya registrado
        ExtentTest test = extent.createTest("Registro Fallido - Correo ya registrado");
        test.log(Status.INFO, "Inicio del test con correo duplicado");

        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickCrearCuenta();
            test.log(Status.PASS, "Ingreso al formulario de registro");

            registerPage.escribirNombre("Mateo");
            registerPage.escribirApellido("Schauvinhold"); // corregido el apellido para mantener consistencia
            registerPage.escribirCorreo("prueba365@gmail.com");
            registerPage.escribirContraseña("123456");
            registerPage.repetirContraseña("123456");
            test.log(Status.PASS, "Datos ingresados con correo ya registrado");

            registerPage.clickRegistrarse();

            if (registerPage.mailRegistrado().equals("Ese email ya se encuentra registrado")) {
                test.log(Status.PASS, "Validación del mensaje de correo ya registrado");
            } else {
                test.log(Status.FAIL, "Fallo en la validación del mensaje de correo ya registrado");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    @Test
    @Tag("REGISTRO")
    public void RegistroFallidoContraseña() throws InterruptedException {
        // Caso de prueba: contraseñas que no coinciden
        ExtentTest test = extent.createTest("Registro Fallido - Contraseñas no coinciden");
        test.log(Status.INFO, "Inicio del test con contraseñas distintas");

        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickCrearCuenta();
            test.log(Status.PASS, "Ingreso al formulario de registro");

            registerPage.escribirNombre("Mateo");
            registerPage.escribirApellido("Schauvinhold");
            registerPage.escribirCorreo("prueba2004@gmail.com");
            registerPage.escribirContraseña("12345678");
            registerPage.repetirContraseña("1234567");
            test.log(Status.PASS, "Datos ingresados con contraseñas diferentes");

            registerPage.clickRegistrarse();

            if (registerPage.contraseñaNoCoinciden().equals("Las contraseñas deben ser iguales")) {
                test.log(Status.PASS, "Validación del mensaje de contraseñas no coincidentes");
            } else {
                test.log(Status.FAIL, "Fallo en la validación del mensaje de contraseñas no coincidentes");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    @AfterEach
    public void cerrar() {
        // Cierra el navegador al finalizar cada prueba
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        // Genera y guarda el reporte final
        extent.flush();
    }
}
