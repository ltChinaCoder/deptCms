package cn.itcast.elec.Container;

import org.apache.commons.lang.StringUtils;

public class ServiceProvider {
private static ServiceProviderCore spc;
static
{
	spc.load("beans.xml");
}
public static Object getService(String serviceName)
{
	if(StringUtils.isBlank(serviceName))
	{
		throw new RuntimeException("���������Ʋ�����");	
	}
	Object object=null;
	if(spc.ac.containsBean(serviceName)==false)
		throw new RuntimeException("�÷��񲻴���");
	else
	{
		 object=spc.ac.getBean(serviceName);
	}
	return object;	
}
}
