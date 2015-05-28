package com.emc.tool.i18n.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.apache.commons.lang.StringUtils;

import com.emc.tool.i18n.Result;
import com.emc.tool.i18n.chain.I18nFactory;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 7823482680662852932L;
	private JPanel contentPane;
	private JSplitPane splitPane;
	private JTextField txtNs;
	private JTextArea textAreaProperties;
	private JTextArea textAreaSource;
	private JTextArea textAreaReplaceResult;
	private JButton btnReplace;
	private JLabel lblSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		LookAndFeelInfo[] installedLookAndFeels = UIManager
				.getInstalledLookAndFeels();

		for (LookAndFeelInfo lookAndFeelInfo : installedLookAndFeels) {
			// System.out.println(lookAndFeelInfo.getClassName());
		}

		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
			UIManager
					.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("i18n tool - xi.weicheng@emc.com -> backup path: C:/i18n-tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1, BorderLayout.CENTER);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "source",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_1.setLeftComponent(scrollPane_1);

		textAreaSource = new JTextArea();
		textAreaSource
				.setText("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<s:TitleWindow xmlns:fx=\"http://ns.adobe.com/mxml/2009\"\r\n\t\t\t   xmlns:s=\"library://ns.adobe.com/flex/spark\"\r\n\t\t\t   xmlns:mx=\"library://ns.adobe.com/flex/mx\"\r\n\t\t\t   xmlns:views=\"com.emc.vsi.flexui.views.*\"\r\n\t\t\t   width=\"494\" height=\"352\" close=\"titlewindow_closeHandler(event)\"\r\n\t\t\t   title=\"Register EMC Protection System\">\r\n\t<fx:Script>\r\n\t\t<![CDATA[\r\n\t\t\timport com.emc.vsi.flexui.utils.CommonUtil;\r\n\t\t\timport com.vmware.widgets.util.Dialogs;\r\n\t\t\timport com.emc.vsi.flexui.utils.ResourceFileUtil;\r\n\t\t\t\r\n\t\t\timport mx.events.CloseEvent;\r\n\t\t\timport mx.managers.PopUpManager;\r\n\t\t\t\r\n\t\t\tprotected function titlewindow_closeHandler(event:CloseEvent):void\r\n\t\t\t{\r\n\t\t\t\tPopUpManager.removePopUp(this);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprotected function btnTest_clickHandler(event:MouseEvent):void\r\n\t\t\t{\r\n\t\t\t\tmaskView.progressBarLabel = \"Testing...\";\r\n\t\t\t\tmaskView.visible = true;\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprotected function btnOK_clickHandler(event:MouseEvent):void\r\n\t\t\t{\r\n\t\t\t\terrorView.errorMsg = \"error msg...\";\r\n\t\t\t\terrorView.visible = true;\r\n\t\t\t\t\r\n\t\t\t\tvar msg:String = resourceManager.getString(ResourceFileUtil.UI_FILE, 'appSync.msg.register.success', ['10.102.7.96', '8445', 'administrator@PIE.LOCAL']);\r\n\t\t\t\tvar title:String = resourceManager.getString(ResourceFileUtil.UI_FILE, 'appSync.msg.register.dialog.title');\r\n\t\t\t\t\r\n\t\t\t\tDialogs.showAlert(msg, title);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprotected function btnCancel_clickHandler(event:MouseEvent):void\r\n\t\t\t{\r\n\t\t\t\tPopUpManager.removePopUp(this);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tprivate function $(key:String):String{\r\n\t\t\t\treturn ResourceFileUtil.getString(ResourceFileUtil.UI_FILE, key);\r\n\t\t\t}\r\n\t\t\t\r\n\t\t]]>\r\n\t</fx:Script>\r\n\t<fx:Declarations>\r\n\t\t<!-- Place non-visual elements (e.g., services, value objects) here -->\r\n\t</fx:Declarations>\r\n\t<s:controlBarContent>\r\n\t\t<s:Button id=\"btnTest\" label=\"Test\" click=\"btnTest_clickHandler(event)\"/>\r\n\t\t<s:Button id=\"btnOK\" label=\"OK\" click=\"btnOK_clickHandler(event)\"/>\r\n\t\t<s:Button id=\"btnCancel\" label=\"Cancel\" click=\"btnCancel_clickHandler(event)\"/>\r\n\t</s:controlBarContent>\r\n\t<s:controlBarLayout>\r\n\t\t<s:HorizontalLayout horizontalAlign=\"right\" paddingBottom=\"10\" paddingRight=\"10\"\r\n\t\t\t\t\t\t\tpaddingTop=\"10\"/>\r\n\t</s:controlBarLayout>\r\n\t\r\n\t<views:ErrorView id=\"errorView\"/>\r\n\t\r\n\t<views:MaskView id=\"maskView\"/>\r\n\t\r\n\t<s:Form width=\"467\" height=\"256\">\r\n\t\t<s:FormItem label=\"Protection System Type:\">\r\n\t\t\t<s:DropDownList id=\"dropDownType\" width=\"230\" selectedIndex=\"0\">\r\n\t\t\t\t<s:dataProvider>\r\n\t\t\t\t\t<s:ArrayList>\r\n\t\t\t\t\t\t<fx:String>AppSync</fx:String>\r\n\t\t\t\t\t</s:ArrayList>\r\n\t\t\t\t</s:dataProvider>\r\n\t\t\t</s:DropDownList>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"425\" label=\"Host name or IP:\">\r\n\t\t\t<s:HGroup verticalAlign=\"middle\">\r\n\t\t\t\t<s:TextInput id=\"txtHost\" text=\"10.102.7.96\"/>\r\n\t\t\t\t<s:Label fontWeight=\"bold\" text=\"Port:\"/>\r\n\t\t\t\t<s:TextInput id=\"txtPort\" width=\"62\" text=\"8445\"/>\r\n\t\t\t</s:HGroup>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"425\" label=\"User name:\">\r\n\t\t\t<s:TextInput id=\"txtUserName\" width=\"229\" text=\"admin\"/>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"425\" label=\"Password:\">\r\n\t\t\t<s:TextInput id=\"txtPassword\" width=\"229\" displayAsPassword=\"true\" text=\"admin\"/>\r\n\t\t</s:FormItem>\r\n\t\t<s:FormItem width=\"427\" label=\"Test Status:\">\r\n\t\t\t<s:HGroup verticalAlign=\"middle\">\r\n\t\t\t\t<s:Image id=\"imgStatusSuccess\"\r\n\t\t\t\t\t\t source=\"@Embed(source='assets/images/statusSuccess.png')\"/>\r\n\t\t\t\t<s:Image id=\"imgStatusFail\" source=\"@Embed(source='assets/images/statusFail.png')\"/>\r\n\t\t\t\t<s:Label id=\"lblTestStatus\" text=\"Connection successful!\"/>\r\n\t\t\t</s:HGroup>\r\n\t\t</s:FormItem>\r\n\t</s:Form>\r\n\t\r\n</s:TitleWindow>");
		scrollPane_1.setViewportView(textAreaSource);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Repace result",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_1.setRightComponent(scrollPane_2);

		textAreaReplaceResult = new JTextArea();
		scrollPane_2.setViewportView(textAreaReplaceResult);
		splitPane_1.setDividerLocation(250);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("namespace:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 22, 82, 14);
		panel.add(lblNewLabel);

		txtNs = new JTextField();
		txtNs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNs.setText("appsync.ui.register");
		txtNs.setBounds(99, 19, 190, 20);
		panel.add(txtNs);
		txtNs.setColumns(10);

		btnReplace = new JButton("Replace");
		btnReplace.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String xml = textAreaSource.getText();
				String ns = txtNs.getText();

				if (StringUtils.isEmpty(xml) || StringUtils.isEmpty(ns)) {
					return;
				}

				Result result = null;

				try {
					// result = new BasicHandler().replace(xml, ns);
					result = I18nFactory.getInstance().produce(xml, ns);

					textAreaReplaceResult.setText(result.getXml());
					textAreaProperties.setText(result.getProps());
					lblSize.setText(result.getPropSize() + "");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(MainFrame.this,
							e1.getMessage(), "Error message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnReplace.setBounds(127, 59, 116, 38);
		panel.add(btnReplace);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Properties",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 186, 279, 333);
		panel.add(scrollPane);

		textAreaProperties = new JTextArea();
		scrollPane.setViewportView(textAreaProperties);

		JLabel lblReplaceSize = new JLabel("Replace Size:");
		lblReplaceSize.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReplaceSize.setBounds(10, 108, 82, 14);
		panel.add(lblReplaceSize);

		lblSize = new JLabel("8");
		lblSize.setForeground(Color.RED);
		lblSize.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSize.setBounds(99, 108, 72, 14);
		panel.add(lblSize);

		JButton btnCopyProps = new JButton("Copy Props");
		btnCopyProps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(textAreaProperties.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
			}
		});
		btnCopyProps.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCopyProps.setBounds(10, 138, 107, 37);
		panel.add(btnCopyProps);

		JButton btnNewButton = new JButton("Copy i18n src");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(textAreaReplaceResult.getText());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
			}
		});
		btnNewButton.setBounds(127, 138, 116, 37);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Paste src");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textAreaSource.setText(Toolkit.getDefaultToolkit().getSystemClipboard()
							.getData(DataFlavor.stringFlavor).toString());
				} catch (HeadlessException | UnsupportedFlavorException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(10, 59, 107, 38);
		panel.add(btnNewButton_1);
		splitPane.setDividerLocation(300);
	}

	public JTextArea getTextAreaProperties() {
		return textAreaProperties;
	}

	public JTextArea getTextAreaSource() {
		return textAreaSource;
	}

	public JTextArea getTextAreaReplaceResult() {
		return textAreaReplaceResult;
	}

	public JButton getBtnReplace() {
		return btnReplace;
	}

	public JTextField getTxtNs() {
		return txtNs;
	}

	public JLabel getLblSize() {
		return lblSize;
	}
}
