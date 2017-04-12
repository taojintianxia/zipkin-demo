package com.github.taojintianxia.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.taojintianxia.config.BasicConfig;

import okhttp3.OkHttpClient;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private BasicConfig basicConfig;

    private Random random = new Random();

    private static final int MAX_WORKING_TIME = 1000;

    @RequestMapping("start")
    public String start() throws InterruptedException, IOException {
        int sleep = random.nextInt(MAX_WORKING_TIME);
        TimeUnit.MILLISECONDS.sleep(sleep);
        // Request request = new Request.Builder().url(basicConfig.getRedirectURI()).get().build();
        // Response response = client.newCall(request).execute();
        // return " [ServericeA works on " + sleep + " ms]" + response.body().toString();
        List<String> uris = basicConfig.getRedirectURI();
        System.out.println("----------------:" + uris.get(1));
        return null;
    }
}
