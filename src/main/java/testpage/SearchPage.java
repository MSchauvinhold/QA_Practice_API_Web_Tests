package testpage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    // Campo de búsqueda de la ciudad
    private By searchBox = By.id("ciudad");

    // Botón para ejecutar la búsqueda
    private By searchButton = By.id("btn-buscador");

    // Elemento que indica que la búsqueda devolvió resultados
    private By searchOk = By.className("categoria");

    // Constructor de la página de búsqueda
    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null); // Se pasa null para wait, como en tu código original
    }

    // Escribe la ciudad en el campo de búsqueda y presiona ENTER
    public void escribirBusqueda(String ciudad) throws InterruptedException {
        this.sendText(ciudad, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }

    // Hace clic en el botón de búsqueda
    public void clickBuscar() throws InterruptedException {
        this.click(searchButton);
    }

    // Devuelve el texto del resultado de la búsqueda
    public String resultadoBusqueda() throws InterruptedException {
        String respuesta = this.getText(searchOk);
        System.out.println("Resultado de la busqueda: " + respuesta);
        return respuesta;
    }
}
