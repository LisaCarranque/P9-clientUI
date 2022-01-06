package clientUI.proxy;

import clientUI.config.Generated;
import clientUI.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This interface is used as proxy for Feign Client Discovery
 * which allows to use the medicalNotes microservice endpoints
 */
@FeignClient(name = "medicalNotes", url = "172.23.0.6:8082")
@Generated
public interface MedicalNotesProxy {

/*    @RequestMapping("/patientHistory/list")
    public List<Note> listNote();*/

    @RequestMapping("/patientHistory/add")
    public Note addNote(@RequestBody Note note);

    @GetMapping("/patientHistory/update/{id}")
    public Note updateNoteInformation(@PathVariable String id);

    @PostMapping("/patientHistory/update")
    public Note validateUpdate(@RequestBody Note note);

    @RequestMapping("/patientHistory/findNotesByUuid/{uuid}")
    public List<Note> findNotesByUuid(@PathVariable String uuid);
/*
    @RequestMapping("/patientHistory/findNoteById/{id}")
    public Optional<Note> findNoteById(@PathVariable String id);

    @RequestMapping("/patientHistory/findNoteByContent/{id}")
    public Optional<Note> findNoteByContent(@RequestBody String content);*/

}


