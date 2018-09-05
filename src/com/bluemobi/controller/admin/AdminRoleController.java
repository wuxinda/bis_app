package com.bluemobi.controller.admin;
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
import com.bluemobi.po.admin.AdminMenu;
import com.bluemobi.po.admin.AdminRole;
import com.bluemobi.service.admin.AdminMenuService;
import com.bluemobi.service.admin.AdminRolePermissionService;
import com.bluemobi.service.admin.AdminRoleService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.admin.AdminMenuPermissionTO;



/**
 * 【角色表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Controller
@RequestMapping("adminRole")
public class AdminRoleController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRoleController.class);
    
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminMenuService adminMenuService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    
    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【角色表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "admin/role.index";
    }
    
    /**
     * 分页查询【角色表】
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
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = adminRoleService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【角色表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer roleId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("roleId", roleId); 
        model.addAttribute("adminRole", adminRoleService.selectObject(map));
        return "admin/role.detail";
    }
    
    /**
     * 进入新增【角色表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, Integer userType) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("userType",userType);
        return "admin/role.edit";
    }
    
    /**
     * 新增【角色表】数据
     * @param adminRole
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdminRole(@ModelAttribute AdminRole adminRole, BindingResult result) {
        try {
        	adminRole.setStatus(1);
        	adminRole.setCreator(this.getUserId());
        	adminRole.setCtime(new Date());
        	
            adminRoleService.insert(adminRole);
            LOGGER.info("用户【{}】添加角色表数据【{}】成功", new Object[] { this.getUserId(), adminRole } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加角色表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminRole, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【角色表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer roleId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId); 
        model.addAttribute("adminRole", adminRoleService.selectObject(map));
        return "admin/role.edit";
    }
    
    /**
     * 修改【角色表】数据
     * @param adminRole
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdminRole(@ModelAttribute AdminRole adminRole, BindingResult result) {
        try {
            adminRoleService.update(adminRole);
            LOGGER.info("用户【{}】修改角色表数据【{}】成功", new Object[] { this.getUserId(), adminRole } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改角色表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminRole, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【角色表】数据
     * @param roleId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdminRole(Integer roleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("roleId", roleId); 
            adminRoleService.delete(map);
            LOGGER.info("用户【{}】删除角色表数据【{}】成功", new Object[] { this.getUserId(), roleId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除角色表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), roleId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    
    /**
     * 进入设置权限页面
     * @author haoj 309444359@qq.com
     * @date 2016-9-10 下午7:28:53
     * @param model
     * @return
     */
    @RequestMapping(value = "rolePermission", method = RequestMethod.GET)
    public String rolePermission(Model model, Integer roleId, Integer menuId) {
        // 加载公共数据
        initIndex(model);
        
        //1、要修改的角色
        AdminRole adminRole = adminRoleService.selectObject(roleId);
        model.addAttribute("adminRole", adminRole);
        
        //2、加载一级菜单，按照一级菜单模块分配权限
        List<AdminMenu> firstMenus = adminMenuService.selectFirstMenus();
        model.addAttribute("firstMenus", firstMenus);
        
        //3、默认选中第一个一级菜单
        menuId = (menuId == null) ? firstMenus.get(0).getMenuId() : menuId;
        model.addAttribute("menuId", menuId);
        
        //4、查询角色菜单权限信息
        List<AdminMenuPermissionTO> menuPermissions = adminRolePermissionService.selectAdminMenuPermissionTOList(roleId, menuId);
        model.addAttribute("menuPermissions", menuPermissions);
        
        return "admin/rolePermission";
    }
    
    /**
     * 新增【角色权限表】数据
     * @param adminRolePermission
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "changeRolePermission", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO changeRolePermission(int roleId, int permissionId, int status) {
    	try {
            int result = adminRolePermissionService.changeRolePermission(roleId, permissionId, status);
            LOGGER.info("用户【{}】修改角色【{}】的权限成功，修改数据条数【{}】", new Object[] { this.getUserName(), roleId, result } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改角色【{}】的权限失败 Exception:【{}】", new Object[] { this.getUserName(), roleId, e });
            return ResultTO.newFailResultTO("修改权限失败", null);
        }
        return ResultTO.newSuccessResultTO("修改权限成功", null);
    }
    
    
}
