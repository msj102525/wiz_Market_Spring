package com.jyes.www.vo.sample;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sampleXmlListVo")
@XmlAccessorType(XmlAccessType.FIELD)
public class SampleXmlListVo {
	@XmlElement(name = "sampleXmlVo")
	private List<SampleVo> sampleXmlListVo = null;

	public List<SampleVo> getSampleXmlListVo() {
		return sampleXmlListVo;
	}
	public void setSampleXmlListVo(List<SampleVo> sampleXmlListVo) {
		this.sampleXmlListVo = sampleXmlListVo;
	}
}