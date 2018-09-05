package com.bluemobi.controller.device;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.bluemobi.po.device.DeviceBrand;
import com.bluemobi.po.device.DeviceCategory;
import com.bluemobi.po.device.DeviceCategoryBrand;
import com.bluemobi.service.device.DeviceBrandService;
import com.bluemobi.service.device.DeviceCategoryBrandService;
import com.bluemobi.service.device.DeviceCategoryService;
import com.bluemobi.to.ResultTO;

/**
 * 【设备品牌表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("deviceBrand")
public class DeviceBrandController extends AbstractBackController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeviceBrandController.class);

	@Autowired
	private DeviceBrandService deviceBrandService;
	@Autowired
	private DeviceCategoryBrandService deviceCategoryBrandService;
	@Autowired
	private DeviceCategoryService deviceCategoryService;

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
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 请求参数中的日期字符串格式
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * 进入【设备品牌表】主页
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
		return "device/brand.index";
	}

	/**
	 * 分页查询【设备品牌表】
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
	public Page<Map<String, Object>> page(String key, int pageSize,
			int pageIndex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		Page<Map<String, Object>> page = deviceBrandService.page(map,
				pageIndex, pageSize);
		return page;
	}

	/**
	 * 查询【设备品牌表】详情
	 * 
	 * @param model
	 * @return string
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(Model model, Integer brandId) {
		// 加载公共数据
		initIndex(model);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("brandId", brandId);
		model.addAttribute("deviceBrand", deviceBrandService.selectObject(map));
		return "device/brand.detail";
	}

	/**
	 * 进入新增【设备品牌表】页面
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
		return "device/brand.edit";
	}

	/**
	 * 新增【设备品牌表】数据
	 * 
	 * @param deviceBrand
	 * @return ResultTO
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO addDeviceBrand(@ModelAttribute DeviceBrand deviceBrand,
			BindingResult result) {
		try {
        	//设置创建人，创建时间
			deviceBrand.setCreator(this.getUserId());
        	deviceBrand.setCtime(new Date());
			deviceBrandService.insert(deviceBrand);
			LOGGER.info("用户【{}】添加设备品牌表数据【{}】成功",
					new Object[] { this.getUserId(), deviceBrand });
		} catch (Exception e) {
			LOGGER.error("用户【{}】添加设备品牌表数据【{}】失败 Exception:【{}】", new Object[] {
					this.getUserId(), deviceBrand, e });
			return ResultTO.newFailResultTO("添加失败", null);
		}
		return ResultTO.newSuccessResultTO("添加成功", null);
	}

	/**
	 * 进入修改【设备品牌表】页面
	 * 
	 * @param model
	 * @return string
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Model model, Integer brandId) {
		// 加载公共数据
		initIndex(model);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandId);
		model.addAttribute("deviceBrand", deviceBrandService.selectObject(map));
		return "device/brand.edit";
	}

	/**
	 * 修改【设备品牌表】数据
	 * 
	 * @param deviceBrand
	 * @return ResultTO
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO editDeviceBrand(@ModelAttribute DeviceBrand deviceBrand,
			BindingResult result) {
		try {
        	//设置修改人和修改时间
			deviceBrand.setModifier(this.getUserId());
			deviceBrand.setMtime(new Date());
			deviceBrandService.update(deviceBrand);
			LOGGER.info("用户【{}】修改设备品牌表数据【{}】成功",
					new Object[] { this.getUserId(), deviceBrand });
		} catch (Exception e) {
			LOGGER.error("用户【{}】修改设备品牌表数据【{}】失败 Exception:【{}】", new Object[] {
					this.getUserId(), deviceBrand, e });
			return ResultTO.newFailResultTO("更新失败", null);
		}
		return ResultTO.newSuccessResultTO("更新成功", null);
	}

	/**
	 * 删除【设备品牌表】数据
	 * 
	 * @param brandId
	 * @return ResultTO
	 * @author AutoCode
	 * @date 2016-11
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO deleteDeviceBrand(Integer brandId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("brandId", brandId);
			deviceBrandService.delete(map);
			LOGGER.info("用户【{}】删除设备品牌表数据【{}】成功",
					new Object[] { this.getUserId(), brandId });
		} catch (Exception e) {
			LOGGER.error("用户【{}】删除设备品牌表数据【{}】失败 Exception:【{}】", new Object[] {
					this.getUserId(), brandId, e });
			return ResultTO.newFailResultTO("删除失败", null);
		}
		return ResultTO.newSuccessResultTO("删除成功", null);
	}

	/**
	 * 品牌绑定设备分类页面跳转
	 * @param model
	 * @param brandId
	 * @return
	 */
	@RequestMapping(value = "bindCategoryJump", method = RequestMethod.GET)
	public String bindCategoryJump(Model model, Integer brandId) {
		// 加载公共数据
		initIndex(model);
		Map<String, Object> mapTemp = new HashMap<String, Object>();
		mapTemp.put("brandId", brandId);
		// 查询当前品牌对应的设备分类id
		List<DeviceCategoryBrand> categoryBrandList = deviceCategoryBrandService
				.selectObjectList(mapTemp);
		// 当前品牌已选择的设备对象集合
		List<DeviceCategory> categoryList = new ArrayList<DeviceCategory>();
		Map<String, Object> mapTmp = new HashMap<String, Object>();
		for (DeviceCategoryBrand categoryBrand : categoryBrandList) {
			mapTmp = new HashMap<String, Object>();
			mapTmp.put("categoryId", categoryBrand.getCategoryId());
			DeviceCategory deviceCategory = deviceCategoryService
					.selectObject(mapTmp);
			categoryList.add(deviceCategory);
		}
		//已选择设备分类集合
		model.addAttribute("categories", categoryList);
		//品牌id
		model.addAttribute("brandId", brandId);
		return "device/brand.bindCategory";
	}

	/**
	 * 品牌绑定设置分类
	 * @param categoryIds
	 * @param brandId
	 * @return
	 */
	@RequestMapping(value = "bindCategory", method = RequestMethod.POST)
	public ResultTO bindCategory(String categoryIds, Integer brandId) {
		try {
			//更新绑定
			deviceBrandService.updateCategoryProperty(categoryIds, brandId);
			LOGGER.info("用户【{}】绑定设备分类数据【{}】成功", new Object[] {
					this.getUserId(), brandId, categoryIds });
		} catch (Exception e) {
			LOGGER.error("用户【{}】绑定设备分类【{}】失败 Exception:【{}】", new Object[] {
					this.getUserId(), brandId, categoryIds, e });
			return ResultTO.newFailResultTO("设备分类绑定属性失败", null);
		}
		return ResultTO.newSuccessResultTO("设备分类绑定属性成功", null);
	}

}
