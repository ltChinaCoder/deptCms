package cn.itcast.elec.Dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itcast.elec.domain.ElecSystemDdl;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.util.PageInfo;
import cn.itcast.elec.web.form.ElecTextForm;

public interface ICommonDao<T> {
  public void save(T t);
  public void updateObject(T t);
  public T findObjectById(String textID);
  public void deleteObjectById(T et);
  public void deleteObjectsByIds(String ... textids);
  public void deleteObjectsByCollection(Collection<T> list);
  List<T> findByContionsNoPage(String hqlWhere, LinkedHashMap<String, String> orders);
  void saveByCollection(Collection<T> list);
  List<T> findByContionsWithPage(String hqlWhere, LinkedHashMap<String, String> orders, PageInfo pageInfo);
  }
