package com.baiyufan.db.persistence;

import java.util.List;

import com.baiyufan.db.model.TContract;

public interface TContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TContract record);

    int insertSelective(TContract record);

    TContract selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TContract record);

    int updateByPrimaryKey(TContract record);
    
    List<TContract> selectClause(TContract contract);
    int checkContractDate(TContract contract);
}