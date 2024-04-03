package org.example.apiStepDef;


import net.thucydides.core.annotations.Steps;
import org.example.apiSteps.StepsApi;
import org.example.apiUtil.Util;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;



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

        stepsApi.verifyStatus(StepsApi::apistatus,2,3,20);
    }





    }
