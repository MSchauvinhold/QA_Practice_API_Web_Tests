package testpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    // Campos de la página de login
    private By mail = By.id("email");
    private By password = By.id("password");

    // Botón de login
    private By login = By.className("btn-primario");

    // Mensajes de saludo y nombre de usuario
    private By hello = By.className("txt-hola");
    private By name = By.className("txt-nombre");

    // Mensajes de campos obligatorios
    private By mailReq = By.xpath("//*[@id=\"root\"]/main/div/form/div[1]/small");
    private By passwordReq = By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/small");

    // Mensajes de validación específica
    private By mailInv = By.xpath("//div[@class='form-group']//small[@class='small-feedback'][normalize-space()='El email es inválido']");
    private By passwordShort = By.xpath("//*[@id=\"root\"]/main/div/form/div[2]/small");

    // Mensaje de credenciales inválidas
    private By credencialsInv = By.className("form-feedback");

    // Constructor de la página de login
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null); // Se pasa null para wait, como en tu código original
    }

    // Ingresa el correo en el campo correspondiente
    public void escribirCorreo(String correo) throws InterruptedException {
        this.sendText(correo, mail);
    }

    // Ingresa la contraseña en el campo correspondiente
    public void escribirContraseña(String contraseña) throws InterruptedException {
        this.sendText(contraseña, password);
    }

    // Devuelve el mensaje de saludo tras el login
    public String saludo() throws InterruptedException {
        System.out.println("MENSAJE DE SALUDO: " + this.getText(hello));
        return this.getText(hello);
    }

    // Devuelve el nombre del usuario tras el login
    public String loginNombre() throws InterruptedException {
        System.out.println("SE VERIFICA EL NOMBRE DEL LOGIN: " + this.getText(name));
        return this.getText(name);
    }

    // Hace clic en el botón de login
    public void clickLogin() throws InterruptedException {
        this.click(login);
    }

    // Devuelve el mensaje de error si el correo es obligatorio
    public String correoObligatorio() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(mailReq));
        return this.getText(mailReq);
    }

    // Devuelve el mensaje de error si la contraseña es obligatoria
    public String contraseñaObligatoria() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordReq));
        return this.getText(passwordReq);
    }

    // Devuelve el mensaje de error si el correo es inválido
    public String correoInvalido() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(mailInv));
        return this.getText(mailInv);
    }

    // Devuelve el mensaje de error si la contraseña es demasiado corta
    public String contraseñaCorta() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordShort));
        return this.getText(passwordShort);
    }

    // Devuelve el mensaje de error si las credenciales son inválidas
    public String credencialesInvalidas() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(credencialsInv));
        return this.getText(credencialsInv);
    }

}


