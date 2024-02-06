package com.coderscampus.coursereportapiintegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaginationDto(
        @JsonProperty("current_page")
        String currentPage,
        @JsonProperty("total_objects")
        Integer totalObjects,
        @JsonProperty("total_pages")
        Integer totalPages,
        @JsonProperty("per_page")
        String perPage
) {
}
