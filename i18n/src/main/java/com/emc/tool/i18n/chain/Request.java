package com.emc.tool.i18n.chain;

import org.dom4j.Document;

public class Request {
	
	private Document document;
	private String namespace;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
