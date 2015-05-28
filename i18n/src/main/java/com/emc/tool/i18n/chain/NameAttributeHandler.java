package com.emc.tool.i18n.chain;


public class NameAttributeHandler extends BaseHandler implements IAttributeHandler {

	public static final String ATTRIBUTE_NAME = "name";

	public NameAttributeHandler() {
		super(ATTRIBUTE_NAME);
	}

	@Override
	public boolean invoke(Request request, Response response) {

		System.out.println("NameAttributeHandler.invoke()");

		return handle(request, response);
	}

}
