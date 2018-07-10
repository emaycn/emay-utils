package cn.emay.utils.result;

public class TestResult {

	public static void main(String[] args) {
		Result r = Result.badResult("123");
		System.out.println(r.getCode());
		System.out.println(r.getMessage());
		System.out.println(r.getResult());
		System.out.println(r.getSuccess());

		SuperResult<Integer> rr = SuperResult.badResult("123", 123);
		System.out.println(rr.getCode());
		System.out.println(rr.getMessage());
		System.out.println(rr.getResult());
		System.out.println(rr.getSuccess());
	}

}
