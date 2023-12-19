package com.app.service;

import com.app.dto.SampleDto;

public interface SampleService {
	 void addSample(SampleDto sampleDto);

	    void updateSample(Long sampleId, SampleDto sampleDto);

	    SampleDto viewSample(Long sampleId);

	    void removeSample(Long sampleId);
}
