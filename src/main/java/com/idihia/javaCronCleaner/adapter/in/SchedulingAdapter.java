package com.idihia.javaCronCleaner.adapter.in;

import com.idihia.javaCronCleaner.application.port.in.SchedulingPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.SECONDS;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulingAdapter {

	private final SchedulingPort schedulingPort;

	@Scheduled(cron = "${croncleaner.schedule.cron}")
	public void scheduleCleanup() {
		log.info("=== Début du cron de nettoyage à {} ===", now().truncatedTo(SECONDS));
		try {
			schedulingPort.triggerCleanup();
		}
		catch (Exception e) {
			log.error("Une erreur est survenue pendant l’exécution du cron :", e);
		}
		finally {
			log.info("=== Fin du cron de nettoyage à {} ===", now().truncatedTo(SECONDS));
		}
	}

}
