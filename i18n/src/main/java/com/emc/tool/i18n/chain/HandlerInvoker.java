package com.emc.tool.i18n.chain;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class HandlerInvoker implements IHanderInvoker {

	private List<IAttributeHandler> attributeHandlers = new ArrayList<IAttributeHandler>();

	@Override
	public Result doInvoke(String xml, String namespace)
			throws DocumentException {

		Result result = new Result();

		Request request = new Request();
		Document doc = new SAXReader().read(new InputSource(new StringReader(
				xml)));
		request.setDocument(doc);
		request.setNamespace(namespace);

		Response response = new Response();

		for (IAttributeHandler attributeHandler : attributeHandlers) {

			if (!attributeHandler.invoke(request, response)) {
				break;
			}
		}

		result.setXml(doc.asXML());
		result.setProps(response.getProps());

		return result;
	}

	@Override
	public boolean addAttributeHandler(IAttributeHandler attributeHandler) {
		return attributeHandlers.add(attributeHandler);
	}

	@Override
	public boolean removeAttributeHandler(IAttributeHandler attributeHandler) {
		return attributeHandlers.remove(attributeHandler);
	}

}
