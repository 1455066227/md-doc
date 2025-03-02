package cn.isaxon.mddoc;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MdDocApplication {


    public static void main(String[] args) {
        SpringApplication.run(MdDocApplication.class, args);
    }

}
