package com.rig.constants;

public final class Commands {
	public static final String deleteRpms = "NGI_CI_OIBAdapter_Access NGI_CI_OIB_Access NGI_CI_OIB_Dump_Access NGI_CI_OIB_Configuration_Access NGI_CI_OIB_ChromeUI_Access NGI_CI_WebRuntimeAdapter_Access NGI_CI_WebRuntime_Access NGI_CI_Fonts_Access";
	public static final String installRpms = "cd /media/user/20160217*\n rpm -ivh *.rpm";
	public static final String checkInstalledRpms = "rpm -qa | grep NGI_CI";
	public static final String enableAutoTest = "grep -rl 'AutoTestEnabled=FALSE' /opt/jlr/var/nx_browser_chrome/configs/config_browser.ini | xargs sed -i 's/AutoTestEnabled=FALSE/AutoTestEnabled=TRUE/g'\n systemctl restart BRAM.service";
	public static final String stopAutoTest = "grep -rl 'AutoTestEnabled=TRUE' /opt/jlr/var/nx_browser_chrome/configs/config_browser.ini | xargs sed -i 's/AutoTestEnabled=TRUE/AutoTestEnabled=FALSE/g'\n systemctl restart BRAM.service";
	public static final String configBrowserPath = "/opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs";
	public static final String deleteCoredumps = "cd /media/user/coredump\n rm -rf *";
	public static final String deleteEvalTar = "cd /opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs\n rm -f eval_result.tar";
	public static final String deleteEvalResult = "cd /opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs\n rm -rf eval_result";
	public static final String deleteSiteListPos = "cd /opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs\n rm sitelist_pos.txt";
	public static final String createOIB_br_backtrace = "for coredump in /media/user/coredump/core-OIB_br-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb /opt/jlr/bin/nx_browser/browser/OIB_br ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_OIB_br.txt";
	public static final String createBrUiThr_backtrace = "for coredump in /media/user/coredump/core-oib_brUiThr-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb /opt/jlr/bin/nx_browser/browser/OIB_br ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_oib_brUiThr.txt";
	public static final String createBrEgThread_backtrace = "for coredump in /media/user/coredump/core-oib_brEgThread-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb /opt/jlr/bin/nx_browser/browser/OIB_br ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_oib_brEgThread.txt";
	public static final String createOIB_ui_backtrace = "for coredump in /media/user/coredump/core-OIB_ui-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb /opt/jlr/bin/nx_browser/ui/OIB_ui ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_OIB_ui.txt";
	public static final String createNx_sp_webruntim_backtrace = "for coredump in /media/user/coredump/core-nx_sp_webruntim-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb /opt/jlr/bin/nx_sp_webruntime/nx_sp_webruntime  ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_nx_sp_webruntime.txt";
	public static final String createNetfrontnx_backtrace = "for coredump in /media/user/coredump/core-netfrontnx-* ; do echo -e \"--------\n${coredump}\n----\n\"; gdb //opt/jlr/bin/nx_ci_webruntime/netfrontnx  ${coredump} -ex bt -ex \"thread apply all bt\" -batch; done > /var/db/ext/$(date '+%Y%m%d')_backtraces_netfrontnx.txt";
	public static final String listCoredumps = "cd /media/user/coredump/" + "\n" + "ls core-OIB_br-*" + "\n"
			+ "ls core-oib_brUiThr-*" + "\n" + "ls core-oib_brEgThread-*" + "\n" + "ls core-OIB_ui-*" + "\n"
			+ "ls core-nx_sp_webruntim-*" + "\n" + "ls core-netfrontnx-*";
	public static final String remoteConfigPath = "/opt/jlr/share/ci/preinstalled/apps/nx_browser_chrome/configs";
	public static final String remoteCoredumpPath = "/media/user/coredump";
	public static final String INSTRUCTION = "1. Add IP to C:\\builds\\config\\config.ini file and save\n"
			+ "2. Put origin daily build you downloaded from Jenkins to C:\\builds folder\n"
			+ "3. Copy sitelist.txt also to C:\\builds\n" + "4. Choose your RIG ip from the list\n"
			+ "5. Drag and drop daily build from Explorer to Daily Build rpms field and sitelist.txt to corresponding Sitelist field\n"
			+ "6. Choose the time to start the autotest and click on Start\n"
			+ "7. The results will be created after you click on Stop button, store location: C:\\results";
}
