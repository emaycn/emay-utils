package cn.emay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		Worker worker = new Worker(workDirectory, params, commands, timeout, charsetName);
		worker.start();
		if (ERROR_CODE_EXCEPTION.equals(worker.getExitCode())) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EXCEPTION);
		} else if (ERROR_CODE_TIME_OUT.equals(worker.getExitCode())) {
			throw new IllegalArgumentException(ERROR_MESSAGE_TIME_OUT);
		}
	}

	public static void main(String[] args) {
		CommandUtil.execCommand(null, null, new String[] { "CMD", "/C", "java" }, 0l, null);
	}

}

class Worker extends Thread {

	final static String TIMEOUT_CODE = "-257";

	private ProcessBuilder builder;

	private Long timeout;

	private String charsetName;

	private String message;

	private String errormessage;

	private String exitCode = TIMEOUT_CODE;

	protected Worker(File workDirectory, Map<String, String> params, String[] commands, Long timeout, String charsetName) {
		this.initProcessBuilder(workDirectory, params, commands);
		this.timeout = timeout;
		this.charsetName = charsetName == null ? "GBK" : charsetName;
	}

	private boolean isTimeOut() {
		if (!this.isAlive()) {
			return false;
		}
		if (timeout <= 0l) {
			return false;
		}
		if (exitCode == TIMEOUT_CODE) {
			return true;
		}
		return false;
	}

	public void start() {
		try {
			super.start();
			if (timeout != null && timeout > 0l) {
				this.join(timeout);
				if (this.isTimeOut()) {
					errormessage = CommandUtil.ERROR_MESSAGE_TIME_OUT;
					exitCode = CommandUtil.ERROR_CODE_TIME_OUT;
					this.interrupt();
				}
			} else {
				this.join();
			}
		} catch (InterruptedException e) {
			errormessage = e.getClass().getName() + ":" + e.getMessage();
			exitCode = CommandUtil.ERROR_CODE_EXCEPTION;
		}
	}

	public void run() {
		try {
			Process process = builder.start();
			message = readInputStream(process.getInputStream());
			errormessage = readInputStream(process.getErrorStream());
			exitCode = String.valueOf(process.waitFor());
		} catch (InterruptedException e) {
			errormessage = e.getClass().getName() + ":" + e.getMessage();
			exitCode = CommandUtil.ERROR_CODE_EXCEPTION;
		} catch (IOException e) {
			errormessage = e.getClass().getName() + ":" + e.getMessage();
			exitCode = CommandUtil.ERROR_CODE_EXCEPTION;
		}
	}

	private String readInputStream(InputStream in) throws IOException {
		String content = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(in, charsetName));
		String line = null;
		while ((line = br.readLine()) != null) {
			content += line + "\n";
		}
		if (br != null) {
			br.close();
		}
		return content;
	}

	private void initProcessBuilder(File workDirectory, Map<String, String> params, String[] commands) {
		ProcessBuilder builder = new ProcessBuilder();
		// 错误信息与标准输出不合并
		builder.redirectErrorStream(false);
		builder.command(commands);
		if (workDirectory != null) {
			builder.directory(workDirectory);
		}
		if (params != null) {
			builder.environment().putAll(params);
		}
		this.builder = builder;
	}

	String getMessage() {
		return message;
	}

	String getErrormessage() {
		return errormessage;
	}

	String getExitCode() {
		return exitCode;
	}
}