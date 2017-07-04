package cn.itcast.elec.Dao.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.elec.web.form.ElecQuestionForm;

public interface IElecQuestionService {
static String service_name="cn.itcast.elec.Dao.Service.Impl.ElecQuestionServiceImpl";

void saveQuestion(ElecQuestionForm eqf, HttpServletRequest request);

List<ElecQuestionForm> getAllQuestion(ElecQuestionForm eqf);

void deleteAllQuestion();

}
