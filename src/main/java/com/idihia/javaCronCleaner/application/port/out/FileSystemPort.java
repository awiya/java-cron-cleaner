package com.idihia.javaCronCleaner.application.port.out;

import java.io.File;
import java.time.Instant;
import java.util.List;

public interface FileSystemPort {

	/**
	 * @param folderPath chemin du dossier à scanner
	 * @return liste de fichiers (File) contenus dans ce dossier.
	 */
	List<File> listFiles(String folderPath);

	/**
	 * Supprime le fichier spécifié.
	 * @param file le File à supprimer
	 * @return true si la suppression a réussi, false sinon
	 */
	boolean deleteFile(File file);

	/**
	 * @param file le fichier dont on veut connaître la date de dernière modification
	 * @return Instant de la dernière modification
	 */
	Instant getLastModifiedTime(File file);

}
