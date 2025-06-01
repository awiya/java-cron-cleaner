package com.idihia.javaCronCleaner.application.port.in;

public interface SchedulingPort {

	/**
	 * Méthode appelée à chaque exécution planifiée.
	 */
	void triggerCleanup();

}
