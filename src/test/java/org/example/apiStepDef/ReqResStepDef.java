package org.example.apiStepDef;

import org.example.apiUtil.Util;
import org.jbehave.core.annotations.Given;

import java.io.FileNotFoundException;

public class ReqResStepDef {

    @Given("I am getting user details")
    public void getUser()  {

        System.out.println(Util.config("Module_Name").get(0).get("ABC"));
        System.out.println(Util.configkey().get("name"));


    }
}
