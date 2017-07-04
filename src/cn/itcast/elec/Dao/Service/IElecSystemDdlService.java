package cn.itcast.elec.Dao.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.itcast.elec.web.form.ElecSystemDdlForm;
import cn.itcast.elec.web.form.ElecUserForm;

public interface IElecSystemDdlService {
	static String service_name="cn.itcast.elec.Dao.Service.Impl.ElecSystemDdlServiceImpl";

	List<ElecSystemDdlForm> findKeyWords();

	List<ElecSystemDdlForm> showKeyDdl(String keyWord);

	void addEditInfor(ElecSystemDdlForm esd);


	


}
