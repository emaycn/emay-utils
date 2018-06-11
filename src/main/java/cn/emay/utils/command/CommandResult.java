package cn.emay.utils.command;

/**
 * 命令结果
 * 
 * @author Frank
 *
 */
public class CommandResult {

	/**
	 * 成功
	 */
	public final static int CODE_SUCCESS = 1;
	/**
	 * 执行错误
	 */
	public final static int CODE_FAIL = -1;
	/**
	 * 超时
	 */
	public final static int CODE_TIMEOUT = 0;

	/**
	 * 命令输出
	 */
	private String message;

	/**
	 * 异常输出
	 */
	private String errormessage;

	/**
	 * 结束码：1：成功；0：超时；-1：执行异常；<0：执行失败
	 */
	private int exitCode;

	public CommandResult(int exitCode, String message, String errormessage) {
		this.message = message;
		this.errormessage = errormessage;
		this.exitCode = exitCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}

}
