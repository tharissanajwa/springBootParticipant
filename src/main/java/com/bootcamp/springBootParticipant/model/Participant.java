package com.bootcamp.springBootParticipant.model;

// Ini adalah kelas Participant yang merepresentasikan data seorang peserta.
public class Participant {
    private String name;          // Nama peserta
    private String address;       // Alamat peserta
    private String phoneNumber;   // Nomor telepon peserta
    private String status;        // Status peserta, misalnya "active" atau "not active"
    private boolean isDeleted = false; // Status penghapusan peserta

    // Konstruktor untuk membuat objek Participant.
    public Participant(String name, String address, String phoneNumber, String status) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    // Getter untuk mendapatkan nama peserta.
    public String getName() {
        return name;
    }

    // Setter untuk mengubah nama peserta.
    public void setName(String name) {
        this.name = name;
    }

    // Getter untuk mendapatkan alamat peserta.
    public String getAddress() {
        return address;
    }

    // Setter untuk mengubah alamat peserta.
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter untuk mendapatkan nomor telepon peserta.
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter untuk mengubah nomor telepon peserta.
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter untuk mendapatkan status peserta.
    public String getStatus() {
        return status;
    }

    // Setter untuk mengubah status peserta.
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter untuk mendapatkan informasi apakah peserta telah dihapus atau tidak.
    public boolean getDeleted() {
        return isDeleted;
    }

    // Method untuk menandai bahwa peserta telah dihapus.
    public void isDeleted() {
        this.isDeleted = true;
    }
}
