import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Resources;
import utils.TestConfig;

import static io.restassured.RestAssured.given;

public class PutRequests extends TestConfig {

    @Test
    public void verifyUpdateUserDetails(){
        try {
            given()
                        .header("Content-Type", "application/json")
                        .body(userPersonalDetailsPayload)
                    .when()
                        .put(Resources.getUserEntryEndPointUri())
                    .then()
                        .statusCode(HttpStatus.SC_OK);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail("Exception thrown Test case failed (!!!) :" + e.getMessage());
        }
    }
}
