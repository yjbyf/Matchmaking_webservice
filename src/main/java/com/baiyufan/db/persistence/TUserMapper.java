package com.baiyufan.db.persistence;

import java.util.List;
import java.util.Map;

import com.baiyufan.db.model.TUser;

public interface TUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TUser record);

	int insertSelective(TUser record);

	TUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TUser record);

	int updateByPrimaryKey(TUser record);

	List<TUser> selectAll(Map<String, Object> map);
	
	List<TUser> selectClause(TUser user);
	int countClause(TUser user);
	List<TUser> selectExceptName(TUser user);
}