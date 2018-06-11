package cn.emay.utils.command;

import java.io.File;
import java.util.Map;

/**
 * 执行系统命令工具 支持:超时机制、环境变量、工作目录
 * 
 * @author Frank
 *
 */
public class CommandUtils {

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
	public static CommandResult execCommand(String[] commands) {
		return execCommand(commands, 0l);
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
	public static CommandResult execCommand(String[] commands, Long timeout) {
		return execCommand(null, null, commands, timeout, null);
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
	public static CommandResult execCommand(File workDirectory, Map<String, String> params, String[] commands, Long timeout, String charsetName) {
		if (commands == null || commands.length == 0) {
			throw new IllegalArgumentException("command is null");
		}
		CommandWorker worker = new CommandWorker(workDirectory, params, commands, timeout, charsetName);
		return worker.runCommand();
	}

}
