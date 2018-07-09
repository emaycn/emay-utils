package cn.emay.utils.id;

public class TestOnlyIdGenerator {

	public static void main(String[] args) {
		System.out.println(OnlyIdGenerator.genOnlyBId("123"));
		System.out.println(OnlyIdGenerator.genOnlyId("123"));
		System.out.println(OnlyIdGenerator.genUUID());
	}

}
