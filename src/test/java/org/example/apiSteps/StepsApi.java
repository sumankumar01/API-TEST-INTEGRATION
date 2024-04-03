package org.example.apiSteps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.thucydides.core.annotations.Step;
import org.awaitility.Awaitility;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

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
                    response.prettyPrint();
                    return response.statusCode() == 200;

                });
    }
    @Step("fetching the user detail list by polling")
    public void verifyStatus(Predicate<Response> predicate, int delay, int pollInterval, int timeout)
    {
        Callable<Response> callable= () -> getuserList();

        Response response=Awaitility.await().pollInSameThread()
                .pollDelay(Duration.ofSeconds(delay))
                .pollInterval(Duration.ofSeconds(pollInterval))
                .atMost(Duration.ofSeconds(timeout))
                .until(callable,predicate);

        response.prettyPrint();


    }
    public static boolean apistatus(Response response)
    {
        ResponseBody responseBody=response.getBody();
        int status=response.getStatusCode();

        return  status == 400;
    }

    public Response getuserList()
    {

        Response response= RestAssured.given().log().all()
                .when()
                .get("https://reqres.in/api/users?delay=3");
        return response;
    }
}
