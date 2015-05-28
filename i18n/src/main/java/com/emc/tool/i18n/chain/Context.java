package com.emc.tool.i18n.chain;

public class Context
{
	private static Context INSTANE_CONTEXT;

	protected int keyCount = 5;

	protected Context()
	{
	}

	public static Context getInstance()
	{

		if (INSTANE_CONTEXT == null)
		{
			INSTANE_CONTEXT = new Context();
		}

		return INSTANE_CONTEXT;
	}

	public int getKeyCount()
	{
		return keyCount;
	}

	public void setKeyCount(int keyCount)
	{
		this.keyCount = keyCount;
	}

}
