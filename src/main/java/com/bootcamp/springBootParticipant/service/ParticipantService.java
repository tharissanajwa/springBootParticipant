package com.bootcamp.springBootParticipant.service;

import com.bootcamp.springBootParticipant.model.Participant;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
// Kelas ini bertanggung jawab untuk mengelola data peserta.
public class ParticipantService {

    private List<Participant> participantData = new ArrayList<>(); // List untuk menyimpan data peserta.

    // Mendapatkan daftar data peserta.
    public List<Participant> getParticipantData() {
        return participantData;
    }

    private String statusMessage; // Pesan status untuk memberi informasi kepada pengguna.

    // Mendapatkan pesan status.
    public String getStatusMessage() {
        return statusMessage;
    }

    // Menambahkan peserta baru ke dalam daftar peserta.
    public List<Participant> addParticipant(List<Participant> participant, String name, String address, String phoneNumber, String status) {
        // Menghilangkan spasi ekstra dari nama, alamat, dan nomor telepon.
        String nameTrim = nameTrim(name);
        String addressTrim = addressTrim(address);
        String phoneNumberTrim = phoneNumberTrim(phoneNumber);

        // Melakukan validasi terhadap nama, alamat, dan nomor telepon.
        boolean nameValidation = nameValidation(nameTrim);
        boolean addressValidation = addressValidation(addressTrim);
        boolean phoneNumberValidation = phoneNumberValidation(phoneNumberTrim);

        // Jika semua validasi terpenuhi, tambahkan peserta baru.
        if (nameValidation && addressValidation && phoneNumberValidation) {
            participant.add(new Participant(nameTrim, addressTrim, phoneNumberTrim, status));
        }
        return participant;
    }

    // Validasi apakah nama peserta baru sesuai kriteria.
    private boolean nameValidation(String name) {
        boolean nameValidation = true;
        boolean nameExist = false; // Nama belum ada dalam data

        // Periksa apakah nama sudah ada dalam data.
        for (Participant participant : getAllParticipant()) {
            if (name != null && !name.isEmpty() && name.equalsIgnoreCase(participant.getName())) {
                nameValidation = false;
                nameExist = true; // Nama sudah ada dalam data
                break;
            }
        }

        // Validasi jika nama kosong atau sudah ada dalam data.
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Sorry, name cannot be empty.");
        } else if (nameExist) {
            throw new IllegalArgumentException("Sorry, name already exists.");
        }

        return nameValidation;
    }

    // Validasi apakah alamat peserta tidak kosong.
    private boolean addressValidation(String address) {
        boolean addressValidation = false;
        if (address != null && !address.isEmpty()) {
            addressValidation = true;
        }

        if (address.isEmpty()) {
            throw new IllegalArgumentException("Sorry, address cannot be empty.");
        }
        return addressValidation;
    }

    // Validasi nomor telepon peserta sesuai format.
    private boolean phoneNumberValidation(String phoneNumber) {
        boolean phoneNumberValidation = false;
        if (phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.matches("^[0-9+]{10,15}$")) {
            phoneNumberValidation = true;
        }

        // Validasi nomor telepon tidak boleh null atau kosong, dan sesuai format.
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Sorry, phone number cannot be null.");
        } else if (phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Sorry, phone number cannot be empty.");
        } else if (!phoneNumber.matches("^[0-9+]{10,15}$")) {
            throw new IllegalArgumentException("Sorry, phone number must be 10 - 15 digits.");
        }

        return phoneNumberValidation;
    }

    // Menghilangkan spasi ekstra dari nama.
    private String nameTrim(String name) {
        return name.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", " ");
    }

    // Menghilangkan spasi ekstra dari alamat.
    private String addressTrim(String address) {
        return address.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", " ");
    }

    // Menghilangkan spasi ekstra dari nomor telepon.
    private String phoneNumberTrim(String phoneNumber) {
        return phoneNumber.replaceAll("^\\s+|\\s+$", "");
    }

    // Inisialisasi data awal peserta jika belum ada.
    private void seedParticipant() {
        if (getParticipantData().size() == 0) {
            getParticipantData().add(new Participant("Tharissa Najwa", "Bandung", "+6282126867931", "active"));
            getParticipantData().add(new Participant("Putri Budiman", "Cimahi", "+6285321868782", "active"));
        }
    }

    // Mendapatkan daftar peserta yang belum dihapus.
    public List<Participant> getAllParticipant() {
        seedParticipant();
        List<Participant> result = new ArrayList<>();
        for (Participant participant : getParticipantData()) {
            if (!participant.getDeleted()) {
                result.add(participant);
            }
        }
        return result;
    }

    // Menghapus peserta berdasarkan nama.
    public boolean deleteParticipant(String name) {
        List<Participant> result = getAllParticipant();
        boolean success = true;
        for (Participant participant: result) {
            if (participant.getName().equalsIgnoreCase(name)) {
                participant.isDeleted();
                statusMessage = "You have successfully deleted data.";
                break;
            } else {
                statusMessage = "Participant data not found.";
                success = false;
            }
        }
        return success;
    }

    // Memperbarui informasi peserta.
    public List<Participant> updateParticipant(String newName, String oldName, String newAddress, String newPhoneNumber) {
        List<Participant> result = new ArrayList<>();
        String newNameTrim = nameTrim(newName);
        String oldNameTrim = nameTrim(oldName);
        String newAddressTrim = addressTrim(newAddress);
        String newPhoneNumberTrim = phoneNumberTrim(newPhoneNumber);

        boolean newNameValidation = nameUpdateValidation(newNameTrim);
        boolean newAddressValidation = addressValidation(newAddressTrim);
        boolean newPhoneNumberValidation = phoneNumberValidation(newPhoneNumberTrim);

        for (Participant participant: getAllParticipant()) {
            if (participant.getName().equalsIgnoreCase(oldNameTrim)) {
                if (!newNameValidation && newAddressValidation && newPhoneNumberValidation) {
                    participant.setName(newNameTrim);
                    participant.setAddress(newAddressTrim);
                    participant.setPhoneNumber(newPhoneNumberTrim);
                    result.add(participant);
                    break;
                }
            }
        }
        return result;
    }

    // Validasi nama saat melakukan pembaruan.
    private boolean nameUpdateValidation(String name) {
        boolean nameValidation = true;
        if (name != null && !name.isEmpty()) {
            nameValidation = false;
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Sorry, name cannot be empty.");
        }
        return nameValidation;
    }
}
