package utilities;

import java.util.Date;

public class DateUtils {
		public static String getTimeStamp() {
			Date date = new Date();
			//replacing whitespace and : with _ for extent report filename
			return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
			

		}

	}