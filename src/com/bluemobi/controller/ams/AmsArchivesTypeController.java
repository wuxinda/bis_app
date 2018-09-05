package com.bluemobi.controller.ams;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.ams.AmsArchivesType;
import com.bluemobi.service.ams.AmsArchivesTypeService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.DateUtil;

/**
 * 【档案类型表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("amsArchivesType")
public class AmsArchivesTypeController extends AbstractBackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmsArchivesTypeController.class);

	@Autowired
	private AmsArchivesTypeService amsArchivesTypeService;

	/**
	 * 将请求参数中的字符串转换成日期格式
	 * 
	 * @param request
	 * @param binder
	 * @return string
	 * @author AutoCode
	 * @date 2016-11
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 请求参数中的日期字符串格式
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * 进入【档案类型表】主页
	 * 
	 * @param model
	 * @return string
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Model model) {
		// 加载公共数据
		initIndex(model);
		return "ams/archivesType.index";
	}

	/**
	 * 分页查询【档案类型表】
	 * 
	 * @param key
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @return Page<Map<String,Object>>
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	@ResponseBody
	public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		Page<Map<String, Object>> page = amsArchivesTypeService.page(map, pageIndex, pageSize);
		return page;
	}

	/**
	 * 查询【档案类型表】详情
	 * 
	 * @param model
	 * @return string
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(Model model, Integer archivesTypeId) {
		// 加载公共数据
		initIndex(model);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("archivesTypeId", archivesTypeId);
		model.addAttribute("amsArchivesType", amsArchivesTypeService.selectObject(map));
		return "ams/archivesType.detail";
	}

	/**
	 * 进入新增【档案类型表】页面
	 * 
	 * @param model
	 * @return string
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		// 加载公共数据
		initIndex(model);
		return "ams/archivesType.edit";
	}

	/**
	 * 新增【档案类型表】数据
	 * 
	 * @param amsArchivesType
	 * @return ResultTO
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO addAmsArchivesType(@ModelAttribute AmsArchivesType amsArchivesType, BindingResult result) {
		amsArchivesType.setCreator(this.getUserId());
		amsArchivesType.setCtime(DateUtil.getCurrentDate());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortOrder", amsArchivesType.getSortOrder());
			List<Map<String, Object>> amsArchivesTypes = amsArchivesTypeService.selectMapList(map);
			if (amsArchivesTypes.size() > 0) {
				return ResultTO.newFailResultTO("添加失败,code已存在", null);
			}
			amsArchivesTypeService.insert(amsArchivesType);
			LOGGER.info("用户【{}】添加档案类型表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesType });
		} catch (Exception e) {
			LOGGER.error("用户【{}】添加档案类型表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesType, e });
			return ResultTO.newFailResultTO("添加失败", null);
		}
		return ResultTO.newSuccessResultTO("添加成功", null);
	}

	/**
	 * 进入修改【档案类型表】页面
	 * 
	 * @param model
	 * @return string
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, Integer archivesTypeId) {
		// 加载公共数据
		initIndex(model);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("archivesTypeId", archivesTypeId);
		model.addAttribute("amsArchivesType", amsArchivesTypeService.selectObject(map));
		return "ams/archivesType.edit";
	}

	/**
	 * 修改【档案类型表】数据
	 * 
	 * @param amsArchivesType
	 * @return ResultTO
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO editAmsArchivesType(@ModelAttribute AmsArchivesType amsArchivesType, BindingResult result) {
		// 修改人
		amsArchivesType.setModifier(this.getUserId());
		// 修改时间
		amsArchivesType.setMtime(DateUtil.getCurrDate());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortOrder", amsArchivesType.getSortOrder());
			List<Map<String, Object>> amsArchivesTypes = amsArchivesTypeService.selectMapList(map);
			if (amsArchivesTypes.size() > 0) {
				return ResultTO.newFailResultTO("添加失败,code已存在", null);
			}
			amsArchivesTypeService.update(amsArchivesType);
			LOGGER.info("用户【{}】修改档案类型表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesType });
		} catch (Exception e) {
			LOGGER.error("用户【{}】修改档案类型表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesType, e });
			return ResultTO.newFailResultTO("更新失败", null);
		}
		return ResultTO.newSuccessResultTO("更新成功", null);
	}

	/**
	 * 删除【档案类型表】数据
	 * 
	 * @param archivesTypeId
	 * @return ResultTO
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO deleteAmsArchivesType(Integer archivesTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("archivesTypeId", archivesTypeId);
			amsArchivesTypeService.delete(map);
			LOGGER.info("用户【{}】删除档案类型表数据【{}】成功", new Object[] { this.getUserId(), archivesTypeId });
		} catch (Exception e) {
			LOGGER.error("用户【{}】删除档案类型表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), archivesTypeId, e });
			return ResultTO.newFailResultTO("删除失败", null);
		}
		return ResultTO.newSuccessResultTO("删除成功", null);
	}

	/**
	 * 获取类型列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectAllAmsArchivesType", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO getAmsArchivesType() {
		List<AmsArchivesType> list = amsArchivesTypeService.selectAmsArchivesType();
		return ResultTO.newSuccessResultTO("获取成功！", list);
	}
}
