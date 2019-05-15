package cn.emay.utils.command;

import org.junit.Test;

import cn.emay.utils.command.CommandResult;
import cn.emay.utils.command.CommandUtils;

/**
 * 
 * @author Frank
 *
 */
public class CommandUtilsTest {

	@Test
	public void testCommand() {
		CommandResult result = CommandUtils.execCommand(null, null, new String[] { "CMD", "/C", "java", "-version" }, 0L, "GBK");
		System.out.println(result.getExitCode());
		System.out.println(result.getMessage());
		System.out.println(result.getErrormessage());
	}

}
