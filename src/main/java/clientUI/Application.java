package clientUI;

import clientUI.proxy.AnalyzeDataProxy;
import clientUI.proxy.MedicalNotesProxy;
import clientUI.proxy.SearchPatientProxy;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.util.Locale;

/**
 * The main class for this clientUI microservice
 */
@Slf4j
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"clientUI"})
@EnableFeignClients(clients = {SearchPatientProxy.class, MedicalNotesProxy.class, AnalyzeDataProxy.class})
@Generated
public class Application {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        log.info("Launch clientUI module");
        SpringApplication.run(Application.class, args);
    }

}
