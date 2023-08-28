package com.bootcamp.springBootParticipant.controller;

import com.bootcamp.springBootParticipant.model.Participant;
import com.bootcamp.springBootParticipant.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/participants")
// Kelas ini bertindak sebagai controller untuk mengatur permintaan terkait peserta.
public class ParticipantController {

    @Autowired
    private ParticipantService participantService; // Layanan untuk mengelola data peserta.

    @GetMapping("")
    // Mengambil semua data peserta.
    private ResponseEntity getAllParticipant() {
        List<Participant> participantList = participantService.getAllParticipant();
        if (participantList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(participantService.getStatusMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(participantList);
    }

    @PostMapping("")
    // Membuat peserta baru.
    private ResponseEntity createNewParticipant(@RequestBody Participant participants) {
        try {
            List<Participant> participantList = participantService.addParticipant(participantService.getParticipantData(), participants.getName(), participants.getAddress(), participants.getPhoneNumber());
            return ResponseEntity.status(HttpStatus.CREATED).body(participantList);
        } catch (IllegalArgumentException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input : " + error.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    // Menghapus peserta berdasarkan nama.
    private ResponseEntity deleteParticipant(@PathVariable("name") String name) {
        boolean success = participantService.deleteParticipant(name);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(participantService.getStatusMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(participantService.getStatusMessage());
        }
    }

    @PutMapping("/{name}")
    // Memperbarui informasi peserta.
    private ResponseEntity updateParticipant(@PathVariable("name") String oldName, @RequestBody Participant participant) {
        try {
            List<Participant> participants = participantService.updateParticipant(participant.getName(), oldName, participant.getAddress(), participant.getPhoneNumber());
            return ResponseEntity.status(HttpStatus.OK).body(participants);
        } catch (IllegalArgumentException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input : " + error.getMessage());
        }
    }

}
