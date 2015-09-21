import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class CDUSettings {
	public Integer CDUTop;
	public Integer CDULeft;
	public Integer CDUHeight;
	public Integer CDUWidth;
	private Properties p;
	private String Inifilename= "src\\FSCDU.ini";
 
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

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void writeInifile(){
		p.put("CDUTop", CDUTop);
		p.put("CDULeft", CDULeft);
		p.put("CDUHeight", CDUHeight);
		p.put("CDUWidth", CDUWidth);
		try {
		p.save(new FileOutputStream(Inifilename), "/* properties updated */");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
