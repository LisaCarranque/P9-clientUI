package clientUI.proxy;

import clientUI.config.Generated;
import clientUI.model.Report;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * This interface is used as proxy for Feign Client Discovery
 * which allows to use the analyzeData microservice endpoints
 */
@FeignClient(name = "analyzeData", url = "172.23.0.10:8080")
@Generated
public interface AnalyzeDataProxy {

    @PostMapping("/assess/lastname")
    public List<Report> analyzePatientDataByLastname(@RequestParam String lastname);

    @RequestMapping("/assess")
    public Report analyzePatientData(@RequestParam String id);

    @RequestMapping("/assess/all")
    public List<Report> analyzeAllPatientData();

}
