package com.example.configlistshapes;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@SpringBootApplication
public class ConfigListShapesApplication {

    @Autowired
    private ShapesConfig shapesConfig;

    public static void main(String[] args) {
        SpringApplication.run(ConfigListShapesApplication.class, args);
    }

    @PostConstruct
    public void logResults() {
        log.debug("We have {} shapes configured", shapesConfig.getShapes().size());
        for(Shape shape : shapesConfig.getShapes()) {
            log.debug("Shape is of type {} and class is instance of {}",
                    shape.getType(), shape.getClass().getSimpleName());
        }
    }

    @Data
    @Component
    @ConfigurationProperties(prefix="app")
    public class ShapesConfig {
        private List<Shape> shapes;
    }

    @Data
    public class Shape {
        private String type;
    }

    @Data
    public class Circle extends Shape {
        private Integer radius;
        private Colors colors;
    }

    @Data
    public class Rectangle extends Shape {
        private Integer length;
        private Integer width;
        private Colors colors;
    }

    @Data
    public class Triangle extends Shape {
        private Integer base;
        private Integer height;
    }

    @Data
    public class Colors {
        private String fill;
        private String border;
    }
}
