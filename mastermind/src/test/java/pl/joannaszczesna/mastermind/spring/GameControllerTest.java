/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.spring;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class GameControllerTest {
    private static String baseUri;
    private static final String GUESS_CODE_ENDPOINT_PATH = "/guess-code";
    private static List<Integer> VALID_LENGTH_USER_CODE = List.of(11111);

    @BeforeAll
    static void setUp(@LocalServerPort int port) {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        baseUri = "%s:%d".formatted(RestAssured.baseURI, port);
    }

    private static String makeGuessCodeRequestBody(List<Integer> code) {
        return """
                {
                "%d"
                }
                """.formatted(code);
    }

    @Test
    void guessCode_whenSuccessfulOperation_returnStatus200() {
        given().body(makeGuessCodeRequestBody(VALID_LENGTH_USER_CODE))
                .contentType(ContentType.JSON)
                .when()
                .post(GUESS_CODE_ENDPOINT_PATH)
                .then()
                .assertThat().statusCode(200);
    }
}
