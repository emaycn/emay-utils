package cn.emay.utils.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 命令执行器
 * 
 * @author Frank
 *
 */
public class CommandWorker extends Thread {

	/**
	 * 命令执行器
	 */
	private ProcessBuilder builder;

	/**
	 * 超时时间
	 */
	private long timeout;

	/**
	 * 编码
	 */
	private String charsetName;

	/**
	 * 执行完毕后打印的信息
	 */
	private String message;

	/**
	 * 错误信息
	 */
	private String errormessage;

	/**
	 * 结束码
	 */
	private int exitCode = CommandResult.CODE_SUCCESS;

	/**
	 * 
	 * @param workDirectory
	 *            工作目录
	 * @param params
	 *            参数
	 * @param commands
	 *            命令
	 * @param timeout
	 *            超时时间
	 * @param charsetName
	 *            返回结果编码
	 */
	protected CommandWorker(File workDirectory, Map<String, String> params, String[] commands, long timeout, String charsetName) {
		this.initProcessBuilder(workDirectory, params, commands);
		this.timeout = timeout;
		this.charsetName = charsetName == null ? "UTF-8" : charsetName;
	}

	/**
	 * 启动
	 */
	public CommandResult runCommand() {
		try {
			super.start();
			if (timeout > 0L) {
				this.join(timeout);
				if (this.isAlive() && exitCode == CommandResult.CODE_SUCCESS) {
					exitCode = CommandResult.CODE_TIMEOUT;
					this.interrupt();
				}
			} else {
				this.join();
			}
		} catch (InterruptedException e) {
			errormessage = e.getClass().getName() + ":" + e.getMessage();
			exitCode = CommandResult.CODE_FAIL;
		}
		return new CommandResult(exitCode, message, errormessage);
	}

	@Override
	public void run() {
		try {
			Process process = builder.start();
			message = readInputStream(process.getInputStream());
			errormessage = readInputStream(process.getErrorStream());
			exitCode = process.waitFor();
		} catch (InterruptedException e) {
			errormessage = e.getClass().getName() + ":" + e.getMessage();
			exitCode = CommandResult.CODE_FAIL;
		} catch (IOException e) {
			errormessage = e.getClass().getName() + ":" + e.getMessage();
			exitCode = CommandResult.CODE_FAIL;
		}
	}

	/**
	 * 读取执行完毕输入流
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * 初始化执行器
	 * 
	 * @param workDirectory
	 *            工作目录
	 * @param params
	 *            参数
	 * @param commands
	 *            命令
	 */
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

}
