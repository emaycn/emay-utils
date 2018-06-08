package cn.emay.util.command;

import java.io.File;
import java.util.Map;

/**
 * 执行系统命令工具 支持:超时机制、环境变量、工作目录
 * 
 * @author Frank
 *
 */
public class CommandUtil {

	final static String ERROR_CODE_NO_COMMAND = "1001";
	final static String ERROR_CODE_EXCEPTION = "1002";
	final static String ERROR_CODE_TIME_OUT = "1003";

	final static String ERROR_MESSAGE_TIME_OUT = "time out ";
	final static String ERROR_MESSAGE_NO_COMMAND = "no command to execution";
	final static String ERROR_MESSAGE_EXCEPTION = "run exception";

	/**
	 * 执行外部命令
	 * 
	 * @param commands
	 *            命令集 如 cp "cn test" /etc/ ---> {cp,"cn test",/etc/}
	 * @return 外部命令返回的结果<br/>
	 * 
	 *         code: 0 成功 <br/>
	 *         1 命令执行报错<br/>
	 *         1001 没有可执行的命令<br/>
	 *         1002 JAVA调用外部命令报错<br/>
	 */
	public static void execCommand(String[] commands) {
		execCommand(commands, 0l);
	}

	/**
	 * 执行外部命令
	 * 
	 * @param commands
	 *            命令集 如 cp "cn test" /etc/ ---> {cp,"cn test",/etc/}
	 * @param timeout
	 *            超时时间
	 * @return 外部命令返回的结果<br/>
	 * 
	 *         code: 0 成功 <br/>
	 *         1 命令执行报错<br/>
	 *         1001 没有可执行的命令<br/>
	 *         1002 JAVA调用外部命令报错<br/>
	 *         1003 超时<br/>
	 */
	public static void execCommand(String[] commands, Long timeout) {
		execCommand(null, null, commands, timeout, null);
	}

	/**
	 * 执行外部命令
	 * 
	 * @param workDirectory
	 *            工作目录
	 * @param params
	 *            环境变量
	 * @param commands
	 *            命令集 如 cp "cn test" /etc/ ---> {cp,"cn test",/etc/}
	 * @param timeout
	 *            超时时间
	 * @param charsetName
	 *            结果集编码 默认GBK
	 * @return 外部命令返回的结果<br/>
	 * 
	 *         code: 0 成功 <br/>
	 *         1 命令执行报错<br/>
	 *         1001 没有可执行的命令<br/>
	 *         1002 JAVA调用外部命令报错<br/>
	 *         1003 超时<br/>
	 */
	public static void execCommand(File workDirectory, Map<String, String> params, String[] commands, Long timeout, String charsetName) {
		if (commands == null || commands.length == 0)
			throw new IllegalArgumentException(ERROR_MESSAGE_NO_COMMAND);
		CommandWorker worker = new CommandWorker(workDirectory, params, commands, timeout, charsetName);
		worker.start();
		worker.getMessage();
		worker.getExitCode();
		worker.getErrormessage();
	}

}
