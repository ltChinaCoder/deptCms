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
		throw new RuntimeException("服务器名称不存在");	
	}
	Object object=null;
	if(spc.ac.containsBean(serviceName)==false)
		throw new RuntimeException("该服务不存在");
	else
	{
		 object=spc.ac.getBean(serviceName);
	}
	return object;	
}
}
