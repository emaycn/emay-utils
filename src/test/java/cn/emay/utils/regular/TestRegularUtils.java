package cn.emay.utils.regular;

public class TestRegularUtils {

	public static void main(String[] args) {
		boolean flag = RegularUtils.matches("^\\d$", "1");
		String str = RegularUtils.specialCodeEscape("#@$$%^^&&&*(())!");
		boolean flag2 = RegularUtils.checkString("zhong中文");
		boolean flag3 = RegularUtils.notExistSpecial("hanyou特殊字符￥@@");
		System.out.println("是否匹配检查：" + flag);
		System.out.println("特殊字符转义：" + str);
		System.out.println("验证只含有中文和英文：" + flag2);
		System.out.println("校验是否包含特殊字符：" + flag3);
	}

}
