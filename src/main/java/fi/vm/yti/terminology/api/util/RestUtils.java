package fi.vm.yti.terminology.api.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RestUtils {

	public static String readContent(InputStream is) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();	
		return content.toString();
	}
}
