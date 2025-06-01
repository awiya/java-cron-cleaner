package com.idihia.javaCronCleaner.adapter.out;

import com.idihia.javaCronCleaner.application.port.out.ClockPort;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SystemClockAdapter implements ClockPort {

	@Override
	public Instant now() {
		return Instant.now();
	}

}
