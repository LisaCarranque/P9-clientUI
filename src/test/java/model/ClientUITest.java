package model;

import clientUI.controller.ClientUIController;
import clientUI.model.Note;
import clientUI.model.Patient;
import clientUI.proxy.MedicalNotesProxy;
import clientUI.proxy.SearchPatientProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientUITest {


    @Spy
    Model model;

    @Mock
    SearchPatientProxy searchPatientProxy;

    @Mock
    MedicalNotesProxy medicalNotesProxy;

    @InjectMocks
    ClientUIController clientUI;


    @Test
    public void indexTest() {
        clientUI.index();
        verifyZeroInteractions(searchPatientProxy);
        verifyZeroInteractions(medicalNotesProxy);
    }


    //Patient
    @Test
    public void getPatientsTest() {
        clientUI.getPatients(model);
        verify(searchPatientProxy, times(1)).home();
    }

    @Test
    public void addPatientTest() {
        UUID uuid = UUID.randomUUID();
        Patient patient = Patient.builder().id(1).firstname("John")
                .lastname("Smith").address("1st street").phone("00000000000").uuid(uuid).birthdate("2002-12-02").genre("M").build();
        clientUI.addPatient(model, patient);
        verify(searchPatientProxy, times(1)).addPatientInformation(patient);
        verify(searchPatientProxy, times(1)).home();
    }

    @Test
    public void updatePatientInformationTest() {
        UUID uuid = UUID.randomUUID();
        Patient patient = Patient.builder().id(1).firstname("John")
                .lastname("Smith").address("1st street").phone("00000000000").uuid(uuid).birthdate("2002-12-02").genre("M").build();
        clientUI.updatePatientInformation(model, "1");
        verify(searchPatientProxy, times(1)).updatePatientInformation("1");
    }

    @Test
    public void validateUpdateSearchPatientTest() {
        UUID uuid = UUID.randomUUID();
        Patient patient = Patient.builder().id(1).firstname("John")
                .lastname("Smith").address("1st street").phone("0000000000").uuid(uuid).birthdate("2002-12-02").genre("M").build();
        clientUI.validateUpdate(model, patient);
        verify(searchPatientProxy, times(1)).validateUpdate(patient);
        verify(searchPatientProxy, times(1)).home();
    }


    //Notes
    @Test
    public void listPatientNoteTest() {
        UUID uuid = UUID.randomUUID();
        clientUI.listPatientNote(model, String.valueOf(uuid));
        verify(medicalNotesProxy, times(1)).findNotesByUuid(String.valueOf(uuid));
    }

    @Test
    public void addNoteTest() {
        UUID uuid = UUID.randomUUID();
        Note note = Note.builder().id("1").content("Cholesterol").uuid(uuid).build();
        clientUI.addNote(model, note);
        verify(medicalNotesProxy, times(1)).addNote(note);
        verify(searchPatientProxy, times(1)).home();
    }

    @Test
    public void updateNoteInformationTest() {
        clientUI.updateNoteInformation(model, "1");
        verify(medicalNotesProxy, times(1)).updateNoteInformation("1");
    }

    @Test
    public void validateUpdateMedicalNoteTest() {
        UUID uuid = UUID.randomUUID();
        Note note = Note.builder().id("1").content("Dizziness").uuid(uuid).build();
        clientUI.validateUpdate(model, note);
        verify(medicalNotesProxy, times(1)).validateUpdate(note);
        verify(medicalNotesProxy, times(1)).findNotesByUuid(String.valueOf(note.getUuid()));
    }


}
