package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.SampleDto;
import com.app.service.SampleService;

@RestController
@RequestMapping("/samples")
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @PostMapping("/add")
    public ResponseEntity<?> addSample(@RequestBody @Validated SampleDto sampleDto) {
    	try {
        sampleService.addSample(sampleDto);
        return new ResponseEntity<>(new ApiResponse("Sample added successfully"), HttpStatus.CREATED);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }

    @PutMapping("/update/{sampleId}")
    public ResponseEntity<?> updateSample(@PathVariable Long sampleId, @RequestBody @Validated SampleDto sampleDto) {
        try {
    	sampleService.updateSample(sampleId, sampleDto);
        return new ResponseEntity<>(new ApiResponse("Sample updated successfully"), HttpStatus.OK);
        } catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }

    @GetMapping("/view/{sampleId}")
    public ResponseEntity<?> viewSample(@PathVariable Long sampleId) {
    	try {
        SampleDto sampleDto = sampleService.viewSample(sampleId);
        return new ResponseEntity<>(sampleDto, HttpStatus.OK);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }

    @DeleteMapping("/{sampleId}")
    public ResponseEntity<?> removeSample(@PathVariable Long sampleId) {
    	try {
        sampleService.removeSample(sampleId);
        return ResponseEntity.ok(new ApiResponse("Sample removed successfully"));
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }


}
