package com.rig.jarrig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;

public class RigComponents extends CustomComponent {

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    @AutoGenerated
    private AbsoluteLayout mainLayout;
    @AutoGenerated
    private Label Test;
    @AutoGenerated
    private TextField siteList;
    @AutoGenerated
    private Button stopAutotest;
    @AutoGenerated
    private Button startTest;
    @AutoGenerated
    private ComboBox timeToStartTest;
    @AutoGenerated
    private TabSheet Tabs;
    @AutoGenerated
    private TextArea configBrowserIni;
    @AutoGenerated
    private Tree filesystem;
    @AutoGenerated
    private Button checkInstalled;
    @AutoGenerated
    private TextArea consoleOutput;
    @AutoGenerated
    private Button delete;
    @AutoGenerated
    private Button connectRig;
    @AutoGenerated
    private Label projectName;
    @AutoGenerated
    private Button custominstall;
    @AutoGenerated
    private Button releaseinstall;
    @AutoGenerated
    private Button dailyinstall;
    @AutoGenerated
    private TextField custom;
    @AutoGenerated
    private TextField release;
    @AutoGenerated
    private TextField daily;
    @AutoGenerated
    private ComboBox IPs;
    String user = "root";
    String password = "root";
    String host1 = "10.96.20.190";
    String host2 = "10.96.20.170";
    String host3 = "10.96.20.73";
    int port = 22;
    String remoteFile = "/media/user/testSSH.txt";
    String line;
    DragAndDropWrapper dndWrapperDaily;
    DragAndDropWrapper dndWrapperSiteList;
    JSch jsch = new JSch();
    Session session;
    Channel channel;
    String deleteRpms = "rpm --nodeps -e NGI_CI_OIBAdapter_Access NGI_CI_OIB_Access NGI_CI_OIB_Dump_Access NGI_CI_OIB_Configuration_Access NGI_CI_OIB_ChromeUI_Access NGI_CI_WebRuntimeAdapter_Access NGI_CI_WebRuntime_Access NGI_CI_Fonts_Access";
    String installRpms = "cd /media/user/20160217*\n rpm -ivh *.rpm";
    String checkInstalledRpms = "rpm -qa | grep NGI_CI";
    String enableAutoTest = "grep -rl 'AutoTestEnabled=FALSE' /opt/jlr/var/nx_browser_chrome/configs/config_browser.ini | xargs sed -i 's/AutoTestEnabled=FALSE/AutoTestEnabled=TRUE/g'\n systemctl restart BRAM.service";
    String stopAutoTest = "grep -rl 'AutoTestEnabled=TRUE' /opt/jlr/var/nx_browser_chrome/configs/config_browser.ini | xargs sed -i 's/AutoTestEnabled=TRUE/AutoTestEnabled=FALSE/g'\n systemctl restart BRAM.service";
    String configBrowserPath = "/opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs";
    String deleteCoredumps = "cd /media/user/coredump\n rm *";
    String deleteEvalResult = "cd /opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs\n rm -rf eval_result";
    String deleteSiteListPos = "cd /opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs\n rm sitelist_pos.txt";
    String createOIB_br_backtrace = "for coredump in /media/user/coredump/core-OIB_br-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb /opt/jlr/bin/nx_browser/browser/OIB_br ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_OIB_br.txt";
    String createBrUiThr_backtrace = "for coredump in /media/user/coredump/core-oib_brUiThr-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb /opt/jlr/bin/nx_browser/browser/OIB_br ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_oib_brUiThr.txt";
    InputStream in;
    Timer timerStart = new Timer();
    Timer timerStop = new Timer();
    String[] autoTestStart;

    /**
     * TO DO: Give this project a structure with packages and split this class
     * into View/Logic subcomponents !!! Create MySQL Database connection to
     * store commands etc. or use a static enumeration class for commands
     * Optimize project structure!!
     */
    public RigComponents() {
	buildMainLayout();
	setCompositionRoot(mainLayout);
	registerWrapper();
	initFileExplorer();
	initTextfieldListener();
	initButtonsListener();
	initTabsheet();
	IPs.addItem(host1);
	IPs.addItem(host2);
	IPs.addItem(host3);

	// TODO add user code here
    }

    private void initComboBoxTime() {
	for (int i = 0; i < 10; i++) {
	    timeToStartTest.addItem("0" + i + ":00");
	}
	for (int i = 10; i < 24; i++) {
	    timeToStartTest.addItem(i + ":00");
	}
    }

    private void initTextfieldListener() {
	daily.addValueChangeListener(new ValueChangeListener() {

	    public void valueChange(ValueChangeEvent event) {
		daily.setDescription(daily.getValue().toString());
	    }
	});
	daily.addTextChangeListener(new TextChangeListener() {

	    public void textChange(TextChangeEvent event) {
		daily.setDescription(event.getText());
	    }
	});

    }

    private String getCurrentTime() {
	Date dateNow = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
	return ft.format(dateNow);
    }

    private void initButtonStart() {
	initComboBoxTime();
	startTest.setEnabled(true);
	startTest.addClickListener(new ClickListener() {
	    public void buttonClick(ClickEvent event) {
		pushSiteListTxt();
		deleteCashedFiles();
		System.out.println(getCurrentTime());
		String[] timeSplitCurrent = getCurrentTime().split(":");
		Object selectedItem = timeToStartTest.getValue();
		// Object selectedItem = "16:47";
		String[] timeSplitSetted = selectedItem.toString().split(":");
		float hourCurrent = Float.parseFloat(timeSplitCurrent[0]);
		float minCurrent = Float.parseFloat(timeSplitCurrent[1]);
		float hourSetted = Float.parseFloat(timeSplitSetted[0]);
		float minSetted = Float.parseFloat(timeSplitSetted[1]);
		final Float countDownTimer = new Float(
			calculateCountdown(hourCurrent, minCurrent, hourSetted, minSetted));
		System.out.println(countDownTimer);
		timerStart.schedule(new TimerTask() {
		    @Override
		    public void run() {
			if (!session.isConnected()) {
			    openSSHConnection();
			}
			openChannel();
			((ChannelExec) channel).setCommand(enableAutoTest);
			updateOutputConsole(enableAutoTest);
		    }
		}, countDownTimer.longValue());
		startTest.setEnabled(false);
	    }

	    private void pushSiteListTxt() {
		openSftpChannelPut(siteList.getValue(), configBrowserPath);
	    }

	    private void deleteCashedFiles() {
		openChannel();
		((ChannelExec) channel)
			.setCommand(deleteCoredumps + "\n" + deleteEvalResult + "\n" + deleteSiteListPos);
		updateOutputConsole(deleteCoredumps + "\n" + deleteEvalResult + "\n" + deleteSiteListPos);
	    }

	});
    }

    private float calculateCountdown(float hCurrent, float mCurrent, float hSetted, float mSetted) {
	float minResultDezimal;
	float hourResult;

	float tmpMCurDezimal = mCurrent / 60;
	System.out.println("MinutenCurrent Dezimal: " + tmpMCurDezimal);
	float tmpHCur = hCurrent + tmpMCurDezimal;
	System.out.println("tmpHCur: " + tmpHCur);

	float tmpMSetDezimal = mSetted / 60;
	System.out.println("MinutenSetted Dezimal: " + tmpMSetDezimal);
	float tmpHSet = hSetted + tmpMSetDezimal;
	System.out.println("tmpHSet: " + tmpHSet);

	if (tmpMCurDezimal <= tmpMSetDezimal) {
	    minResultDezimal = tmpMSetDezimal - tmpMCurDezimal;
	    hourResult = hSetted - hCurrent;
	} else {
	    minResultDezimal = 1 - (tmpMCurDezimal - tmpMSetDezimal);
	    hourResult = (hSetted - hCurrent) - 1;
	}
	System.out.println("minResultDezimal: " + minResultDezimal);
	System.out.println("hourResult: " + hourResult);
	return (hourResult * 60 * 60 * 1000) + (minResultDezimal * 60 * 60 * 1000);
    }

    private void initTabsheet() {
	configBrowserIni.setValue("Here to place Config_browser.ini");
    }

    private void initButtonsListener() {
	initButtonConnect();
	initButtonDailyInstall();
	initButtonDelete();
	initButtonInstalled();
	initButtonStart();
	initButtonStop();
    }

    private void initButtonStop() {
	stopAutotest.addClickListener(new ClickListener() {

	    public void buttonClick(ClickEvent event) {
		openChannel();
		((ChannelExec) channel).setCommand(stopAutoTest + "\n" + createOIB_br_backtrace);
		updateOutputConsole(stopAutoTest + "\n" + createOIB_br_backtrace);

		startTest.setEnabled(true);
	    }
	});

    }

    private void initButtonConnect() {
	connectRig.addClickListener(new ClickListener() {

	    public void buttonClick(ClickEvent event) {
		if (IPs.getValue() != null) {
		    openSSHConnection();
		    openChannel();
		    ((ChannelExec) channel).setCommand("echo \"connected\"");
		    updateOutputConsole("echo \"connected\"");
		}
	    }
	});
    }

    private void initButtonInstalled() {
	checkInstalled.addClickListener(new ClickListener() {

	    public void buttonClick(ClickEvent event) {
		openChannel();
		((ChannelExec) channel).setCommand(checkInstalledRpms);
		updateOutputConsole(checkInstalledRpms);
	    }
	});
    }

    private void initButtonDelete() {
	delete.addClickListener(new ClickListener() {

	    public void buttonClick(ClickEvent event) {
		openChannel();
		((ChannelExec) channel).setCommand(deleteRpms);
		updateOutputConsole(deleteRpms);
	    }
	});
    }

    private void initButtonDailyInstall() {
	dailyinstall.addClickListener(new ClickListener() {
	    public void buttonClick(ClickEvent event) {
		// extractArchive(daily.getValue());
		openSftpChannelPut(daily.getValue(), "/media/user/");
		openChannel();
		String extractAndInstall = "cd /media/user\n tar -zxvf " + getTarFileName(daily.getValue()) + "\n rm "
			+ getTarFileName(daily.getValue()) + "\n cd /media/user/"
			+ getTarFileName(daily.getValue()).substring(0, getTarFileName(daily.getValue()).length() - 7)
			+ "\n rpm -ivh *.rpm";
		((ChannelExec) channel).setCommand(extractAndInstall);
		updateOutputConsole(extractAndInstall);

	    }

	    private String getTarFileName(String tarFile) {
		tarFile = tarFile.replace('\\', '/');
		File file = new File(tarFile);
		return file.getName();
	    }

	    // private void extractArchive(String tarFile) {
	    //
	    // String directoryFile = tarFile.substring(0, tarFile.length() -
	    // 3);
	    // tarFile = tarFile.replace('\\', '/');
	    // File file = new File(tarFile);
	    // File destFolder = new File(directoryFile);
	    // FileInputStream fileIn = null;
	    // try {
	    // fileIn = new FileInputStream(file);
	    // } catch (FileNotFoundException e) {
	    // // TODO Auto-generated catch block
	    // e.printStackTrace();
	    // }
	    // try {
	    // GZIPInputStream unarchiver = new GZIPInputStream(fileIn);
	    // FileOutputStream fileOutputStream = new
	    // FileOutputStream(destFolder);
	    // int bytes_read;
	    // byte[] buffer = new byte[1024];
	    // while ((bytes_read = unarchiver.read(buffer)) > 0) {
	    // fileOutputStream.write(buffer, 0, bytes_read);
	    // }
	    // unarchiver.close();
	    // fileOutputStream.close();
	    // System.out.println("The file was decompressed successfully!");
	    //
	    // } catch (IOException e1) {
	    // // TODO Auto-generated catch block
	    // e1.printStackTrace();
	    // }
	    //
	    // }
	});

    }

    private void openSftpChannelPut(String tarFile, String path) {
	tarFile = tarFile.replace('\\', '/');
	File file = new File(tarFile);

	ChannelSftp sftpChannel = null;
	try {
	    sftpChannel = (ChannelSftp) session.openChannel("sftp");
	    sftpChannel.connect();

	} catch (JSchException e1) {
	    e1.printStackTrace();
	}
	try {
	    sftpChannel.cd(path);
	    sftpChannel.put(new FileInputStream(file), file.getName());
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SftpException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    sftpChannel.exit();
	}
    }

    private void updateOutputConsole(String operation) {
	try {
	    in = channel.getInputStream();
	    channel.connect();
	    String outStream = "Command executed: " + operation + "\n\n" + "Output: \n";
	    byte[] tmp = new byte[1024];
	    while (true) {
		while (in.available() > 0) {
		    int i = in.read(tmp, 0, 1024);
		    if (i < 0)
			break;
		    outStream = outStream + new String(tmp, 0, i);
		    System.out.print(new String(tmp, 0, i));
		}
		if (channel.isClosed()) {
		    if (in.available() > 0)
			continue;
		    outStream = outStream + "Exit-status: " + channel.getExitStatus();
		    break;
		}
	    }
	    consoleOutput.setReadOnly(false);
	    consoleOutput.setValue(outStream);
	    consoleOutput.setReadOnly(true);
	} catch (Exception e) {
	    System.out.println(e);
	}
    }

    private void initFileExplorer() {
	FilesystemContainer rigDocs = new FilesystemContainer(new File("C:/Users/jaroslav.kniss/Downloads/"), false);
	filesystem.setContainerDataSource(rigDocs);
	filesystem.setImmediate(true);
	filesystem.setSelectable(true);
	filesystem.setDragMode(TreeDragMode.NODE);
    }

    @SuppressWarnings({ "deprecation", "serial" })
    private void registerWrapper() {
	dndWrapperDaily = new DragAndDropWrapper(daily);
	dndWrapperDaily.setDropHandler(new DropHandler() {
	    public AcceptCriterion getAcceptCriterion() {
		return AcceptAll.get();
	    }

	    // get item selected in the filesystem list and put it to dropped
	    // textfield item
	    public void drop(DragAndDropEvent event) {
		Transferable t = event.getTransferable();
		Object sourceItemId = t.getData("itemId");
		daily.setValue(sourceItemId.toString());
	    }
	});
	dndWrapperDaily.setWidth(daily.getWidth(), UNITS_PIXELS);
	dndWrapperDaily.setCaption("Daily Build rpms");
	mainLayout.addComponent(dndWrapperDaily, "top:120.0px;left:60.0px;");

	dndWrapperSiteList = new DragAndDropWrapper(siteList);
	dndWrapperSiteList.setDropHandler(new DropHandler() {
	    public AcceptCriterion getAcceptCriterion() {
		return AcceptAll.get();
	    }

	    // get item selected in the filesystem list and put it to dropped
	    // textfield item
	    public void drop(DragAndDropEvent event) {
		Transferable t = event.getTransferable();
		Object sourceItemId = t.getData("itemId");
		siteList.setValue(sourceItemId.toString());
	    }
	});

	dndWrapperSiteList.setWidth(siteList.getWidth(), UNITS_PIXELS);
	dndWrapperSiteList.setCaption("Sitelist.txt");
	mainLayout.addComponent(dndWrapperSiteList, "top:420.0px;left:60.0px;");

    }

    private void openSSHConnection() {
	try {
	    session = jsch.getSession(user, IPs.getValue().toString(), port);
	    session.setPassword(password);
	    session.setConfig("StrictHostKeyChecking", "no");
	    session.connect();
	    // InputStream in_stream = new
	    // ByteArrayInputStream("ls".getBytes(StandardCharsets.UTF_8));
	    // channel.setInputStream(null);

	    // channel.setOutputStream();
	    // ((ChannelExec) channel).setErrStream(System.err);

	    // ChannelSftp sftpChannel = (ChannelSftp)
	    // session.openChannel("sftp");
	    // sftpChannel.connect();
	    // System.out.println("SSH Channel created.");

	    // InputStream out= null;
	    // out= sftpChannel.get(remoteFile);
	    // BufferedReader br = new BufferedReader(new
	    // InputStreamReader(out));
	    // while ((line = br.readLine()) != null)
	    // System.out.println(line);
	    // br.close();

	} catch (Exception e) {
	    System.err.print(e);
	}
    }

    private Channel openChannel() {
	try {
	    channel = session.openChannel("exec");
	} catch (JSchException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return channel;
    }

    @AutoGenerated
    private AbsoluteLayout buildMainLayout() {
	// common part: create layout
	mainLayout = new AbsoluteLayout();
	mainLayout.setImmediate(false);
	mainLayout.setWidth("1920px");
	mainLayout.setHeight("1000px");

	// top-level component properties
	setWidth("1920px");
	setHeight("1000px");

	// IPs
	IPs = new ComboBox();
	IPs.setCaption("IP");
	IPs.setImmediate(false);
	IPs.setWidth("200px");
	IPs.setHeight("-1px");
	mainLayout.addComponent(IPs, "top:60.0px;left:60.0px;");

	// daily
	daily = new TextField();
	daily.setCaption("Daily Build rpms");
	daily.setImmediate(false);
	daily.setWidth("200px");
	daily.setHeight("-1px");
	mainLayout.addComponent(daily, "top:120.0px;left:60.0px;");

	// release
	release = new TextField();
	release.setCaption("Release rpms");
	release.setImmediate(false);
	release.setWidth("200px");
	release.setHeight("-1px");
	mainLayout.addComponent(release, "top:180.0px;left:60.0px;");

	// custom
	custom = new TextField();
	custom.setCaption("Custom rpms");
	custom.setImmediate(false);
	custom.setWidth("200px");
	custom.setHeight("-1px");
	mainLayout.addComponent(custom, "top:240.0px;left:60.0px;");

	// dailyinstall
	dailyinstall = new Button();
	dailyinstall.setCaption("install");
	dailyinstall.setImmediate(true);
	dailyinstall.setWidth("-1px");
	dailyinstall.setHeight("-1px");
	mainLayout.addComponent(dailyinstall, "top:120.0px;left:278.0px;");

	// releaseinstall
	releaseinstall = new Button();
	releaseinstall.setCaption("install");
	releaseinstall.setImmediate(true);
	releaseinstall.setWidth("-1px");
	releaseinstall.setHeight("-1px");
	mainLayout.addComponent(releaseinstall, "top:180.0px;left:278.0px;");

	// custominstall
	custominstall = new Button();
	custominstall.setCaption("install");
	custominstall.setImmediate(true);
	custominstall.setWidth("-1px");
	custominstall.setHeight("-1px");
	mainLayout.addComponent(custominstall, "top:240.0px;left:278.0px;");

	// projectName
	projectName = new Label();
	projectName.setImmediate(false);
	projectName.setWidth("-1px");
	projectName.setHeight("-1px");
	projectName.setValue("JarRiG");
	mainLayout.addComponent(projectName, "top:11.0px;left:11.0px;");

	// connectRig
	connectRig = new Button();
	connectRig.setCaption("Connect");
	connectRig.setImmediate(true);
	connectRig.setWidth("-1px");
	connectRig.setHeight("-1px");
	mainLayout.addComponent(connectRig, "top:60.0px;left:278.0px;");

	// delete
	delete = new Button();
	delete.setCaption("uninstall");
	delete.setImmediate(true);
	delete.setWidth("-1px");
	delete.setHeight("158px");
	mainLayout.addComponent(delete, "top:120.0px;left:380.0px;");

	// consoleOutput
	consoleOutput = new TextArea();
	consoleOutput.setCaption("Console Output");
	consoleOutput.setImmediate(false);
	consoleOutput.setReadOnly(true);
	consoleOutput.setWidth("754px");
	consoleOutput.setHeight("276px");
	consoleOutput.setWordwrap(false);
	mainLayout.addComponent(consoleOutput, "top:422.0px;left:541.0px;");

	// checkInstalled
	checkInstalled = new Button();
	checkInstalled.setCaption("installed Rpms");
	checkInstalled.setImmediate(true);
	checkInstalled.setWidth("-1px");
	checkInstalled.setHeight("-1px");
	mainLayout.addComponent(checkInstalled, "top:314.0px;left:278.0px;");

	// Tabs
	Tabs = buildTabs();
	mainLayout.addComponent(Tabs, "top:29.0px;left:540.0px;");

	// timeToStartTest
	timeToStartTest = new ComboBox();
	timeToStartTest.setCaption("Autotest start");
	timeToStartTest.setImmediate(false);
	timeToStartTest.setWidth("-1px");
	timeToStartTest.setHeight("-1px");
	mainLayout.addComponent(timeToStartTest, "top:496.0px;left:60.0px;");

	// startTest
	startTest = new Button();
	startTest.setCaption("Start");
	startTest.setImmediate(true);
	startTest.setWidth("-1px");
	startTest.setHeight("-1px");
	mainLayout.addComponent(startTest, "top:496.0px;left:260.0px;");

	// stopAutotest
	stopAutotest = new Button();
	stopAutotest.setCaption("Stop");
	stopAutotest.setImmediate(true);
	stopAutotest.setWidth("-1px");
	stopAutotest.setHeight("-1px");
	mainLayout.addComponent(stopAutotest, "top:496.0px;left:340.0px;");

	// siteList
	siteList = new TextField();
	siteList.setCaption("Sitelist.txt ");
	siteList.setImmediate(false);
	siteList.setWidth("200px");
	siteList.setHeight("-1px");
	mainLayout.addComponent(siteList, "top:420.0px;left:60.0px;");

	// Test
	Test = new Label();
	Test.setImmediate(false);
	Test.setWidth("-1px");
	Test.setHeight("-1px");
	Test.setValue("Autotest");
	mainLayout.addComponent(Test, "top:360.0px;left:11.0px;");

	return mainLayout;
    }

    @AutoGenerated
    private TabSheet buildTabs() {
	// common part: create layout
	Tabs = new TabSheet();
	Tabs.setImmediate(true);
	Tabs.setWidth("-1px");
	Tabs.setHeight("360px");

	// filesystem
	filesystem = new Tree();
	filesystem.setCaption("Filesystem");
	filesystem.setImmediate(false);
	filesystem.setWidth("-1px");
	filesystem.setHeight("329px");
	Tabs.addTab(filesystem, "Explorer", null);

	// configBrowserIni
	configBrowserIni = new TextArea();
	configBrowserIni.setImmediate(true);
	configBrowserIni.setWidth("-1px");
	configBrowserIni.setHeight("-1px");
	configBrowserIni.setWordwrap(false);
	Tabs.addTab(configBrowserIni, "config_browser.ini", null);

	return Tabs;
    }

}
