package org.example.apiSteps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

public class StepsApi {



    @Step("fetching the user details page wise")
    public void getUserDetails(int page)
    {
        Awaitility.await()
                .timeout(66, TimeUnit.SECONDS)
                .pollDelay(0, TimeUnit.SECONDS)
                .pollInterval(5, TimeUnit.SECONDS)
                .until(() -> {

                    Response response= RestAssured.given().log().all()
                            .when()
                            .get("https://reqres.in/api/users?page="+page);
                    response.statusCode();
                    return response.statusCode() == 200;

                });
    }
}
