package com.idihia.javaCronCleaner.adapter.out;

import com.idihia.javaCronCleaner.application.port.out.FileSystemPort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FileSystemAdapter implements FileSystemPort {

	@Override
	public List<File> listFiles(String folderPath) {
		return Optional.of(new File(folderPath))
			.filter(File::exists)
			.filter(File::isDirectory)
			.map(File::listFiles)
			.map(Arrays::asList)
			.orElse(List.of());
	}

	@Override
	public boolean deleteFile(File file) {
		return file.delete();
	}

	@Override
	public Instant getLastModifiedTime(File file) {
		try {
			return Files.getLastModifiedTime(file.toPath()).toInstant();
		}
		catch (Exception e) {
			throw new RuntimeException("Impossible de lire la date de dernière modif de " + file.getAbsolutePath(), e);
		}
	}

}
