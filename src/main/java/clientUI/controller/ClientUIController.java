package clientUI.controller;

import clientUI.model.Note;
import clientUI.model.Patient;
import clientUI.proxy.MedicalNotesProxy;
import clientUI.proxy.SearchPatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

/**
 * This controller is used to gather the microservices data and redirect to the user interface
 */
@Slf4j
@Controller
public class ClientUIController {

    @Autowired
    SearchPatientProxy searchPatientProxy;

    @Autowired
    MedicalNotesProxy medicalNotesProxy;

    /**
     * This method is used to display the welcome view
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }


    //Patient
    /**
     * This method is used to get the list of patients
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @return the view patient/list with the list of all patients
     */
    @RequestMapping("/patient/list")
    public String getPatients(Model model) {
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", Patient.builder().build());
        model.addAttribute("note", Note.builder().build());
        return "patient/list";
    }

    /**
     * This method is used to add a patient
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param patient the patient to add
     * @return the view patient/list with the list of all patients containing the newly added patient
     */
    @RequestMapping("/patient/add")
    public String addPatient(Model model, @Valid Patient patient) {
        patient.setUuid(UUID.randomUUID());
        searchPatientProxy.addPatientInformation(patient);
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", Patient.builder().build());
        model.addAttribute("note", Note.builder().build());
        return "patient/list";
    }

    /**
     * This method is used to get the data of a targeted patient to update
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param id the id of the targeted patient
     * @return the view patient/edit with the field to edit
     */
    @RequestMapping("/patient/update/{id}")
    public String updatePatientInformation(Model model, @PathVariable String id) {
        log.info("client ui controller update patient with id : " + id);
        Patient patient = searchPatientProxy.updatePatientInformation(id);
        model.addAttribute("patient", patient);
        log.info("redirect to patient/edit");
        return "patient/edit";
    }

    /**
     * The method is used to update patient information
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param patient the patient to update
     * @return the view patient/list with the list of all patients containing the newly updated patient
     */
    @RequestMapping("/patient/update")
    public String validateUpdate(Model model, @ModelAttribute("patient") @Valid Patient patient) {
        log.info("update patient data");
        searchPatientProxy.validateUpdate(patient);
        log.info("patient data updated");
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", Patient.builder().build());
        model.addAttribute("note", Note.builder().build());
        log.info("redirect to patient/list");
        return "patient/list";
    }


    //Notes
    /**
     * This method is used to list patient history of a patient targeted by uuid
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param uuid the uuid of the targeted patient
     * @return the view note/list containing the list of notes for this patient
     */
    @RequestMapping("/note/list/{uuid}")
    public String listPatientNote(Model model, @PathVariable String uuid) {
        model.addAttribute("notes", medicalNotesProxy.findNotesByUuid(uuid));
        model.addAttribute("note", Note.builder().build());
        model.addAttribute("uuid", uuid);
        return "note/list";
    }

    /**
     * This method is responsible for adding a note to patient history
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param note the note to add to the patient history
     * @return the view patient/list with the list of all patients
     */
    @RequestMapping("/note/add")
    public String addNote(Model model, @Valid Note note) {
        medicalNotesProxy.addNote(note);
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", Patient.builder().build());
        model.addAttribute("note", Note.builder().build());
        return "patient/list";
    }

    /**
     * This method is used to get a note to update
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param id the id of the note to update
     * @return the view note/edit containing the field to edit note information
     */
    @RequestMapping("/note/update/{id}")
    public String updateNoteInformation(Model model, @PathVariable String id) {
        log.info("client ui controller update note with id : " + id);
        Note note = medicalNotesProxy.updateNoteInformation(id);
        model.addAttribute("note", note);
        log.info("redirect to note/edit");
        return "note/edit";
    }

    /**
     * This method is used to update note information
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param note the note to update
     * @return the view note/list with the list of all notes for this patient
     */
    @RequestMapping("/note/update")
    public String validateUpdate(Model model, @ModelAttribute("note") @Valid Note note) {
        log.info("update note data");
        medicalNotesProxy.validateUpdate(note);
        log.info("note data updated");
        model.addAttribute("notes", medicalNotesProxy.findNotesByUuid(String.valueOf(note.getUuid())));
        model.addAttribute("note", Note.builder().build());
        model.addAttribute("uuid", note.getUuid());
        log.info("redirect to note/list");
        return "note/list";
    }

}
