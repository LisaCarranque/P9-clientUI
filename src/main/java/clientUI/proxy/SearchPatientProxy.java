package clientUI.proxy;

import clientUI.config.Generated;
import clientUI.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This interface is used as proxy for Feign Client Discovery
 * which allows to use the searchPatient microservice endpoints
 */
@FeignClient(name = "searchPatient", url = "172.23.0.4:8081")
@Generated
public interface SearchPatientProxy {

    @GetMapping("/patient/list")
    public List<Patient> home();

    @PostMapping("/patient/add")
    public Patient addPatientInformation(@RequestBody Patient patient);

    @GetMapping("/patient/update/{id}")
    public Patient updatePatientInformation(@PathVariable String id);

    @PostMapping("/patient/update")
    public Patient validateUpdate(@RequestBody Patient patient);

    @GetMapping("/patient/get/{id}")
    public Patient getPatientById(@PathVariable String id);

    @GetMapping("/patient/getAll")
    public List<Patient> getAll();

    @GetMapping("/patient/getAllDistinctLastnames")
    public List<String> findAllDistinctLastnames();

    @GetMapping("/patient/get/{lastname}")
    public List<Patient> getPatientByLastname(@PathVariable String lastname);

}
