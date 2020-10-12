package com.myIsoland.service.file;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseService;
import com.myIsoland.common.config.V2Config;
import com.myIsoland.common.file.FileUploadUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.enitity.file.TsysDatas;
import com.myIsoland.enitity.file.TsysDatasExample;
import com.myIsoland.mapper.file.TsysDatasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SysDatasService implements BaseService<TsysDatas, TsysDatasExample> {
	
	
	@Autowired
	private TsysDatasMapper tsysDatasMapper;
	

	
	
	
	
	/**
	 * 文件上传文件存储到文件表
	 * @param record
	 * @param fileURL
	 * @return 主键
	 * @throws IOException 
	 */
	public String insertSelective(MultipartFile file) throws IOException {
		//文件上传获取文件名字
        String files_name = FileUploadUtils.upload(file);
        //相对路径——项目url请求路径
        String relative_filesURL= V2Config.getIsroot_dir()+files_name;
        //绝对路径-删除需要得路径
        String absolute_filesURL=null;
        
        
    	if ("Y".equals(V2Config.getIsstatic())) {//项目路径
           	absolute_filesURL=V2Config.getIsroot_dir()+files_name;
   		}else {//磁盘路径
   			absolute_filesURL=V2Config.getDefaultBaseDir()+files_name;
   			//filesURL=V2Config.getIsroot_dir()+files;
   		}
     
        String fileName=file.getOriginalFilename();
    	// 获得文件后缀名称
    	String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    	if(StringUtils.isEmpty(suffixName)) {
    		//如果没有后缀默认后缀
    		suffixName=FileUploadUtils.IMAGE_JPG_EXTENSION;
    	}
     
		TsysDatas record=new TsysDatas();
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		record.setFilePath(relative_filesURL);
		record.setFileAbsolutePath(absolute_filesURL);
		record.setFileSuffix(suffixName);
		//上传路径类型
		record.setFileType(V2Config.getIsstatic());
		if(tsysDatasMapper.insertSelective(record)>0)
		{
			return record.getId();
		}
		return null;
	}
	/**
	 * 文件上传文件存储到文件表
	 * @param record
	 * @param fileURL
	 * @return 主键
	 * @throws IOException
	 */
	public String saveImage(MultipartFile file) throws IOException {
		//文件上传获取文件名字
		String files_name = FileUploadUtils.upload(file);
		//相对路径——项目url请求路径
		String relative_filesURL=V2Config.getIsroot_dir()+files_name;
		//绝对路径-删除需要得路径
		String absolute_filesURL=null;


		if ("Y".equals(V2Config.getIsstatic())) {//项目路径
			absolute_filesURL=V2Config.getIsroot_dir()+files_name;
		}else {//磁盘路径
			absolute_filesURL=V2Config.getDefaultBaseDir()+files_name;
			//filesURL=V2Config.getIsroot_dir()+files;
		}

		String fileName=file.getOriginalFilename();
		// 获得文件后缀名称
		String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		if(StringUtils.isEmpty(suffixName)) {
			//如果没有后缀默认后缀
			suffixName=FileUploadUtils.IMAGE_JPG_EXTENSION;
		}

		return absolute_filesURL;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}

	@Override
	public int insertSelective(TsysDatas record) {
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		return tsysDatasMapper.insertSelective(record);
	}

	@Override
	public TsysDatas selectByPrimaryKey(String id) {
		
		return tsysDatasMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(TsysDatas record) {
		return tsysDatasMapper.updateByPrimaryKeySelective(record);
	}
	
	public int updateByPrimaryKey(TsysDatas record) {
		return tsysDatasMapper.updateByPrimaryKey(record);
	}

	
	@Override
	public int updateByExampleSelective(TsysDatas record, TsysDatasExample example) {
		
		return tsysDatasMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(TsysDatas record, TsysDatasExample example) {
		
		return tsysDatasMapper.updateByExample(record, example);
	}

	@Override
	public List<TsysDatas> selectByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.deleteByExample(example);
	}
	

	
}
