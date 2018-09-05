package com.bluemobi.to;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【图片信息】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class PicInfoTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//图片id
	private Integer id;
	//缩略图路径
	private String compressPath;
	//原图路径
	private String path;
	//宽
	private Integer wide;
	//高
	private Integer high;


    /**设置图片id*/
	public void setId(Integer id){
		this.id=id;
	}
	/**获取图片id*/
	public Integer getId(){
		return this.id;
	}
    /**设置缩略图路径*/
	public void setCompressPath(String compressPath){
		this.compressPath=compressPath;
	}
	/**获取缩略图路径*/
	public String getCompressPath(){
		return this.compressPath;
	}
    /**设置原图路径*/
	public void setPath(String path){
		this.path=path;
	}
	/**获取原图路径*/
	public String getPath(){
		return this.path;
	}
    /**设置宽*/
	public void setWide(Integer wide){
		this.wide=wide;
	}
	/**获取宽*/
	public Integer getWide(){
		return this.wide;
	}
    /**设置高*/
	public void setHigh(Integer high){
		this.high=high;
	}
	/**获取高*/
	public Integer getHigh(){
		return this.high;
	}


}
