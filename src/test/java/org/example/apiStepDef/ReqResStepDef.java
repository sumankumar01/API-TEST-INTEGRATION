package org.example.apiStepDef;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.awaitility.Awaitility;
import org.example.apiSteps.StepsApi;
import org.example.apiUtil.Util;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class ReqResStepDef {

    @Steps
    StepsApi stepsApi;


    @Given("I am getting user details of page $num")
    public void getUser(@Named("num") int num)  {

        System.out.println(Util.config("Module_Name").get(0).get("ABC"));
        System.out.println(Util.configkey().get("name"));

        stepsApi.getUserDetails(num);


    }
    @Given("I am fetching user list")
    public void fetchUserList()  {

        verifyStatus(ReqResStepDef::apistatus,2,3,20);
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
    public static boolean apistatus(Response response)
    {
        ResponseBody responseBody=response.getBody();
        int status=response.getStatusCode();

        return  status == 200;
    }

    public Response getuserList()
    {

        Response response= RestAssured.given().log().all()
                .when()
                .get("https://reqres.in/api/users?delay=3");
        return response;
    }


    }
