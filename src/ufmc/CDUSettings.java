package ufmc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author frits.senders
 *
 */
public class CDUSettings {

	public Integer CDUTop=10;
	public Integer CDULeft=10;
	public Integer CDUHeight=400;
	public Integer CDUWidth=600;
	public String  IOCPServerIP="127.0.0.1";
	public Integer IOCPServerPort=8000;
	private Properties properties;
	private String iniFileName = "";
	
	String defaultFileName = "default.ini"; //default iniFileName

	/* 
	 * default constructor 
	 */
	public CDUSettings() {
		init(defaultFileName);
	}
	
	/* 
	 * constructor specify ini-file name
	 */
	public CDUSettings( String fileName) {
		init(fileName);
	}

	/* 
	 * initialization 
	 */
	private void init( String fileName) {
		properties = new Properties();
		setIniFileName(fileName);
	}
	
	/* 
	 * return from which ini-file we are reading/writing
	 */
	public String getIniFileName() {
		return iniFileName;
	}

	/* 
	 * set new ini-file, create it if necessary
	 * AND read it!
	 */
	public void setIniFileName(String iniFileName) {
		this.iniFileName = iniFileName;
		try {
			readInifile();	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* 
	 * read the ini-file, create it if necessary
	 * AND read it!
	 */
	public void readInifile() {
		// if file does not extst, create it
		if (!iniFileExists(iniFileName)){
			createFile(iniFileName);
			writeInifile();
		}
		try {
			properties.load(new FileInputStream(iniFileName));
			CDUTop = Integer.parseInt(properties.getProperty("CDUTop"));
			CDULeft = Integer.parseInt(properties.getProperty("CDULeft"));
			CDUHeight = Integer.parseInt(properties.getProperty("CDUHeight"));
			CDUWidth = Integer.parseInt(properties.getProperty("CDUWidth"));
			IOCPServerIP = properties.getProperty("IOCPServerIP");
			IOCPServerPort = Integer.parseInt(properties.getProperty("IOCPServerPort"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* 
	 * write proprerties to the ini-file 
	 */
	public void writeInifile() {
		properties.put("CDUTop", CDUTop.toString());
		properties.put("CDULeft", CDULeft.toString());
		properties.put("CDUHeight", CDUHeight.toString());
		properties.put("CDUWidth", CDUWidth.toString());
		properties.put("IOCPServerIP", IOCPServerIP);
		properties.put("IOCPServerPort", IOCPServerPort.toString());
		try {
			properties.store(new FileOutputStream(iniFileName), "/* properties updated */");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private boolean iniFileExists(String fileName){
		File f = new File(fileName); 
		return f.isFile();
	}

	private void createFile(String fileName){
		File f = new File(fileName); 
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
