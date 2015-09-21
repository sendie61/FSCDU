import java.io.FileInputStream;
import java.util.Properties;

public class CDUSettings {
	public int CDUTop;
	public int CDULeft;
	public int CDUHeight;
	public int CDUWidth;

	public CDUSettings() {
		readInifile("src\\FSCDU.ini");
	}

	public void readInifile(String filename) {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream(filename));
			CDUTop = Integer.parseInt(p.getProperty("CDUTop"));
			CDULeft = Integer.parseInt(p.getProperty("CDULeft"));
			CDUHeight = Integer.parseInt(p.getProperty("CDUHeight"));
			CDUWidth = Integer.parseInt(p.getProperty("CDUWidth"));

			p.list(System.out);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
