package com.emc.tool.i18n.chain;

import org.dom4j.DocumentException;

public interface IHanderInvoker {

	Result doInvoke(String xml, String namespace) throws DocumentException;

	public boolean addAttributeHandler(IAttributeHandler attributeHandler);

	public boolean removeAttributeHandler(IAttributeHandler attributeHandler);
}
