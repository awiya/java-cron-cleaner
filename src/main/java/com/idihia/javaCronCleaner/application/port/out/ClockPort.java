package com.idihia.javaCronCleaner.application.port.out;

import java.time.Instant;

public interface ClockPort {

	Instant now();

}
