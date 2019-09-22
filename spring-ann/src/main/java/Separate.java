import java.util.Arrays;
import java.util.List;

public class Separate {

	public static void main(String[] args) {
		String[] vsList = { "ss" };
//		List<String> list = Arrays.asList("ef", "c");
		List<String> list = Arrays.asList("--", "ghi", ":", "-", "rst");
//		String targetStr = "abc,def.ghi";
		String targetStr = "abc---def::ghi::jkl:mno-";
		System.out.println("{" + getResult(targetStr, list) + "}");
	}

	public static String getResult(String str, List<String> list) {
		String split = " ";
		for (String vs : list) {
			str = str.replaceAll(vs, split);
		}
		String[] resultArray = str.split(split);
		StringBuffer sub = new StringBuffer();
		for (int i = 0; i < resultArray.length; i++) {
			sub.append("\"" + resultArray[i] + "\"");
			if (i < resultArray.length - 1) {
				sub.append(",");
			}
		}
		return sub.toString();
	}
}
