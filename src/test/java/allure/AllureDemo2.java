package allure;

import io.qameta.allure.Step;

public class AllureDemo2 {

    @Step("amazing inner method")
    public static void goingEvenDeeper(){
        System.out.println("inner");
    }

}
