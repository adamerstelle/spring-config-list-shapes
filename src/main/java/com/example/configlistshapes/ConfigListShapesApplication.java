package com.example.configlistshapes;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@SpringBootApplication
public class ConfigListShapesApplication implements CommandLineRunner {

    @Autowired
    private ShapesConfig shapesConfig;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConfigListShapesApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("We have {} shapes configured", shapesConfig.getShapes().size());
        for(Shape shape : shapesConfig.getShapes()) {
            log.info("Shape is of type {} and class is instance of {}",
                    shape.getType(), shape.getClass().getSimpleName());
        }
    }

    @Data
    @Component
    @ConfigurationProperties(prefix="app")
    public static class ShapesConfig {
        private List<Shape> shapes;
    }

    @Data
    public static class Shape {
        private String type;
    }

    @Data
    public static class Circle extends Shape {
        private Integer radius;
        private Colors colors;
    }

    @Data
    public static class Rectangle extends Shape {
        private Integer length;
        private Integer width;
        private Colors colors;
    }

    @Data
    public static class Triangle extends Shape {
        private Integer base;
        private Integer height;
    }

    @Data
    public static class Colors {
        private String fill;
        private String border;
    }
}
