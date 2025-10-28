package testback;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class POSTTest {

    // Ejemplo 1 - POST básico para crear un usuario
    @Test
    public void PostTest_01() {
        // Crea un objeto JSON con los datos del usuario
        JsonObject request = new JsonObject();
        request.addProperty("name", "Mateo");
        request.addProperty("job", "tester");

        // Envía la solicitud POST y valida el código 201
        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .when()
                .body(request).post("https://reqres.in/api/users")
                .then()
                .statusCode(201) // Verifica que el usuario fue creado
                .log().status()  // Muestra el código de estado
                .log().body();   // Muestra el cuerpo de la respuesta
    }

    // Ejemplo 2 - POST con validaciones de contenido
    @Test
    public void PostTest_02() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "MateoS");
        request.addProperty("job", "testerQA");

        // Envía la solicitud POST y valida tanto el status como los campos devueltos
        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(request)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .statusCode(201)
                .log().body()
                .body("name", equalTo("MateoS"))
                .body("job", equalTo("testerQA"));
    }

    // Ejemplo 3 - POST fallido (login sin contraseña)
    @Test
    public void PostTestFallido() {
        // Crea el objeto JSON con solo el email
        JsonObject request = new JsonObject();
        request.addProperty("email", "mateus@outlook");

        // Envía la solicitud POST y recibe la respuesta
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(request)
                .when()
                .post("https://reqres.in/api/login");

        // Extrae el mensaje de error
        String error = response.jsonPath().getString("error");

        // Valida que el status sea 400 y que el mensaje de error sea el esperado
        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(error, "Missing password");

        // Imprime información relevante de la respuesta
        System.out.println(response.getBody().asString());
        System.out.println("El mensaje de error es: " + error);
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Tiempo de respuesta: " + response.time() + " ms");
    }
}
