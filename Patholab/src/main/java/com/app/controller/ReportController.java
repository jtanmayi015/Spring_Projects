package com.app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ReportDto;
import com.app.dto.ViewReportDto;
import com.app.service.ReportService;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins="http://localhost:3000")
public class ReportController {
	@Autowired
    private ReportService reportService;
	
	 @PostMapping("/add")
	    public ResponseEntity<?> addReport(@RequestBody ReportDto reportDto) {
		 try {
		 reportService.addReport(reportDto);
	        return new ResponseEntity<>(new ApiResponse("Report added successfully"), HttpStatus.CREATED);
		 } catch (RuntimeException e) {
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
			}
	    }
	 @PutMapping("/update/{reportId}")
	    public ResponseEntity<?> updateReport(@PathVariable Long reportId, @RequestBody ReportDto reportDto) {
		try {
		 reportService.updateReport(reportId, reportDto);
	        return new ResponseEntity<>(new ApiResponse("Report updated successfully"), HttpStatus.OK);
		} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	    }
	 
	 @GetMapping("/view/{reportId}/{patientId}")
	    public ResponseEntity<?> viewReport(@PathVariable Long reportId,@PathVariable Long patientId) {
		 try {
		 ViewReportDto reportDto = reportService.viewReport(reportId,patientId);
	        return new ResponseEntity<>(reportDto, HttpStatus.OK);
		 } catch (RuntimeException e) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	    }
	 
	 @GetMapping("/all/{patientId}/reports")
	    public ResponseEntity<?> viewAllReport(@PathVariable Long patientId) {
		 try {
	    	Set<ViewReportDto> reports = reportService.viewAllReports(patientId);
	        return ResponseEntity.ok(reports);
		 } catch (RuntimeException e) {
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
			}
	    }         
	 
	 @DeleteMapping("/{reportId}")
	    public ResponseEntity<?> removeReport(@PathVariable Long reportId) {
		 try {
	        reportService.removeReport(reportId);
	        return ResponseEntity.ok(new ApiResponse("Report removed successfully"));
		 } catch (RuntimeException e) {
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
			}
	    }
	
}
