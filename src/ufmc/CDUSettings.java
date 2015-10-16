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

	private Properties p;
	private String Inifilename = "src\\FSCDU.ini";

	public CDUSettings() {
		p = new Properties();
		try {
			readInifile();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readInifile() {
		try {
			p.load(new FileInputStream(Inifilename));
			CDUTop = Integer.parseInt(p.getProperty("CDUTop"));
			CDULeft = Integer.parseInt(p.getProperty("CDULeft"));
			CDUHeight = Integer.parseInt(p.getProperty("CDUHeight"));
			CDUWidth = Integer.parseInt(p.getProperty("CDUWidth"));
			IOCPServerIP = p.getProperty("IOCPServerIP");
			IOCPServerPort = Integer.parseInt(p.getProperty("IOCPServerPort"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void writeInifile() {
		p.put("CDUTop", CDUTop.toString());
		p.put("CDULeft", CDULeft.toString());
		p.put("CDUHeight", CDUHeight.toString());
		p.put("CDUWidth", CDUWidth.toString());
		p.put("IOCPServerIP", IOCPServerIP);
		p.put("IOCPServerPort", IOCPServerPort.toString());
		try {
			p.store(new FileOutputStream(Inifilename), "/* properties updated */");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
