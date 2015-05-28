package com.emc.tool.i18n;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileUtil {

	public static String readText(String path) throws IOException {

		InputStream resourceAsStream = FileUtil.class.getResourceAsStream(path);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				resourceAsStream, Charset.forName("UTF-8")));

		StringBuffer sb = new StringBuffer();

		String line = br.readLine();

		while (line != null) {
			sb.append(line).append("\n");
			line = br.readLine();
		}

		if (sb.length() > 0) {
			sb.delete(sb.length() - 1, sb.length());
		}

		return sb.toString();
	}
}
