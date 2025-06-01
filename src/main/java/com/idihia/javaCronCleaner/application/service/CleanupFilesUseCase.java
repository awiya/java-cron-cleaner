package com.idihia.javaCronCleaner.application.service;

import com.idihia.javaCronCleaner.application.port.in.SchedulingPort;
import com.idihia.javaCronCleaner.application.port.out.ClockPort;
import com.idihia.javaCronCleaner.application.port.out.FileSystemPort;
import com.idihia.javaCronCleaner.domain.FileCleanupService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CleanupFilesUseCase implements SchedulingPort {

	private final FileCleanupService domainService;

	public CleanupFilesUseCase(FileSystemPort fsPort, ClockPort clockPort,
			@Value("${croncleaner.target.folder}") String targetFolder,
			@Value("${croncleaner.retention.days}") long retentionDays) {
		this.domainService = new FileCleanupService(fsPort, clockPort, targetFolder, retentionDays);
	}

	@Override
	public void triggerCleanup() {
		domainService.executeCleanup();
	}

}
