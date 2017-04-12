package com.github.taojintianxia.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class BasicConfig {

    @Value("#{'${server.redirect.uri}'.split(',')}")
    private List<String> redirectURI;

}
