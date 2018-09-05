package com.bluemobi.controller.admin;
import java.util.HashMap;
import java.util.Map;

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

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.admin.AdminMenu;
import com.bluemobi.po.admin.AdminPermission;
import com.bluemobi.service.admin.AdminMenuService;
import com.bluemobi.service.admin.AdminPermissionService;
import com.bluemobi.to.ResultTO;



/**
 * 【权限表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Controller
@RequestMapping("adminPermission")
public class AdminPermissionController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPermissionController.class);
    
    @Autowired
    private AdminPermissionService adminPermissionService;
    
    @Autowired
    private AdminMenuService adminMenuService;
    
    
    
    /**
     * 进入【权限表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        
        //1、设置一级菜单下拉框
        model.addAttribute("firstMenus", adminMenuService.selectFirstMenus());
        
        return "admin/permission.index";
    }
    
    /**
     * 分页查询【权限表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(Model model, String key, int firstMenuId, int secondMenuId, int pageSize, int pageIndex) {
       
    	Map<String, Object> map = new HashMap<String, Object>();
        if(firstMenuId!=0)map.put("firstMenuId", firstMenuId);
        if(secondMenuId!=0)map.put("secondMenuId", secondMenuId);
        Page<Map<String, Object>> page = adminPermissionService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【权限表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer permissionId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("permissionId", permissionId); 
        model.addAttribute("adminPermission", adminPermissionService.selectObject(map));
        return "admin/permission.detail";
    }
    
    /**
     * 进入新增【权限表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("pid", 0); 
        model.addAttribute("firstMenus", adminMenuService.selectObjectList(map));
        
        return "admin/permission.edit";
    }
    
    /**
     * 新增【权限表】数据
     * @param adminPermission
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdminPermission(@ModelAttribute AdminPermission adminPermission, BindingResult result) {
        try {
            adminPermissionService.insert(adminPermission);
            LOGGER.info("用户【{}】添加权限表数据【{}】成功", new Object[] { this.getUserId(), adminPermission } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加权限表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminPermission, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【权限表】页面
     * @author haoj 309444359@qq.com
     * @date 2016-9-8 下午2:40:24
     * @param model
     * @param permissionId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer permissionId) {
        // 加载公共数据
        initIndex(model);
        
        //1、查询需要修改的权限数据
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("permissionId", permissionId); 
        AdminPermission adminPermission = adminPermissionService.selectObject(map1);
        model.addAttribute("adminPermission", adminPermission);
        
        //2、通过二级菜单id，获取一级菜单id
        int secondMenuId = adminPermission.getMenuId();
        AdminMenu secondAdminMenu = adminMenuService.selectFirstMenuBySecondMenuId(secondMenuId);
        int firstMenuId = secondAdminMenu.getPid();
        model.addAttribute("firstMenuId", firstMenuId);
        
        //3、设置一级菜单下拉框
        model.addAttribute("firstMenus", adminMenuService.selectFirstMenus());
        
        //4、设置二级菜单下拉框
        model.addAttribute("secondMenus", adminMenuService.selectSecondMenusByFirstMenuId(firstMenuId));
        
        return "admin/permission.edit";
    }
    
    /**
     * 修改【权限表】数据
     * @param adminPermission
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdminPermission(@ModelAttribute AdminPermission adminPermission, BindingResult result) {
        try {
            adminPermissionService.update(adminPermission);
            LOGGER.info("用户【{}】修改权限表数据【{}】成功", new Object[] { this.getUserId(), adminPermission } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改权限表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminPermission, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【权限表】数据
     * @param permissionId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdminPermission(Integer permissionId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("permissionId", permissionId); 
            adminPermissionService.delete(map);
            LOGGER.info("用户【{}】删除权限表数据【{}】成功", new Object[] { this.getUserId(), permissionId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除权限表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), permissionId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
