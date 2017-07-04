package cn.itcast.elec.Dao.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.web.form.ElecEventForm;

public interface IElecEventService {
static String service_name="cn.itcast.elec.Dao.Service.Impl.ElecEventServiceImpl";

List<ElecEventForm> findUser(ElecEventForm eef, HttpServletRequest request);

void saveEvent(ElecEventForm eef);

void deleteEvent(ElecEventForm eef);

ElecEventForm findEventByid(ElecEventForm eef);

void updateEventById(ElecEventForm eef);
 
ArrayList createFiledName();


void saveEventFromExcel(ElecEventForm eef);

List<ElecEventForm> getUserEvent(ElecEventForm eef, HttpServletRequest request);

ArrayList createFieldData(ElecEventForm eef2, HttpServletRequest request);
}
