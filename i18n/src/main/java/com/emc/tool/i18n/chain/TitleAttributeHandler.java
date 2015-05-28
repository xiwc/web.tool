package com.emc.tool.i18n.chain;

public class TitleAttributeHandler extends BaseHandler implements IAttributeHandler {

	public static final String ATTRIBUTE_NAME = "title";
	
	public TitleAttributeHandler() {
		super(ATTRIBUTE_NAME);
	}

	@Override
	public boolean invoke(Request request, Response response) {

		System.out.println("TitleAttributeHandler.invoke()");

		return handle(request, response);
	}

}
