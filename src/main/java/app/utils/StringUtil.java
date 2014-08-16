package app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	
	/**
	 * 多行变为一行
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
