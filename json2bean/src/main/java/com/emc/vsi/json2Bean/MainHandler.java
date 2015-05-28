package com.emc.vsi.json2Bean;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MainHandler {

	public static final String FILE_PATH = "C:\\json-2-bean\\";
	private static ILogger logger;
	private static Map<String, Object> objMap = new HashMap<String, Object>();
	private static final String VAL_OBJ = null;
	private static Config config = new Config();

	public static void handle(String json, Config config, ILogger logger) throws Exception {

		MainHandler.logger = logger;
		MainHandler.config = config;

		logger.info("delete then force mkdir [%s].", FILE_PATH);

		FileUtils.deleteDirectory(new File(FILE_PATH));
		FileUtils.forceMkdir(new File(FILE_PATH));

		objMap.clear();

		logger.info(">>>start parse json object");

		parseJSONObject(null, JSON.parseObject(String.format("{\"root\": %s}", json)));

		logger.info("<<<end parse json object");
	}

	private static boolean isPrimitive(Object obj) {

		String name = obj.getClass().getName();

		String[] names = { "java.lang.Boolean", "java.lang.Character", "java.lang.Byte", "java.lang.Short",
				"java.lang.Integer", "java.lang.Long", "java.lang.Float", "java.lang.Double", "java.lang.Void",
				"java.lang.String", "java.math.BigDecimal" };

		return ArrayUtils.contains(names, name);
	}

	private static void parseJSONObject(String name, JSONObject jsonObj) throws IOException {

		BeanInfo beanInfo = new BeanInfo();

		if (!StringUtils.isEmpty(name)) {
			beanInfo.setClsName(StringUtils.capitalize(name));
		}

		Map<String, Object> map = new HashMap<String, Object>();

		for (String key : jsonObj.keySet()) {

			Object val = jsonObj.get(key);

			if (val instanceof String || val == null) {
				logger.info("private String %s;", key);

				beanInfo.getFields().add(new Field(key, String.class.getSimpleName(), String.valueOf(val)));
			} else if (val instanceof Boolean) {
				logger.info("private Boolean %s;", key);

				beanInfo.getFields().add(new Field(key, Boolean.class.getSimpleName(), String.valueOf(val)));
			} else if (val instanceof Integer) {
				logger.info("private Integer %s;", key);

				beanInfo.getFields().add(new Field(key, Integer.class.getSimpleName(), String.valueOf(val)));
			} else if (val instanceof Long) {
				logger.info("private Long %s;", key);

				beanInfo.getFields().add(new Field(key, Long.class.getSimpleName(), String.valueOf(val)));
			} else if (val instanceof Short) {
				logger.info("private Short %s;", key);

				beanInfo.getFields().add(new Field(key, Short.class.getSimpleName(), String.valueOf(val)));
			} else if (val instanceof Character) {
				logger.info("private Character %s;", key);

				beanInfo.getFields().add(new Field(key, Character.class.getSimpleName(), String.valueOf(val)));
			} else if (val instanceof JSONObject) {

				String newK = key;

				if (objMap.containsKey(key)) {

					if (StringUtils.isEmpty(name)) {
						newK = key + new Date().getTime();
					} else {
						newK = name + StringUtils.capitalize(key);
					}
				}

				objMap.put(newK, null);

				logger.info("private %s %s;", StringUtils.capitalize(newK), newK);

				beanInfo.getFields().add(new Field(key, StringUtils.capitalize(newK), VAL_OBJ));

				map.put(newK, val);
			} else if (val instanceof JSONArray) {

				JSONArray jsonArr = (JSONArray) val;

				if (jsonArr.size() > 0 && isPrimitive(jsonArr.get(0))) {

					logger.info("private List<%s> %s;", jsonArr.get(0).getClass().getSimpleName(), key);

					beanInfo.getFields().add(
							new Field(key, String.format("List<%s>", jsonArr.get(0).getClass().getSimpleName(),
									String.valueOf(jsonArr.get(0)))));
				} else {

					String clsName = key;

					if (key.endsWith("ies")) {
						clsName = key.substring(0, key.length() - 3) + "y";
					} else if (key.endsWith("s")) {
						clsName = key.substring(0, key.length() - 1);
					}

					if (objMap.containsKey(clsName)) {

						if (StringUtils.isEmpty(name)) {
							clsName = clsName + new Date().getTime();
						} else {
							clsName = name + StringUtils.capitalize(clsName);
						}
					}

					objMap.put(clsName, null);

					logger.info("private List<%s> %s;", StringUtils.capitalize(clsName), key);

					beanInfo.getFields().add(
							new Field(key, String.format("List<%s>", StringUtils.capitalize(clsName), VAL_OBJ)));

					if (jsonArr.size() > 0) {
						map.put(clsName, jsonArr.getJSONObject(0));
					} else {
						map.put(clsName, new JSONObject());
					}
				}
			} else {
				logger.error("Key: %s, Value: %s, Unrecognized Type: %s", key, String.valueOf(val), val.getClass()
						.getName());
			}
		}

		createJavaFile(beanInfo);

		for (String key : map.keySet()) {

			Object object = map.get(key);

			if (object instanceof JSONObject) {

				logger.info("JSONObject: [%s] %s", StringUtils.capitalize(key), key);

				parseJSONObject(key, (JSONObject) object);
			}
		}
	}

	private static void createJavaFile(BeanInfo beanInfo) {

		if (beanInfo == null || beanInfo.getClsName() == null) {
			return;
		}

		try {
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
			configuration.setClassicCompatible(true);
			configuration.setClassForTemplateLoading(MainHandler.class, "");
			configuration.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			configuration.setNumberFormat("");
			configuration.setDefaultEncoding("utf-8");
			Template template = configuration.getTemplate("Bean.ftl");

			StringWriter sw = new StringWriter();

			Map<String, Object> dataModelMap = new HashMap<String, Object>();
			dataModelMap.put("m", beanInfo);
			dataModelMap.put("c", config);

			template.process(dataModelMap, sw);

			File file = new File(FILE_PATH + beanInfo.getClsName() + ".java");
			FileUtils.write(file, sw.toString(), "utf-8");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
