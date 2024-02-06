package com.coderscampus.coursereportapiintegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MatchDto(
        @JsonProperty("full_name")
        String fullName,
        String email,
        @JsonProperty("phone_number")
        String phoneNumber,
        String experience,
        String availability,
        String location,
        boolean online,
        @JsonProperty("created_at")
        String createdAt,
        String track
) {
}
