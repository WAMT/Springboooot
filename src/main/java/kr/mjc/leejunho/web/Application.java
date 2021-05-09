package kr.mjc.leejunho.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //웹 서블릿을 스캔한다.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
