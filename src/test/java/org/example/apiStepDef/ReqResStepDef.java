package org.example.apiStepDef;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.awaitility.Awaitility;
import org.example.apiUtil.Util;
import org.jbehave.core.annotations.Given;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class ReqResStepDef {

    @Given("I am getting user details")
    public void getUser()  {

        System.out.println(Util.config("Module_Name").get(0).get("ABC"));
        System.out.println(Util.configkey().get("name"));

       /* Response response= RestAssured.given().log().all()
                .when()
                .get("https://reqres.in/api/users?page=2");

        response.prettyPrint();*/


        Awaitility.await()
                .timeout(66, TimeUnit.SECONDS)
                .pollDelay(0, TimeUnit.SECONDS)
                .pollInterval(5, TimeUnit.SECONDS)
                .until(() -> {

                    Response response= RestAssured.given().log().all()
                            .when()
                            .get("https://reqres.in/api/users?page=2");
                    response.statusCode();
                    return response.statusCode() == 200;

                });

    }
    @Given("I am fetching user list")
    public void fetchUserList()  {

        verifyStatus(ReqResStepDef::commstatus,2,3,20);
    }

    public void verifyStatus(Predicate<Response> predicate, int delay, int pollInterval, int timeout )
    {
        Callable<Response> callable= () -> getuserList();

        Response response=Awaitility.await().pollInSameThread()
                .pollDelay(Duration.ofSeconds(delay))
                .pollInterval(Duration.ofSeconds(pollInterval))
                .atMost(Duration.ofSeconds(timeout))
                .until(callable,predicate);

        response.prettyPrint();



    }
    public static boolean commstatus(Response response)
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
