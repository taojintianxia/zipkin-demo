package com.github.taojintianxia.controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeController {

    @Autowired
    private OkHttpClient client;

    private Random random = new Random();

    private static final int MAX_WORKING_TIME = 1000;


    @RequestMapping("start")
    public String start() throws InterruptedException, IOException {
        int sleep = random.nextInt(MAX_WORKING_TIME);
        TimeUnit.MILLISECONDS.sleep(sleep);
        Request request = new Request.Builder().url("http://localhost:9090/foo").get().build();
        Response response = client.newCall(request).execute();
        return " [service1 sleep " + sleep + " ms]" + response.body().toString();
    }
}
