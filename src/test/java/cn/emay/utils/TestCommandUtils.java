package cn.emay.utils;

import cn.emay.utils.command.CommandResult;
import cn.emay.utils.command.CommandUtils;

public class TestCommandUtils {
	
	public void testCommand() {
		CommandResult result = CommandUtils.execCommand(null, null, new String[] { "CMD", "/C", "java","-version" }, 0l, null);
		System.out.println(result.getExitCode());
		System.out.println(result.getMessage());
		System.out.println(result.getErrormessage());
	}

}