import com.github.fge.jsonschema.main.JsonSchema;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Resources;
import utils.TestConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetRequests extends TestConfig {

    @Test
    public void verifySuccessResponse() {
        try {
            given()
                    .when()
                    .get(Resources.getUsersPage2EndPointUri())
                    .then()
                    .assertThat().statusCode(HttpStatus.SC_OK).body("page", equalTo(1),
                    "total", equalTo(12));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
        }
    }
    @Test public void
    only_200(){
        given()
                .when()
                .get(Resources.getUsersPage2EndPointUri())
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK).body("data.id", hasItems(7, 8, 9, 10, 11, 12),
                        "data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"));
    }

    @Test
    public void verifyResponseUserDataSchema() {
        try {
            given()
                    .when()
                    .get(Resources.getUserEntryEndPointUri())
                    .then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/schemaFileTesting")).and()
                    .statusCode(HttpStatus.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception thrown Test case failed :" + e.getMessage(), e);
        }
    }
}
