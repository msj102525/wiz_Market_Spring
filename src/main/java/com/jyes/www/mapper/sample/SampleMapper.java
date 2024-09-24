package com.jyes.www.mapper.sample;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jyes.www.vo.sample.SampleVo;

@Repository(value = "sampleMapper")
public interface SampleMapper {
	public Object selectOne() throws SQLException;
	public int insertData(SampleVo vo) throws SQLException;
	public List<SampleVo> selectDataList() throws SQLException;
}