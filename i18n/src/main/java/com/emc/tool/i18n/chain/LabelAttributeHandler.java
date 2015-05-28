package com.emc.tool.i18n.chain;


public class LabelAttributeHandler extends BaseHandler implements IAttributeHandler {

	public static final String ATTRIBUTE_NAME = "label";

	public LabelAttributeHandler() {
		super(ATTRIBUTE_NAME);
	}

	@Override
	public boolean invoke(Request request, Response response) {

		System.out.println("LabelAttributeHandler.invoke()");

		return handle(request, response);
	}

}
