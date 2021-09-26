// 1- Pacotes
package petstore;
// 2 - Bibliotecas

// 3 - Classe

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhojson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhojson)));
    }
    // Incluir - creater - Post
    @Test // identifica o método ou função como um teste para o TestNG
    public void incluitPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe - Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas era text/xml
                .log().all()
                .body(jsonBody)
        .when() // Quando
                .post(uri)
        .then() // Então
                .log().all()
                .statusCode(200)
        ;


    }


}
