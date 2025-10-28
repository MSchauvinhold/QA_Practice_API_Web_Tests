package testweb;

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

public class SearchTest {

    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte_Search.html");
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
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.setup();
        searchPage.url("https://digital-booking-front.digitalhouse.com/");
    }

    @Test
    @Tag("BUSQUEDA")
    @Tag("SMOKE")
    public void busquedaExitosa_Uruguay() throws InterruptedException {
        ExtentTest test = extent.createTest("Búsqueda exitosa en Uruguay");
        test.log(Status.INFO, "Comienza el test de búsqueda");

        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.escribirBusqueda("Punta del Este");
        searchPage.clickBuscar();

        test.log(Status.PASS, "Se realizó la búsqueda en Uruguay exitosamente");
        searchPage.resultadoBusqueda();
        test.log(Status.PASS, "Se validó que la búsqueda fue correcta");
    }

    @Test
    @Tag("BUSQUEDA")
    @Tag("REGRESSION")
    public void busquedaExitosa_Grecia() throws InterruptedException {
        ExtentTest test = extent.createTest("Búsqueda exitosa en Grecia");
        test.log(Status.INFO, "Comienza el test de búsqueda");

        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.escribirBusqueda("Paros");
        searchPage.clickBuscar();

        test.log(Status.PASS, "Se realizó la búsqueda en Grecia exitosamente");
        searchPage.resultadoBusqueda();
        test.log(Status.PASS, "Se validó que la búsqueda fue correcta");
    }

    @AfterEach
    public void close() {
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}
