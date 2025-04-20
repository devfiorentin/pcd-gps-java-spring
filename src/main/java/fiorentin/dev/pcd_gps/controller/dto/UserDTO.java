package fiorentin.dev.pcd_gps.controller.dto;

public record UserDTO(String firstName, String lastName, int age, boolean pcd, String carModel, String plate, double latitude, double longitude) {}
