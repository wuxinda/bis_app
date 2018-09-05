package com.bluemobi.controller.log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.log.LogUserImage;
import com.bluemobi.service.log.LogUserImageService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;



/**
 * 【】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
@Controller
@RequestMapping("logUserImage")
public class LogUserImageController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUserImageController.class);
    
    @Autowired
    private LogUserImageService logUserImageService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "log/userImage.index";
    }
    
    /**
     * 分页查询【】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = logUserImageService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", id); 
        model.addAttribute("logUserImage", logUserImageService.selectObject(map));
        return "log/userImage.detail";
    }
    
    /**
     * 进入新增【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "log/userImage.edit";
    }
    
    /**
     * 新增【】数据
     * @param logUserImage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addLogUserImage(@ModelAttribute LogUserImage logUserImage, BindingResult result) {
        try {
            logUserImageService.insert(logUserImage);
            LOGGER.info("用户【{}】添加数据【{}】成功", new Object[] { this.getUserId(), logUserImage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), logUserImage, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id); 
        model.addAttribute("logUserImage", logUserImageService.selectObject(map));
        return "log/userImage.edit";
    }
    
    /**
     * 修改【】数据
     * @param logUserImage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editLogUserImage(@ModelAttribute LogUserImage logUserImage, BindingResult result) {
        try {
            logUserImageService.update(logUserImage);
            LOGGER.info("用户【{}】修改数据【{}】成功", new Object[] { this.getUserId(), logUserImage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), logUserImage, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【】数据
     * @param id
     * @return ResultTO
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteLogUserImage(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("id", id); 
            logUserImageService.delete(map);
            LOGGER.info("用户【{}】删除数据【{}】成功", new Object[] { this.getUserId(), id });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), id, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
	 * 用户解锁失败图片上传
	 */
	@RequestMapping(value = "imageService")
	@ResponseBody
	public ResultTO  faceImageService(HttpServletRequest request,
			@RequestParam(value = "avatar", required = false) MultipartFile[] avatar) {
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		    Map<String, Object> result = new HashMap<String, Object>();
		    if(avatar!=null&&!avatar.equals("")) {
				Map<String, Object> uploadResultMap = uploadImage(avatar,
				BaseConstant.USER_AVATAR_IMAGE);
				Map<String, Object> image = new HashMap<String, Object>();
				//判断图片是否解析成功
				if((boolean)uploadResultMap.get("flag")) {
					Date date=new Date();
					image.put("date",date.getTime());
					image.put("imageUrl", uploadResultMap.get("imageUrl").toString());
					result.put("msg",image);
					LogUserImage logUserImage=new LogUserImage(); 
					logUserImage.setImageUrl(uploadResultMap.get("imageUrl").toString());
					logUserImage.setTime(date);
					logUserImage.setStoreAreaId("1");
					logUserImage.setStoreId(storeId);
					logUserImage.setLogMsg("密集架试图解锁");
					logUserImageService.insert(logUserImage);
				}else {
					result.put("msg","照片上传失败");
				}
		    }
			return ResultTO.newSuccessResultTO("发送成功", null);
	}
	  /**
     * 删除【】数据
     * @param id
     * @return ResultTO
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "deleteImg", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteImg(HttpServletRequest request) {
    	String imgUrlId = RequestParamUtil.getEncodeParam(request, "imgUrlId");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("id", imgUrlId); 
            logUserImageService.delete(map);
            LOGGER.info("用户【{}】删除数据【{}】成功", new Object[] { this.getUserId(), imgUrlId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), imgUrlId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
}
