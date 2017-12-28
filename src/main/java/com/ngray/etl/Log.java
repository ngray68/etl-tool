package com.ngray.etl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple utility wrapper around the standard java logger
 * @author nigelgray
 *
 */
public final class Log {

	private static final Logger logger = LogManager.getRootLogger();
	
	public static Logger getLogger() {
		return logger;	
	}	
}