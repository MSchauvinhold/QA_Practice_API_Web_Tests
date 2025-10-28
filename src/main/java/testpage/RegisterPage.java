package testpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    // Campos del formulario de registro
    private By nombre = By.id("firstName");
    private By apellido = By.id("lastName");
    private By email = By.id("email");
    private By password = By.id("password");
    private By repassword = By.id("repassword");

    // Botones de la página
    private By btnRegistrarse = By.className("btn-primario");
    private By btnCrearCuenta = By.linkText("Crear cuenta");

    // Mensajes de confirmación o éxito
    private By gracias = By.className("txt-gracias");
    private By exito = By.className("txt-exito");

    // Mensajes de error
    private By mailRegister = By.className("form-feedback");
    private By passwordDis = By.className("small-feedback");

    // Constructor de la página de registro
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null); // Se pasa null para wait, como en tu código original
    }

    // Hace clic en el enlace "Crear cuenta"
    public void clickCrearCuenta() throws InterruptedException {
        this.click(btnCrearCuenta);
    }

    // Ingresa el nombre en el formulario
    public void escribirNombre(String name) throws InterruptedException {
        this.sendText(name, nombre);
    }

    // Ingresa el apellido en el formulario
    public void escribirApellido(String name) throws InterruptedException {
        this.sendText(name, apellido);
    }

    // Ingresa el correo en el formulario
    public void escribirCorreo(String mail) throws InterruptedException {
        this.sendText(mail, email);
    }

    // Ingresa la contraseña en el formulario
    public void escribirContraseña(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    // Repite la contraseña en el formulario
    public void repetirContraseña(String pass) throws InterruptedException {
        this.sendText(pass, repassword);
    }

    // Hace clic en el botón "Registrarse"
    public void clickRegistrarse() throws InterruptedException {
        this.click(btnRegistrarse);
        Thread.sleep(1000); // Espera breve para que se muestre el mensaje de confirmación
    }

    // Devuelve el mensaje de agradecimiento tras crear la cuenta
    public String cuentaCreadaGracias() throws InterruptedException {
        String res = this.getText(gracias);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    // Devuelve el mensaje de éxito tras crear la cuenta
    public String cuentaCreadaExito() throws InterruptedException {
        String res = this.getText(exito);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    // Devuelve el mensaje de error si el correo ya está registrado
    public String mailRegistrado() throws InterruptedException {
        String res = this.getText(mailRegister);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    // Devuelve el mensaje de error si las contraseñas no coinciden
    public String contraseñaNoCoinciden() throws InterruptedException {
        String res = this.getText(passwordDis);
        System.out.println("Resultado Card value: " + res);
        return res;
    }
}
