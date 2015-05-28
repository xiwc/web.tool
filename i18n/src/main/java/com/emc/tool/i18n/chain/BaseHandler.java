package com.emc.tool.i18n.chain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;

public abstract class BaseHandler
{

	protected String attrName;

	public BaseHandler(String attrName)
	{
		super();
		this.attrName = attrName;
	}

	protected boolean handle(Request request, Response response)
	{

		if (StringUtils.isEmpty(attrName))
		{
			System.out.println("Attribute name has not been setted!");
			return false;
		}

		Document doc = request.getDocument();

		List<?> selectNodes2 = doc.selectNodes("//@" + attrName);

		// need total size.
		System.out.println(String.format("Size:%s", selectNodes2.size()));

		for (Object object : selectNodes2)
		{

			Attribute attribute = (Attribute) object;
			String pName = attribute.getParent().getName();
			String attrVal = attribute.getValue();

			if (StringUtils.isEmpty(attrVal)
					|| (attrVal.trim().startsWith("{") && attrVal.trim()
							.endsWith("}")))
			{
				continue;
			}

			String id = attrVal.trim().replace("*", "must")
					.replaceAll("[\\W&&[^\\s]]", "").replaceAll("\\s+", ".")
					.toLowerCase();

			int keyCount = Context.getInstance().getKeyCount();

			if (id.split("\\.").length > keyCount)
			{
				System.out.println(String.format(
						"key count is %s, more than default setting value %s",
						id.split("\\.").length, Context.getInstance()
								.getKeyCount()));

				id = StringUtils.join(
						Arrays.copyOf(id.split("\\.", keyCount + 1), keyCount),
						".");
			}

			String key = StringUtils.join(
					new String[] { request.getNamespace(), pName.toLowerCase(),
							attrName.toLowerCase(), id }, ".");

			if (key.endsWith("."))
			{
				key = key + "n" + new Date().getTime();
			}

			if (response.getProps().containsKey(key))
			{
				System.err.println(String.format("Existing Key:%s", key));

				if (!response.getProps().get(key).equals(attrVal))
				{
					key = key + ".n" + new Date().getTime();
					response.getProps().put(key, attrVal);
					// need property.
					System.out.println(String.format("%s=%s", key, attrVal));
				}
				else
				{
					System.out
							.println("The Key Value pair has existing, will be reuse.");
				}
			}
			else
			{
				response.getProps().put(key, attrVal);
				// need property.
				System.out.println(String.format("%s=%s", key, attrVal));
			}

			attribute.setValue(StringUtils.replace("{$('key')}", "key", key));
		}

		return true;
	}
}
