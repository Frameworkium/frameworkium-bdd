package com.common;

public class Config {

    private static final String baseURL = System.getProperty("ip");

    private static final String qaURL = System.getProperty("qa.baseURL");


    public static String getBaseURL(){
        return baseURL;
    }

    public static String getQaURL(){
        return qaURL;
    }

}
