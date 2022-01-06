package clientUI.controller;

import clientUI.dto.NoteDto;
import clientUI.dto.PatientDto;
import clientUI.model.Note;
import clientUI.model.Patient;
import clientUI.model.Report;
import clientUI.proxy.AnalyzeDataProxy;
import clientUI.proxy.MedicalNotesProxy;
import clientUI.proxy.SearchPatientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Autowired
    AnalyzeDataProxy analyzeDataProxy;

    DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * This method is used to display the welcome view
     * @return
     */
    @RequestMapping("/")
    public String index() {
        log.info("Client UI: displays index view");
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
        log.info("Client UI: fetching patient list");
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", PatientDto.builder().build());
        model.addAttribute("note", NoteDto.builder().build());
        log.info("Client UI: displays patient/list view");
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
        log.info("Client UI: adding patient: " + patient.getFirstname()  + " with id " + patient.getId());
        patient.setBirthdate((LocalDate.parse(patient.getBirthdate())).format(formatter));
        searchPatientProxy.addPatientInformation(patient);
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", PatientDto.builder().build());
        model.addAttribute("note", NoteDto.builder().build());
        log.info("Client UI: displays patient/list view");
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
        log.info("Client UI: updates patient with id: " + id);
        Patient patient = searchPatientProxy.updatePatientInformation(id);
        model.addAttribute("patient", patient);
        log.info("Client UI: redirects to patient/edit view");
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
        log.info("Client UI: updates patient data");
        searchPatientProxy.validateUpdate(patient);
        log.info("Client UI: patient data updated");
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", PatientDto.builder().build());
        model.addAttribute("note", NoteDto.builder().build());
        log.info("Client UI: redirects to patient/list view");
        return "patient/list";
    }


    //Patient History
    /**
     * This method is used to list patient history of a patient targeted by uuid
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param uuid the uuid of the targeted patient
     * @return the view note/list containing the list of notes for this patient
     */
    @RequestMapping("/patientHistory/list/{uuid}")
    public String listPatientNote(Model model, @PathVariable String uuid) {
        log.info("Client UI: finding notes by patient uuid: " + uuid);
        model.addAttribute("notes", medicalNotesProxy.findNotesByUuid(uuid));
        model.addAttribute("note", NoteDto.builder().build());
        model.addAttribute("uuid", uuid);
        log.info("Client UI: displays note/list view");
        return "note/list";
    }

    /**
     * This method is responsible for adding a note to patient history
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param note the note to add to the patient history
     * @return the view patient/list with the list of all patients
     */
    @RequestMapping("/patientHistory/add")
    public String addNote(Model model, @Valid Note note) {
        log.info("Client UI: adding note for patient uuid: " + note.getUuid());
        medicalNotesProxy.addNote(note);
        model.addAttribute("patients", searchPatientProxy.home());
        model.addAttribute("patient", PatientDto.builder().build());
        model.addAttribute("note", NoteDto.builder().build());
        log.info("Client UI: displays patient/list view");
        return "patient/list";
    }

    /**
     * This method is used to get a note to update
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param id the id of the note to update
     * @return the view note/edit containing the field to edit note information
     */
    @RequestMapping("/patientHistory/update/{id}")
    public String updateNoteInformation(Model model, @PathVariable String id) {
        log.info("Client UI: updates note with id: " + id);
        Note note = medicalNotesProxy.updateNoteInformation(id);
        model.addAttribute("note", note);
        log.info("Client UI: redirects to note/edit view");
        return "note/edit";
    }

    /**
     * This method is used to update note information
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param note the note to update
     * @return the view note/list with the list of all notes for this patient
     */
    @RequestMapping("/patientHistory/update")
    public String validateUpdate(Model model, @ModelAttribute("note") @Valid Note note) {
        log.info("Client UI: updates note data for patient with uuid: " + note.getUuid());
        medicalNotesProxy.validateUpdate(note);
        log.info("Client UI: note data updated");
        model.addAttribute("notes", medicalNotesProxy.findNotesByUuid(String.valueOf(note.getUuid())));
        model.addAttribute("note", NoteDto.builder().build());
        model.addAttribute("uuid", note.getUuid());
        log.info("Client UI: redirects to note/list view");
        return "note/list";
    }


    //Analyze
    /**
     * This method is used to get an report by patient id
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @param id the id of the targeted patient
     * @return the view assess/list containing the patient report
     */
    @RequestMapping("/assess/{id}")
    public String analyzePatient(Model model, @PathVariable String id) {
        log.info("Client UI: analyzes patient data for patient with id: " + id);
        Report report = analyzeDataProxy.analyzePatientData(id);
        log.info("Client UI: patient data analyzed for patient with id: " + id);
        model.addAttribute("report", report);
        model.addAttribute("reports", new ArrayList<>(Collections.singletonList(report)));
        model.addAttribute("uuid", report.getUuid());
        model.addAttribute("patients", searchPatientProxy.getAll());
        model.addAttribute("lastnames", searchPatientProxy.findAllDistinctLastnames());
        model.addAttribute("patient", PatientDto.builder().build());
        log.info("Client UI: redirects to assess/list view");
        return "assess/list";
    }

    /**
     * This method is used to get the report of a patient targeted by lastname
     * @param patient the patient lastname
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @return the view assess/list containing the report of this targeted by lastname
     * and each report if several patient have the same lastname
     */
    @PostMapping("/assess/lastname")
    public String analyzePatientByLastname(Patient patient, Model model) {
        log.info("Client UI: analyzes patient data for patient with lastname: " + patient.getLastname());
        List<Report> reports = analyzeDataProxy.analyzePatientDataByLastname(patient.getLastname());
        log.info("Client UI: patient data analyzed for patient with lastname: " + patient.getLastname());
        model.addAttribute("reports", reports);
        model.addAttribute("patients", searchPatientProxy.getAll());
        model.addAttribute("lastnames", searchPatientProxy.findAllDistinctLastnames());
        model.addAttribute("patient", PatientDto.builder().build());
        log.info("Client UI: redirects to assess/list view");
        return "assess/list";
    }

    /**
     * This method is used to get all reports for all patients
     * @param model Java-5-specific interface that defines a holder for model attributes.
     * @return the view assess/list containing the list of all reports
     */
    @RequestMapping("/assess/all")
    public String analyzeAllPatients(Model model) {
        log.info("Client UI: analyzes all patient data");
        List<Report> reports = analyzeDataProxy.analyzeAllPatientData();
        model.addAttribute("reports", reports);
        model.addAttribute("lastnames", searchPatientProxy.findAllDistinctLastnames());
        model.addAttribute("patient", PatientDto.builder().build());
        log.info("Client UI: redirects to assess/list view");
        return "assess/list";
    }

}
