package com.github.taojintianxia.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.taojintianxia.config.BasicConfig;
import com.github.taojintianxia.config.ZipkinProperties;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private BasicConfig basicConfig;

    @Autowired
    private ZipkinProperties zipkinProperties;

    private Random random = new Random();

    private static final int MAX_WORKING_TIME = 1000;

    @RequestMapping("serviceE")
    public String start() throws InterruptedException, IOException {
        String result = zipkinProperties.getServiceName() + " is executing";
        int sleep = random.nextInt(MAX_WORKING_TIME);
        TimeUnit.MILLISECONDS.sleep(sleep);
        List<String> uris = basicConfig.getRedirectURI();
        if (uris.isEmpty()) {
            return result;
        } else {
            for (String uri : uris) {
                Request request = new Request.Builder().url(uri).get().build();
                Response response = client.newCall(request).execute();
                result = result + "\n " + zipkinProperties.getServiceName() + " works on " + sleep + " ms]"
                        + response.body().toString();
            }
        }
        System.out.println("----------------:" + uris.get(1));
        return null;
    }
}
