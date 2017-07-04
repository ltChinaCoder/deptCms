package cn.itcast.elec.Dao.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.web.form.ElecLogForm;

public interface IElecLogService {
static String service_name="cn.itcast.elec.Dao.Service.Impl.ElecLogServiceImpl";

void saveLog(HttpServletRequest request, String string);


List<ElecLogForm> getAllLog(ElecLogForm ef);




void deleteAllLog(ElecLogForm ef);
}
