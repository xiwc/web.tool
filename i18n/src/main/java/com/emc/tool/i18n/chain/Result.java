package com.emc.tool.i18n.chain;

import java.util.HashMap;
import java.util.Map;

public class Result {

	private String xml;
	private Map<String, String> props = new HashMap<String, String>();

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public Map<String, String> getProps() {
		return props;
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}

}
