package com.jyes.www.service.sample;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyes.www.mapper.sample.SampleMapper;
import com.jyes.www.service.sample.impl.ISampleService;
import com.jyes.www.vo.sample.SampleVo;

@Service(value="sampleService")
public class SampleService implements ISampleService {
	
	@Resource(name="sampleMapper")
	private SampleMapper sampleMapper;

	@Override
	public int insertData(SampleVo vo) throws SQLException {
		// TODO Auto-generated method stub
		return sampleMapper.insertData(vo);
	}

	@Override
	public int insertDataTransaction(SampleVo vo) throws SQLException {
		// TODO Auto-generated method stub
		int returnValue = 0;
		returnValue = sampleMapper.insertData(vo);
		if (returnValue > 0) {
			returnValue = sampleMapper.insertData(vo);
		}
		if (returnValue < 1) {
			throw new SQLException("insert 실패 원복");
		}
		return returnValue;
	}

	@Override
	public Object selectOne() throws SQLException {
		// TODO Auto-generated method stub
		return sampleMapper.selectOne();
	}

	@Override
	public List<SampleVo> selectDataList() throws SQLException {
		// TODO Auto-generated method stub
		return sampleMapper.selectDataList();
	}

}
