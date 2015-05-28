package org.xiwc.tool.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class RootController {

	static Logger logger = LoggerFactory.getLogger(RootController.class);

	@Autowired
	Environment env;

	@RequestMapping()
	String index(ModelMap modelMap) {

		logger.debug("index page.");

		return "index";
	}

	@RequestMapping("page/i18n")
	String i18nPage(ModelMap modelMap) {

		logger.debug("i18n page.");
		modelMap.put("ns", "ui");
		modelMap.put(
				"input",
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<s:TitleWindow xmlns:fx=\"http://ns.adobe.com/mxml/2009\"\r\n\t\t\t   xmlns:s=\"library://ns.adobe.com/flex/spark\"\r\n\t\t\t   xmlns:mx=\"library://ns.adobe.com/flex/mx\"\r\n\t\t\t   xmlns:views=\"com.emc.vsi.flexui.views.*\"\r\n\t\t\t   width=\"494\" height=\"352\" close=\"titlewindow_closeHandler(event)\"\r\n\t\t\t   title=\"Register EMC Protection System\">\r\n\t<fx:Script>\r\n\t\t<![CDATA[\r\n\t\t\timport com.emc.vsi.flexui.utils.CommonUtil;\r\n\t\t\timport com.vmware.widgets.util.Dialogs;\r\n\t\t\timport com.emc.vsi.flexui.utils.ResourceFileUtil;\r\n\t\t\t\r\n\t\t\timport mx.events.CloseEvent;\r\n\t\t\timport mx.managers.PopUpManager;\r\n\t\t\t\r\n\t\t\tprotected function titlewindow_closeHandler(event:CloseEvent):void\r\n\t\t\t{\r\n\t\t\t\tPopUpManager.removePopUp(this);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprotected function btnTest_clickHandler(event:MouseEvent):void\r\n\t\t\t{\r\n\t\t\t\tmaskView.progressBarLabel = \"Testing...\";\r\n\t\t\t\tmaskView.visible = true;\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprotected function btnOK_clickHandler(event:MouseEvent):void\r\n\t\t\t{\r\n\t\t\t\terrorView.errorMsg = \"error msg...\";\r\n\t\t\t\terrorView.visible = true;\r\n\t\t\t\t\r\n\t\t\t\tvar msg:String = resourceManager.getString(ResourceFileUtil.UI_FILE, 'appSync.msg.register.success', ['10.102.7.96', '8445', 'administrator@PIE.LOCAL']);\r\n\t\t\t\tvar title:String = resourceManager.getString(ResourceFileUtil.UI_FILE, 'appSync.msg.register.dialog.title');\r\n\t\t\t\t\r\n\t\t\t\tDialogs.showAlert(msg, title);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprotected function btnCancel_clickHandler(event:MouseEvent):void\r\n\t\t\t{\r\n\t\t\t\tPopUpManager.removePopUp(this);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprivate function $(key:String):String{\r\n\t\t\t\treturn ResourceFileUtil.getString(ResourceFileUtil.UI_FILE, key);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t]]>\r\n\t</fx:Script>\r\n\t<fx:Declarations>\r\n\t\t<!-- Place non-visual elements (e.g., services, value objects) here -->\r\n\t</fx:Declarations>\r\n\t<s:controlBarContent>\r\n\t\t<s:Button id=\"btnTest\" label=\"Test\" click=\"btnTest_clickHandler(event)\"/>\r\n\t\t<s:Button id=\"btnOK\" label=\"OK\" click=\"btnOK_clickHandler(event)\"/>\r\n\t\t<s:Button id=\"btnCancel\" label=\"Cancel\" click=\"btnCancel_clickHandler(event)\"/>\r\n\t</s:controlBarContent>\r\n\t<s:controlBarLayout>\r\n\t\t<s:HorizontalLayout horizontalAlign=\"right\" paddingBottom=\"10\" paddingRight=\"10\"\r\n\t\t\t\t\t\t\tpaddingTop=\"10\"/>\r\n\t</s:controlBarLayout>\r\n\t\r\n\t<views:ErrorView id=\"errorView\"/>\r\n\t\r\n\t<views:MaskView id=\"maskView\"/>\r\n\t\r\n\t<s:Form width=\"467\" height=\"256\">\r\n\t\t<s:FormItem label=\"Protection System Type:\">\r\n\t\t\t<s:DropDownList id=\"dropDownType\" width=\"230\" selectedIndex=\"0\">\r\n\t\t\t\t<s:dataProvider>\r\n\t\t\t\t\t<s:ArrayList>\r\n\t\t\t\t\t\t<fx:String>AppSync</fx:String>\r\n\t\t\t\t\t</s:ArrayList>\r\n\t\t\t\t</s:dataProvider>\r\n\t\t\t</s:DropDownList>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"425\" label=\"Host name or IP:\">\r\n\t\t\t<s:HGroup verticalAlign=\"middle\">\r\n\t\t\t\t<s:TextInput id=\"txtHost\" text=\"10.102.7.96\"/>\r\n\t\t\t\t<s:Label fontWeight=\"bold\" text=\"Port:\"/>\r\n\t\t\t\t<s:TextInput id=\"txtPort\" width=\"62\" text=\"8445\"/>\r\n\t\t\t</s:HGroup>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"425\" label=\"User name:\">\r\n\t\t\t<s:TextInput id=\"txtUserName\" width=\"229\" text=\"admin\"/>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"425\" label=\"Password:\">\r\n\t\t\t<s:TextInput id=\"txtPassword\" width=\"229\" displayAsPassword=\"true\" text=\"admin\"/>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"427\" label=\"Test Status:\">\r\n\t\t\t<s:HGroup verticalAlign=\"middle\">\r\n\t\t\t\t<s:Image id=\"imgStatusSuccess\"\r\n\t\t\t\t\t\t source=\"@Embed(source='assets/images/statusSuccess.png')\"/>\r\n\t\t\t\t<s:Image id=\"imgStatusFail\" source=\"@Embed(source='assets/images/statusFail.png')\"/>\r\n\t\t\t\t<s:Label id=\"lblTestStatus\" text=\"Connection successful!\"/>\r\n\t\t\t</s:HGroup>\r\n\t\t</s:FormItem>\r\n\t</s:Form>\r\n\t\r\n</s:TitleWindow>");

		return "i18n";
	}

	@RequestMapping("page/json2bean")
	String json2beanPage(ModelMap modelMap) {

		logger.debug("json2bean page.");

		modelMap.put(
				"json",
				"{\"servicePlan\": {\"id\": \"7a696f98-b870-4a73-ad6a-283105e9c45c\", \"planName\": \"eric_bronze_plan\", \"planDisplayName\": \"eric_bronze_plan\", \"description\": \"modified description on one plan\", \"version\": \"2\", \"enabled\": \"true\", \"isBuiltIn\": \"false\", \"planType\": \"Bronze\", \"discoveryPhaseSpec\": {\"enabled\": \"true/false\", \"schedule\": {\"scheduleExpression\": \"*:*:*:*:0:5:0\", \"options\": {\"rpoMonitor\": \"true/false\", \"rpoValueInMinute\": \"1440\"} } }, \"replicationPhaseSpec\": {\"enabled\": \"true/false\", \"options\": {\"numberOfCopiesForExpire\": \"2\", \"appConsistency\": \"VM/CRASH\", \"isIncludeVMDisksForCopy\": \"true/false\", \"maxSimultaneousVmSnapshots\": \"4\"} }, \"mountPhase\": {\"enabled\": \"true\", \"options\": {\"site\": \"local/remote\", \"accessMode\": \"physical/virtual/virtual with roll\", \"clusterMount\": \"true/false\", \"resignature\": \"true/false\", \"vCenterUuid\": \"844BC0C8-2252-49E5-8663-26473257D993\", \"mountHost\": \"10.110.43.145\", \"accessType\": \"read-only/read-write\"} }, \"unmountPreviousCopyPhase\": {\"enabled\": \"true\"}, \"datasets\": [{\"id\": \"e07fb9f4-0c04-4d52-ac8f-f1ce4aab9da8\", \"type\": \"datastore\", \"options\": {\"vCenterServer\": \"dd7096ff-1417-49af-acff-d9eb68312617\", \"datastore\": \"ds:///vmfs/volumes/545aa3db-ffb16c39-38e1-d072dc5a4584/\"}, \"override\": {\"id\": \"e89ddb6a-d905-4c42-8bc1-b963ed359376\", \"enabled\": \"true\", \"name\": \"Mount Copy\", \"options\": {\"vCenterUuid\": \"844BC0C8-2252-49E5-8663-26473257D993\", \"resignature\": \"true\", \"clusterMount\": \"false\", \"accessMode\": \"physical\", \"accessType\": \"read-only\"} }, \"datastore\": {\"id\": \"b7459be9-1852-404d-94fc-bfe35989b937\", \"name\": \"Natasha_CG_CLR_2\", \"datastoreVcenterId\": \"datastore-2162\", \"datastoreUrl\": \"ds:///vmfs/volumes/545aa3db-ffb16c39-38e1-d072dc5a4584/\", \"vcenterName\": \"10.102.7.17\", \"datacenterName\": \"vsi_sh_dev_dc\"} }, {\"id\": \"24936f58-64f6-4dc4-b747-3588660c952d\", \"type\": \"datastore\", \"options\": {\"vCenterServer\": \"dd7096ff-1417-49af-acff-d9eb68312617\", \"datastore\": \"ds:///vmfs/volumes/5451fed2-397602f4-9859-e8b748d5d700/\"}, \"override\": null, \"datastore\": {\"id\": \"9a42a68d-a2e9-4beb-9b7d-bfed2d676a55\", \"name\": \"Natasha_CG_NewRP_2\", \"datastoreVcenterId\": \"datastore-2106\", \"datastoreUrl\": \"ds:///vmfs/volumes/5451fed2-397602f4-9859-e8b748d5d700/\", \"vcenterName\": \"10.102.7.17\", \"datacenterName\": \"vsi_sh_dev_dc\"} } ] } }");

		return "json2bean";
	}
}
