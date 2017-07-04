package cn.itcast.elec.Container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.xml.internal.ws.util.StringUtils;

import freemarker.template.utility.StringUtil;

public class ServiceProviderCore {
protected static ApplicationContext ac;
public static void load(String filename)
{
	if(org.apache.commons.lang.StringUtils.isBlank(filename))
		throw new RuntimeException("路径不能为空");
	ac=new ClassPathXmlApplicationContext(filename);
}
}
