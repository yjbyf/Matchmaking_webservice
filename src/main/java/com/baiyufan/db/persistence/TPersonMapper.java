package com.baiyufan.db.persistence;

import java.util.List;
import java.util.Map;

import com.baiyufan.db.model.TPerson;

public interface TPersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPerson record);

    int insertSelective(TPerson record);

    TPerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPerson record);

    int updateByPrimaryKey(TPerson record);
    
    List<TPerson> selectAll(Map<String, Object> map);
    List<TPerson> selectClause(TPerson user);
}