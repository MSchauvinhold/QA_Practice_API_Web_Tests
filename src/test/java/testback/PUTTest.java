package testback;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PUTTest {

    // Ejemplo 1 - PUT simple para actualizar un usuario
    @Test
    public void PutTest_01() {
        // Crea el JSON con los datos a actualizar
        JsonObject resquestBody = new JsonObject();
        resquestBody.addProperty("name", "Mateo");
        resquestBody.addProperty("job", "analista");

        // Envía la solicitud PUT y valida que el código de estado sea 200
        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(resquestBody)
                .when()
                .put("https://reqres.in/api/users/12")
                .then()
                .statusCode(200) // Verifica que la actualización fue exitosa
                .log().status();  // Muestra el código de estado en consola
    }

    // Ejemplo 2 - PUT con validaciones y extracción de datos
    @Test
    public void PutTest_02() {
        // Crea el JSON con los datos a actualizar
        JsonObject resquestBody = new JsonObject();
        resquestBody.addProperty("name", "Mateo");
        resquestBody.addProperty("job", "analista");

        // Envía la solicitud PUT y guarda la respuesta
        Response responseBody = given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(resquestBody)
                .when()
                .put("https://reqres.in/api/users/13");

        // Extrae campos específicos de la respuesta
        String nombreModificado = responseBody.jsonPath().getString("name");
        String trabajoModificado = responseBody.jsonPath().getString("job");
        String fechaModificada = responseBody.jsonPath().getString("updatedAt");

        // Validaciones simples
        Assert.assertEquals(nombreModificado, "Mateo");
        Assert.assertEquals(trabajoModificado, "analista");
        Assert.assertEquals(responseBody.statusCode(), 200);

        // Imprime información de la respuesta en consola
        System.out.println("Nombre modificado: " + nombreModificado);
        System.out.println("Trabajo modificado: " + trabajoModificado);
        System.out.println("Fecha de actualización: " + fechaModificada);
        System.out.println("Código de respuesta: " + responseBody.statusCode());
        System.out.println("Tiempo de respuesta: " + responseBody.getTime() + " ms");
    }
}
