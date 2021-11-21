package com.swagger.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import java.util.ArrayList;

/**
 * @author CJYong
 * @create 2021-11-21 15:30
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket groupone(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket grouptwo(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("b");
    }

    @Bean
    public Docket groupthree(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("c");
    }



    /**
     * 通过 createRestApi函数来构建一个DocketBean实例 ,可以随意命名
     */
    @Bean
    public Docket createRestApi(Environment environment) {
        //设置要显示swagger的环境
        Profiles profiles = Profiles.of("dev","test");
        //获取生产环境
        //通过environment.acceptsProfiles(profiles)判断自己是否处在指定环境
        boolean acceptsProfiles = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("MyGroup")             // 分组功能
                .enable(acceptsProfiles)          // 是否开启swagger
                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage指定扫描的包
                //any扫描全部的包
                //none()都不扫描
                //withClassAnnotation()扫描类上的注解，参数是注解的反射对象
                //withMethodAnnotation()扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.swagger.controller"))
                //paths()，过滤路径，只让/kuang开头的请求通过
//                .paths(PathSelectors.ant("/jianyong/**"))
                .build();
    }

    /**
     * 构建 api文档的详细信息函数
     * @return
     */
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("CJYong","https://www.baidu.com/","624597867@qq.com");
        return new ApiInfo(
                "CJY的Swagger日记",
                "本人描述",
                "v1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}

