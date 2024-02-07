import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://postman-echo.com")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    public void shouldPostData() {
// Given - When - Then
// Предусловия
        given()
                .spec(requestSpec)
                .body("{\n" +
                        "\"name\": \"Ivan\",\n" +
                        "\"surname\": \"Ivanov\",\n" +
                        "\"age\": 13\n" +
                        "}")
// Выполняемые действия
                .when()
                .post("/post")
// Проверки
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.name", equalTo("Ivan"))
                .body("data.age", greaterThanOrEqualTo(18))
        ;
    }
}
