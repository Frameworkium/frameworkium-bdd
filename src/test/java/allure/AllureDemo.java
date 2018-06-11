package allure;

import io.qameta.allure.Step;

public class AllureDemo {

//    @Step("amazing method")
    public static void innerMethodForGiven(){
        System.out.println("given line...");
    }

//    @Step("amazing method")
    public static void innerMethodForWhen(){
        System.out.println("when line...");
    }

//    @Step("amazing method")
    public static void innerMethodForThen(){
        AllureDemo2.goingEvenDeeper();
        System.out.println("then line...");
    }

}
