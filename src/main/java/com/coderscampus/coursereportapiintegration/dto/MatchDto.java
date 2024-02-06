package com.coderscampus.coursereportapiintegration.dto;

import com.coderscampus.coursereportapiintegration.config.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

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
        @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class )
        LocalDateTime createdAt,
        String track
) {
}
