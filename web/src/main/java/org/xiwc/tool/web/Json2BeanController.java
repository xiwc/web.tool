package org.xiwc.tool.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiwc.tool.model.RespBody;
import org.xiwc.tool.util.WebUtil;
import org.xiwc.tool.util.ZipUtil;

import com.emc.vsi.json2Bean.Config;
import com.emc.vsi.json2Bean.ILogger;
import com.emc.vsi.json2Bean.MainHandler;
import com.emc.vsi.json2Bean.Utils;

@Controller
@RequestMapping("json2bean")
public class Json2BeanController {

	static Logger logger = LoggerFactory.getLogger(Json2BeanController.class);

	@Autowired
	Environment env;

	@RequestMapping(value = "convert", method = RequestMethod.POST)
	@ResponseBody
	RespBody convert(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "json", required = true) String json,
			@RequestParam(value = "cmmt", required = true, defaultValue = "true") Boolean cmmt) {

		Config config = new Config();
		config.setAddValueAsComment(cmmt);
		config.setDate(Utils.formatDate(new Date()));

		List<String> logs = new ArrayList<String>();

		try {
			MainHandler.handle(json, config, new ILogger() {

				public void info(String msg, Object... params) {
					logger.info(String.format(msg, params));
					logs.add("INFO:  " + String.format(msg, params));
				}

				public void error(String msg, Object... params) {
					logger.error(String.format(msg, params));
					logs.add("ERROR: " + String.format(msg, params));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			RespBody.failed(e.getMessage());
		}

		String realPath = WebUtil.getRealPath(request);

		String zipFileName = String.format("json2bean_%s.zip", new Date().getTime());

		try {
			ZipUtil.zip(new File(MainHandler.FILE_PATH), new File(realPath, zipFileName));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			RespBody.failed(e.getMessage());
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logs", org.xiwc.tool.util.StringUtil.join("\r\n", logs));
		map.put("zipUrl", zipFileName);

		return RespBody.succeed(map);
	}
}
