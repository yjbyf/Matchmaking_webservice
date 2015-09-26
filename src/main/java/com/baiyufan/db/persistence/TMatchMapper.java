package com.baiyufan.db.persistence;

import java.util.List;

import com.baiyufan.db.model.TMatch;

public interface TMatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMatch record);

    int insertSelective(TMatch record);

    TMatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMatch record);

    int updateByPrimaryKey(TMatch record);
    
    List<TMatch> selectClause(TMatch match);
}