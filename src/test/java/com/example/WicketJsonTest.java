package com.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.ajax.json.JSONString;
import org.junit.Test;

public class WicketJsonTest
{
	@Test
	public void testBeanToString()
	{
		MyPojo1 myPojo = new MyPojo1();
		String json = new JSONObject(myPojo).toString();

		assertEquals(MyPojo1.EXPECTED, json);
	}

	@Test
	public void testBeanJSONString()
	{
		MyPojo2 myPojo = new MyPojo2();
		String json = new JSONObject(myPojo).toString();

		assertEquals(MyPojo2.EXPECTED, json);
	}

	@Test
	public void testArrayJSONString()
	{
		List<Object> myArray = Arrays.asList(new MyPojo1(), new MyPojo2());

		final String expected = String.format("[%s,%s]", MyPojo1.EXPECTED, MyPojo2.EXPECTED);
		final String json = new JSONArray(myArray).toString();

		assertEquals(expected, json);
	}

	public static class MyPojo1
	{
		static final String EXPECTED = "{\"myProp1\":\"value1\",\"myProp2\":\"value2\"}";

		private String myProp1 = "value1";
		private String myProp2 = "value2";

		public String getMyProp1()
		{
			return this.myProp1;
		}

		public String getMyProp2()
		{
			return this.myProp2;
		}
	}

	public static class MyPojo2 implements JSONString
	{
		static final String EXPECTED = "{\"myProp3\":\"value3\",\"myProp4\":\"value4\",\"myProp5\":\"value5\"}";

		private String myProp3 = "value3";
		private String myProp4 = "value4";

		public String getMyProp3()
		{
			return this.myProp3;
		}

		public String getMyProp4()
		{
			return this.myProp4;
		}

		@Override
		public String toJSONString()
		{
			JSONObject object = new JSONObject(this);
			object.put("myProp5", "value5");

			return object.toString();
		}
	}
}
