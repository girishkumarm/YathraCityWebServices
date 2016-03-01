package com.yathraCity.services.utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {

	public String toJSON( Object o )
	{
		return new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(o);
	}

	public <T> Object parse( String str, Class<T> clazz )
	{
		return new Gson().fromJson(str, clazz);
	}

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static String stringify( Object o )
	{
		return gson.toJson(o);
	}

	public static <T> T parse( String jsonString, Type type )
	{
		return gson.fromJson(jsonString, type);
	}

	public static void print( Object o )
	{
		System.out.println(stringify(o));
	}

	public static Map<String, Object> findKeyValsOfJSON( String json )
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if( json == null || json.trim().isEmpty() )
		{
			return map;
		}
		try
		{
			org.codehaus.jackson.JsonFactory factory = new JsonFactory();
			org.codehaus.jackson.map.ObjectMapper mapper = new ObjectMapper(factory);
			org.codehaus.jackson.JsonNode rootNode = mapper.readTree(json);
			if( rootNode.isObject() )
			{
				map.putAll(convertJSONToKeyVal(rootNode));
			}
			else if( rootNode.isArray() )
			{
				Iterator<JsonNode> fieldsIterator = rootNode.getElements();
				while( fieldsIterator.hasNext() )
				{
					JsonNode node = fieldsIterator.next();
					if( node.isObject() )
					{
						map.putAll(convertJSONToKeyVal(node));
					}
					else
					{
						System.out.println("non object");
						// ignore array elements of types other than
						// objects. i.e, int, boolean, etc.
					}
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return map;
	}

	private static Map<String, Object> convertJSONToKeyVal( JsonNode rootNode )
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.getFields();
			while( fieldsIterator.hasNext() )
			{
				Map.Entry<String, JsonNode> field = fieldsIterator.next();
				if( field.getValue().isObject() )
				{
					map.putAll(convertJSONToKeyVal(field.getValue()));
				}
				else if( field.getValue().isArray() )
				{
					for( JsonNode n : field.getValue() )
					{
						if( n.isObject() )
						{
							map.putAll(convertJSONToKeyVal(n));
						}
						else
						{
							// ignore array elements of types other than
							// objects. i.e, int, boolean, etc.
						}
					}
				}
				else
				{
					if( field.getValue().isBoolean() )
					{
						map.put(field.getKey(), field.getValue().asBoolean());
					}
					else if( field.getValue().isBigDecimal() || field.getValue().isDouble() )
					{
						map.put(field.getKey(), field.getValue().asDouble());
					}
					else if( field.getValue().isInt() || field.getValue().isDouble() )
					{
						map.put(field.getKey(), field.getValue().asInt());
					}
					else if( field.getValue().isInt() || field.getValue().isTextual() )
					{
						map.put(field.getKey(), field.getValue().asText());
					}
					else
					{
						map.put(field.getKey(), field.getValue());
					}
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return map;
	}
}
