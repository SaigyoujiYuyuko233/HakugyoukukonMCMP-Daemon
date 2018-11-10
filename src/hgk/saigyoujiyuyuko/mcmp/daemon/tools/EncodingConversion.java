package hgk.saigyoujiyuyuko.mcmp.daemon.tools;

import java.io.UnsupportedEncodingException;

public class EncodingConversion {
	
	public String Utf8(String in) throws UnsupportedEncodingException {
		byte[] by = in.getBytes();
		String utf8 = new String(by, "UTF-8");
		
		return utf8;
	}
	
}
