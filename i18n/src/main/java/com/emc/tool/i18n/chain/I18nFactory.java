package com.emc.tool.i18n.chain;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;
import org.joda.time.DateTime;

import com.emc.tool.i18n.Result;

public class I18nFactory {

	private static I18nFactory instance = new I18nFactory();

	public static final String FILE_PATH = "C:/i18n-tool";

	private IHanderInvoker handerInvoker = new HandlerInvoker();

	{
		// add attribute handler here.
		handerInvoker.addAttributeHandler(new LabelAttributeHandler());
		handerInvoker.addAttributeHandler(new TitleAttributeHandler());
		handerInvoker.addAttributeHandler(new HeaderTextAttributeHandler());
		handerInvoker.addAttributeHandler(new TextAttributeHandler());
		handerInvoker.addAttributeHandler(new NameAttributeHandler());

		// ...
	}

	private I18nFactory() {
	}

	public static I18nFactory getInstance() {
		return instance;
	}

	public Result produce(String xml, String namespace) {

		Result result = new Result();

		try {
			com.emc.tool.i18n.chain.Result result2 = handerInvoker.doInvoke(xml, namespace);

			StringBuffer sb = new StringBuffer();

			String[] array = result2.getProps().keySet().toArray(new String[0]);

			Arrays.sort(array, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});

			for (String key : array) {
				sb.append(String.format("%s=%s", key, result2.getProps().get(key))).append("\n");
			}

			result.setProps(sb.toString());
			result.setPropSize(result2.getProps().size());
			result.setXml(result2.getXml());

			// save hande result to file.
			try {

				String fileName = DateTime.now().toString("yyyy-MM-dd_HHmmss_") + DateTime.now().getMillis() + ".txt";
				FileUtils.forceMkdir(new File(FILE_PATH));
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), "input source:\n\n");
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), xml, true);
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), "\n\noutput source:\n\n", true);
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), result2.getXml(), true);
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), "\n\noutput properties:\n\n", true);
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), sb.toString(), true);
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), "\n\ntotal size:", true);
				FileUtils.write(FileUtils.getFile(new File(FILE_PATH), fileName), result2.getProps().size() + "", true);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return result;
	}
}
