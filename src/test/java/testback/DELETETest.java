package testback;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DELETETest {

    // Ejemplo 1 - DELETE directo usando el endpoint completo
    @Test
    public void DeleteTest_01() {
        // Realiza la solicitud DELETE para eliminar el usuario con ID 2
        Response responseBody = given()
                .header("x-api-key","reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/2");

        // Verifica que el código de respuesta sea 204 (sin contenido)
        Assert.assertEquals(responseBody.statusCode(), 204);

        // Imprime información de respuesta en consola
        System.out.println("El codigo de respuesta es: " + responseBody.statusCode());
        System.out.println("Tiempo de respuesta: " + responseBody.getTime() + " ms");
    }

    // Ejemplo 2 - DELETE con construcción dinámica de la URL
    @Test
    public void DeleteTest_02() {
        // Variables para construir la URL
        String urlREQRES = "https://reqres.in/api";
        String pathUser = "/users";
        String DeleteUser = "/13";
        String APIKeyPass = "reqres-free-v1";
        String APIKeyName = "x-api-key";

        // Realiza la solicitud DELETE usando la URL construida
        Response responseBody = given()
                .header(APIKeyName, APIKeyPass)
                .when()
                .delete(urlREQRES + pathUser + DeleteUser);

        // Verifica que el código de respuesta sea 204
        Assert.assertEquals(responseBody.statusCode(), 204);

        // Imprime información de respuesta en consola
        System.out.println("El codigo de respuesta es: " + responseBody.statusCode());
        System.out.println("Tiempo de respuesta: " + responseBody.getTime() + " ms");
    }
}


