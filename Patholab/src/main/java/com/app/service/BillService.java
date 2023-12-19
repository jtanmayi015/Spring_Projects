package com.app.service;

import javax.validation.constraints.NotNull;

import com.app.dto.BillDto;
import com.app.dto.TotalBillDto;
import com.app.entities.Bill;

public interface BillService {

	void addBill(BillDto billDTO);

	void updateBill(Long billId, BillDto billDto);

	BillDto viewBill(Long billId);

	void removeBill(Long billId);

	TotalBillDto viewTotalBill(Long patientId);

	

	

}
