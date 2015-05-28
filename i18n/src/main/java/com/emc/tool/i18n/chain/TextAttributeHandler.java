package com.emc.tool.i18n.chain;


public class TextAttributeHandler extends BaseHandler implements IAttributeHandler {

	public static final String ATTRIBUTE_NAME = "text";

	public TextAttributeHandler() {
		super(ATTRIBUTE_NAME);
	}

	@Override
	public boolean invoke(Request request, Response response) {

		System.out.println("TextAttributeHandler.invoke()");

		return handle(request, response);
	}

}
