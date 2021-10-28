// 1- Pacotes
package petstore;
// 2 - Bibliotecas

// 3 - Classe

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endere�o da entidade Pet

    // 3.2 - M�todos e Fun��es
    public String lerJson(String caminhojson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhojson)));
    }
    // Incluir - creater - Post
    @Test // identifica o m�todo ou fun��o como um teste para o TestNG
    public void incluitPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe - Gherkin
        // Dado - Quando - Ent�o
        // Given - When - Then

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas era text/xml
                .log().all()
                .body(jsonBody)
        .when() // Quando
                .post(uri)
        .then() // Ent�o
                .log().all()
                .statusCode(200)
                .body("name",is("Bob"))
                .body("status", is ("available"))
                .body("category.name", is("AZ2473LORD"))
                .body("tags.name",contains("data"))

        ;


    }
    @Test
    public void consultarpet(){
        String petId = "1979070653";
        String token =
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Bob"))
                .body("category.name", is("AZ2473LORD"))
                .body("status", is("available"))
        .extract()
                .path("category.name")


        ;
        System.out.println("o token � " + token);
    }

    }



