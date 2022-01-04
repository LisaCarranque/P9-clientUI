package clientUI.proxy;

import clientUI.model.Note;
import clientUI.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This interface is used as proxy for Feign Client Discovery
 * which allows to use the MedicalNotes microservice endpoints
 */
@FeignClient(name = "medicalNotes", url = "172.23.0.6:9103")
public interface MedicalNotesProxy {

    @RequestMapping("/note/list")
    public List<Note> listNote();

    @RequestMapping("/note/add")
    public Note addNote(@RequestBody Note note);

    @GetMapping("/note/update/{id}")
    public Note updateNoteInformation(@PathVariable String id);

    @PostMapping("/note/update")
    public Note validateUpdate(@RequestBody Note note);

    @RequestMapping("/note/findNotesByUuid/{uuid}")
    public List<Note> findNotesByUuid(@PathVariable String uuid);

    @RequestMapping("/note/findNoteById/{id}")
    public Optional<Note> findNoteById(@PathVariable String id);

    @RequestMapping("/note/findNoteByContent/{id}")
    public Optional<Note> findNoteByContent(@RequestBody String content);

}


