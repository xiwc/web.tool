package org.xiwc.tool.web;

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

import com.emc.tool.i18n.chain.I18nFactory;

@Controller
@RequestMapping("i18n")
public class I18nController {

	static Logger logger = LoggerFactory.getLogger(I18nController.class);

	@Autowired
	Environment env;

	@RequestMapping(value = "replace", method = RequestMethod.POST)
	@ResponseBody
	RespBody replace(@RequestParam(value = "input", required = true) String input,
			@RequestParam(value = "ns", required = true, defaultValue = "ui") String ns) {

		return RespBody.succeed(I18nFactory.getInstance().produce(input, ns));
	}
}
