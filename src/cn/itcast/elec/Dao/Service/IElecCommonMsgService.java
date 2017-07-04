package cn.itcast.elec.Dao.Service;

import java.util.LinkedHashMap;
import java.util.List;

import cn.itcast.elec.web.action.ElecCommonMsgAction;
import cn.itcast.elec.web.form.ElecCommonMsgForm;

public interface IElecCommonMsgService {
public static String service_name="cn.itcast.elec.Dao.Service.Impl.ElecCommonMsgServiceImpl";

List<ElecCommonMsgForm> findElecCommonMsgList();

void saveCommonMsg(ElecCommonMsgForm ecmf);

ElecCommonMsgForm homeZdShow();

ElecCommonMsgForm getEcmById(ElecCommonMsgForm ecmf);

void deleteEcmById(ElecCommonMsgForm ecmf);

}
