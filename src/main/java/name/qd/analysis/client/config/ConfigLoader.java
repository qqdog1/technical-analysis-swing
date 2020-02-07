package name.qd.analysis.client.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {
	private Logger log = LoggerFactory.getLogger(ConfigLoader.class);
	public static ConfigLoader instance = new ConfigLoader();
	
	private String cache_path;
	private String twse_data_path;
	private boolean isWriteCacheToFile;
	
	private ConfigLoader() {
		loadConfig();
	}
	
	public static ConfigLoader getInstance() {
		return instance;
	}
	
	private void loadConfig() {
		try {
			Properties properties = new Properties();
			FileInputStream fIn = new FileInputStream("./config/config");
			properties.load(fIn);
			// 
			readCachePath(properties);
			readTWSEFilePath(properties);
			readIsWriteCacheToFile(properties);
			
			fIn.close();
		} catch (FileNotFoundException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
	}
	
	private void readCachePath(Properties properties) {
		cache_path = properties.getProperty("file_cache_path");
	}
	
	private void readTWSEFilePath(Properties properties) {
		twse_data_path = properties.getProperty("twse_data_path");
	}
	
	private void readIsWriteCacheToFile(Properties properties) {
		isWriteCacheToFile = Boolean.parseBoolean(properties.getProperty("write_cache_file"));
	}
	
	public String getCachePath() {
		return cache_path;
	}
	
	public String getTWSEFilePath() {
		return twse_data_path;
	}
	
	public boolean isWriteFileToCache() {
		return isWriteCacheToFile;
	}
}
