package cn.itcast.elec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {

	public static Date stringConvertToDate(String source) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		if("".equals(source))
			return null;
		try {
			
			date=sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String dateConvertToString(Date date) {
		if(date!=null &&date.toString().length()>9)
		{
		String s=String.valueOf(date);
		String date1=s.substring(0, 10);
		return date1;
		}
		else
		{	
		return null;
		}
	}

}
