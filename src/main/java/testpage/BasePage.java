package testpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    // Instancia de WebDriver para controlar el navegador
    public WebDriver driver;

    // Instancia de WebDriverWait para esperas explícitas
    public WebDriverWait wait;

    // Constructor de BasePage
    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        // Se crea un WebDriverWait de 3 segundos
        this.wait = new WebDriverWait(driver, Duration.ofMillis(3000));
    }

    // Maximiza la ventana del navegador
    public void setup() {
        driver.manage().window().maximize();
    }

    // Navega a la URL indicada
    public void url(String url){
        driver.get(url);
    }

    // Cierra el navegador y finaliza la sesión de WebDriver
    public void close() {
        driver.quit();
    }

    // Devuelve un elemento Web ubicado con el locator dado
    protected WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    // Envía texto a un campo web, esperando que esté presente
    protected void sendText(String imputText, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(imputText);
    }

    // Envía una tecla o combinación de teclas a un elemento web
    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).sendKeys(key);
    }

    // Hace clic en un elemento web, esperando que sea clickeable
    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    // Obtiene el texto visible de un elemento web
    protected String getText(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }

}
