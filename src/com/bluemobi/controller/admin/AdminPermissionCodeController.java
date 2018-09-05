package com.bluemobi.controller.admin;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.admin.AdminPermissionCode;
import com.bluemobi.service.admin.AdminPermissionCodeService;
import com.bluemobi.to.ResultTO;



/**
 * 【权限表代码表，用于控制前端的按钮显示】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
@Controller
@RequestMapping("adminPermissionCode")
public class AdminPermissionCodeController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPermissionCodeController.class);
    
    @Autowired
    private AdminPermissionCodeService adminPermissionCodeService;
    

    
    /**
     * 进入【权限表代码表，用于控制前端的按钮显示】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "admin/permissionCode.index";
    }
    
    /**
     * 分页查询【权限表代码表，用于控制前端的按钮显示】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = adminPermissionCodeService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【权限表代码表，用于控制前端的按钮显示】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer permissionCodeId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("permissionCodeId", permissionCodeId); 
        model.addAttribute("adminPermissionCode", adminPermissionCodeService.selectObject(map));
        return "admin/permissionCode.detail";
    }
    
    /**
     * 进入新增【权限表代码表，用于控制前端的按钮显示】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "admin/permissionCode.edit";
    }
    
    /**
     * 新增【权限表代码表，用于控制前端的按钮显示】数据
     * @param adminPermissionCode
     * @return ResultTO
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdminPermissionCode(@ModelAttribute AdminPermissionCode adminPermissionCode, BindingResult result) {
        try {
            adminPermissionCodeService.insert(adminPermissionCode);
            this.refreshPermissionCodeMap();
            LOGGER.info("用户【{}】添加权限表代码表，用于控制前端的按钮显示数据【{}】成功", new Object[] { this.getUserId(), adminPermissionCode } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加权限表代码表，用于控制前端的按钮显示数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminPermissionCode, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【权限表代码表，用于控制前端的按钮显示】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer permissionCodeId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("permissionCodeId", permissionCodeId); 
        model.addAttribute("adminPermissionCode", adminPermissionCodeService.selectObject(map));
        return "admin/permissionCode.edit";
    }
    
    /**
     * 修改【权限表代码表，用于控制前端的按钮显示】数据
     * @param adminPermissionCode
     * @return ResultTO
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdminPermissionCode(@ModelAttribute AdminPermissionCode adminPermissionCode, BindingResult result) {
        try {
            adminPermissionCodeService.update(adminPermissionCode);
            this.refreshPermissionCodeMap();
            LOGGER.info("用户【{}】修改权限表代码表，用于控制前端的按钮显示数据【{}】成功", new Object[] { this.getUserId(), adminPermissionCode } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改权限表代码表，用于控制前端的按钮显示数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminPermissionCode, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【权限表代码表，用于控制前端的按钮显示】数据
     * @param permissionCodeId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdminPermissionCode(Integer permissionCodeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("permissionCodeId", permissionCodeId); 
            adminPermissionCodeService.delete(map);
            LOGGER.info("用户【{}】删除权限表代码表，用于控制前端的按钮显示数据【{}】成功", new Object[] { this.getUserId(), permissionCodeId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除权限表代码表，用于控制前端的按钮显示数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), permissionCodeId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    /**
     * 刷新权限代码map
     * @author haoj 309444359@qq.com
     * @date 2016-9-20 下午7:47:49
     */
    private void refreshPermissionCodeMap(){
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	Map<String,String> permissionCodeMap = adminPermissionCodeService.getPermissionCodeMap();
    	request.getServletContext().setAttribute("permissionCodeMap", permissionCodeMap);
    }
    
}
