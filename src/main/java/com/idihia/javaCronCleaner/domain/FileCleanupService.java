package com.idihia.javaCronCleaner.domain;

import com.idihia.javaCronCleaner.application.port.out.ClockPort;
import com.idihia.javaCronCleaner.application.port.out.FileSystemPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.Instant;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@AllArgsConstructor
public class FileCleanupService {

	private final FileSystemPort fileSystem;

	private final ClockPort clock;

	private final String targetFolder;

	private final long retentionDays;


	public void executeCleanup() {
		log.info("Début du nettoyage : dossier cible = {}", targetFolder);

		List<File> files = fileSystem.listFiles(targetFolder);
		if (files == null || files.isEmpty()) {
			log.info("Aucun fichier à traiter dans {}", targetFolder);
			return;
		}

		Instant seuil = clock.now().minus(retentionDays, DAYS);

		for (File f : files) {
			try {
				Instant lastModified = fileSystem.getLastModifiedTime(f);
				if (lastModified.isBefore(seuil)) {
					boolean deleted = fileSystem.deleteFile(f);
					if (deleted) {
						log.info("Supprimé : {} (dernière modif : {})", f.getAbsolutePath(), lastModified);
					}
					else {
						log.error("Impossible de supprimer : {}", f.getAbsolutePath());
					}
				}
			}
			catch (Exception ex) {
				log.error("Erreur sur fichier {} : {}", f.getAbsolutePath(), ex.getMessage());
			}
		}

		log.info("Fin du nettoyage pour le dossier {}", targetFolder);
	}

}
