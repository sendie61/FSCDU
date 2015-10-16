package UnitTests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import ufmc.CDUSettings;

public class CDUSettingsTest {
	public CDUSettings settings;

	@Test
	// check default constructor + getIniFilName()
	public void testGetDefaultIniFileName() {
		CDUSettings ini = new CDUSettings();
		String result = ini.getIniFileName();
		// should return default iniName "src/FSCDU.ini"
		File file = new File("default.ini");
		assertTrue("File 'default.ini' not created.", file.isFile());
		file.delete();
		assertEquals("iniFileName NOT 'default.ini'", "default.ini", result);
	}

	@Test
	// check filename in constructor + default properties
	public void testGetIniFileName() {
		CDUSettings ini = new CDUSettings("abc.def");
		String result = ini.getIniFileName();
		// should return default iniName "abc.def"
		File file = new File("abc.def");
		assertTrue("File 'abc.def' not created.", file.isFile());
		file.delete();
		assertEquals("iniFileName NOT 'abc.def'", "abc.def", result);
		assertEquals((long) 10, (long) ini.CDUTop);
		assertEquals((long) 10, (long) ini.CDULeft);
		assertEquals((long) 400, (long) ini.CDUHeight);
		assertEquals((long) 600, (long) ini.CDUWidth);
		assertEquals( "127.0.0.1", ini.IOCPServerIP);
		assertEquals((long) 8000, (long) ini.IOCPServerPort);
	}

	@Test
	public void testSetIniFileName() {
		CDUSettings ini = new CDUSettings();
		String name1 = "Filename1.ini";
		ini.setIniFileName(name1);
		String result = ini.getIniFileName();
		File file = new File(name1);
		assertTrue("File '" + name1 + "' not created.", file.isFile());
		file.delete();
	    file = new File("default.ini");
		assertTrue("File 'default.ini' not created.", file.isFile());
		file.delete();
		assertEquals("iniFileName NOT equal", name1, result);
		assertEquals((long) 10, (long) ini.CDUTop);
		assertEquals((long) 10, (long) ini.CDULeft);
		assertEquals((long) 400, (long) ini.CDUHeight);
		assertEquals((long) 600, (long) ini.CDUWidth);
		assertEquals( "127.0.0.1", ini.IOCPServerIP);
		assertEquals((long) 8000, (long) ini.IOCPServerPort);
	}

	@Test
	// read the test inifile 'test.ini'
	public void testReadInifile() {
		CDUSettings ini = new CDUSettings("src/UnitTests/test.ini");
		assertEquals((long) 1, (long) ini.CDUHeight);
		assertEquals((long) 2, (long) ini.CDUTop);
		assertEquals((long) 3, (long) ini.CDUWidth);
		assertEquals((long) 4, (long) ini.CDULeft);
		assertEquals((long) 5, (long) ini.IOCPServerPort);
		assertEquals( "localhost", ini.IOCPServerIP);	
	}

}
