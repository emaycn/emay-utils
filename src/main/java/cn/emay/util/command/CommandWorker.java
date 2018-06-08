package cn.emay.util.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class CommandWorker extends Thread {

	final static String TIMEOUT_CODE = "-257";

	private ProcessBuilder builder;

	private Long timeout;

	private String charsetName;

	private String message;

	private String errormessage;

	private String exitCode = TIMEOUT_CODE;

	protected CommandWorker(File workDirectory, Map<String, String> params, String[] commands, Long timeout, String charsetName) {
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
