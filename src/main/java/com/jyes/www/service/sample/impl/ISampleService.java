package com.jyes.www.service.sample.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyes.www.vo.sample.SampleVo;

/*
 * Copyright jyes.co.kr.
 * All rights reserved
 * This software is the confidential and proprietary information
 * of jyes.co.kr. ("Confidential Information")
 */
public interface ISampleService {
	public Object selectOne() throws SQLException;
	public int insertData(SampleVo vo) throws SQLException;
	@Transactional(rollbackFor={SQLException.class})
	public int insertDataTransaction(SampleVo vo) throws SQLException;
	public List<SampleVo> selectDataList() throws SQLException;
}