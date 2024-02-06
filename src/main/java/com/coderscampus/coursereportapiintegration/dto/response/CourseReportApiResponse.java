package com.coderscampus.coursereportapiintegration.dto.response;

import com.coderscampus.coursereportapiintegration.dto.MatchDto;
import com.coderscampus.coursereportapiintegration.dto.MetaDataDto;

import java.util.List;

public record CourseReportApiResponse(
        List<MatchDto> matches,
        MetaDataDto meta
) {

}
