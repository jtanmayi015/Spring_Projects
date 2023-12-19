package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.BillDto;
import com.app.dto.TotalBillDto;
import com.app.service.BillService;
@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
    private BillService billService;

    @PostMapping("/add")
    public ResponseEntity<?> addBill(@RequestBody BillDto billDTO) {
    	try {
        billService.addBill(billDTO);
        return new ResponseEntity<>(new ApiResponse("Bill added successfully"), HttpStatus.CREATED);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
    }

    @PutMapping("/update/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable Long billId, @RequestBody BillDto billDto) {
    	try {
        billService.updateBill(billId, billDto);
        return new ResponseEntity<>(new ApiResponse("Bill updated successfully"), HttpStatus.OK);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }

    @GetMapping("/view/{billId}")
    public ResponseEntity<?> viewBill(@PathVariable Long billId) {
    	try {
        BillDto billDTO = billService.viewBill(billId);
        return new ResponseEntity<>(billDTO, HttpStatus.OK);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
    @DeleteMapping("/{billId}")
    public ResponseEntity<?> removeBill(@PathVariable Long billId) {
    	try {
        billService.removeBill(billId);
        return ResponseEntity.ok(new ApiResponse("Bill removed successfully"));
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
    
    @GetMapping("/complete-bill/{patientId}")
    public ResponseEntity<?> getTotalBillForPatient(@PathVariable Long patientId) {
    	try {
        TotalBillDto totalBillDto = billService.viewTotalBill(patientId);
        return ResponseEntity.ok(totalBillDto);
    	} catch (RuntimeException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
    }
}
