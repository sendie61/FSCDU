package UnitTests;


import static org.junit.Assert.*;

import org.junit.Test;

import ufmc.CDUSettings;

public class CDUSettingsTest {
	public CDUSettings settings;
	
	@Test
	public void testGetIniFileName() {
		CDUSettings ini = new CDUSettings();
		String result= ini.getIniFileName();
		// should return default iniName "src/FSCDU.ini"
		assertEquals("iniFileName NOT src/FSCDU.ini", "src/FSCDU.ini", result);
	}
	
	@Test
	public void testGetIniFileName2() {
		CDUSettings ini = new CDUSettings("abc.def");
		String result= ini.getIniFileName();
		// should return default iniName "src/FSCDU.ini"
		assertEquals("iniFileName NOT 'abc.def'", "abc.def", result);
	}
	
	@Test
	public void testSetAndGetIniFileName() {
		CDUSettings ini = new CDUSettings();
		String name1= "Filename1.ini";
		ini.setIniFileName(name1);
		String result= ini.getIniFileName();
		assertEquals("iniFileName NOT equal", name1, result);
	}
	
	@Test
	public void testCDUSettings() {
		CDUSettings ini = new CDUSettings();
		
		fail("Not yet implemented");
	}

	@Test
	public void testReadInifile() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteInifile() {
		fail("Not yet implemented");
	}

}
