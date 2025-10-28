package testback;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GETTest {

    // Ejemplo 1 - GET básico
    @Test
    public void Get_Test01() {
        // Realiza la solicitud GET a la API
        Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");

        // Imprime información de la respuesta
        System.out.println("Cuerpo de la respuesta: " + resGet.getBody().asString());
        System.out.println("Código de estado: " + resGet.statusCode());
        System.out.println("Encabezado 'Date': " + resGet.getHeader("Date"));
        System.out.println("Tiempo de respuesta: " + resGet.getTime() + " ms");
    }

    // Ejemplo 2 - GET con validaciones
    @Test
    public void Get_Test02() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        int statusCode = response.statusCode();
        JsonPath body = response.jsonPath(); // Permite acceder fácilmente a los campos del JSON

        // Extrae información específica de la respuesta
        String nombre_0 = body.getString("data.first_name[0]");
        String mail_0 = body.getString("data.email[0]");
        String nombre_2 = body.getString("data.first_name[2]");
        String mail_2 = body.getString("data.email[2]");

        // Validaciones simples con asserts
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(nombre_0, "Michael");
        Assert.assertEquals(mail_0, "michael.lawson@reqres.in");
        Assert.assertEquals(nombre_2, "Tobias");
        Assert.assertEquals(mail_2, "tobias.funke@reqres.in");

        // Logs informativos en consola
        System.out.println("Código de status: " + statusCode);
        System.out.println("Primer registro - nombre: " + nombre_0 + ", email: " + mail_0);
        System.out.println("Tercer registro - nombre: " + nombre_2 + ", email: " + mail_2);
    }

    // Ejemplo 3 - GET con estilo BDD
    @Test
    public void Get_Test03() {
        // Usando sintaxis fluida de RestAssured
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200) // Valida que el status sea 200
                .log().body(false); // Imprime el cuerpo en consola
    }
}
