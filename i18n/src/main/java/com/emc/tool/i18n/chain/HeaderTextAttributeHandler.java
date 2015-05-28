package com.emc.tool.i18n.chain;

public class HeaderTextAttributeHandler extends BaseHandler implements IAttributeHandler {

	public static final String ATTRIBUTE_NAME = "headerText";

	public HeaderTextAttributeHandler() {
		super(ATTRIBUTE_NAME);
	}

	@Override
	public boolean invoke(Request request, Response response) {

		System.out.println("HeaderTextAttributeHandler.invoke()");

		return handle(request, response);
	}

}
