package cn.itcast.elec.Dao.Service;

import java.util.List;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.form.ElecTextForm;

public interface IElecTextService {
	public  static final String  Service_Name="cn.itcast.elec.Dao.Service.Impl.ElecTextServiceImpl";
 public void saveElecText(ElecText elecText);
 public List findByContionsNoPage(ElecTextForm etf);
}
