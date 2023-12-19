package com.app.service;

import java.util.Set;

import com.app.dto.ReportDto;
import com.app.dto.ViewReportDto;

public interface ReportService {

	void addReport(ReportDto reportDto);

	void updateReport(Long reportId, ReportDto reportDto);

	ViewReportDto viewReport(Long reportId,Long patientId);

	void removeReport(Long reportId);

	Set<ViewReportDto> viewAllReports(Long patientId);

	

}
