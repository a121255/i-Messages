package com.test.imessagesbackend.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("i-Message Api Dpcument")
                .version("1.0")
                .description("i-Message 프로젝트 API 사용 방법을 설명합니다.");
        return new OpenAPI()
                .info(info);
    }
}
