package ufmc;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class CDUSettings {

	public Integer CDUTop;
	public Integer CDULeft;
	public Integer CDUHeight;
	public Integer CDUWidth;
	public String IOCPServerIP;
	public Integer IOCPServerPort;

	private Properties properties;
	private String iniFileName = "src\\FSCDU.ini"; //default iniFileName

	public CDUSettings() {
		properties = new Properties();
		try {
			readInifile();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readInifile() {
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

	public String getIniFileName() {
		return iniFileName;
	}

	public void setIniFileName(String iniFileName) {
		this.iniFileName = iniFileName;
	}

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

}
