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
import com.bluemobi.service.admin.AdminMenuService;
import com.bluemobi.to.ResultTO;



/**
 * 【菜单表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Controller
@RequestMapping("adminMenu")
public class AdminMenuController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminMenuController.class);
    
    @Autowired
    private AdminMenuService adminMenuService;
    

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
     * 进入【菜单表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {

        //1,查询所有菜单数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", "");
        map.put("status", 1);
        List<AdminMenu> firstMenus = adminMenuService.selectObjectList(map);
        //2,加载公共数据
        initIndex(model);
        model.addAttribute("firstMenus", firstMenus);
        return "admin/menu.index";
    }
    
    /**
     * 分页查询【菜单表】
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
    public Page<Map<String, Object>> page(String key, int menuId, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(menuId!=0)map.put("pid", menuId);
        Page<Map<String, Object>> page = adminMenuService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【菜单表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer menuId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("menuId", menuId); 
        model.addAttribute("adminMenu", adminMenuService.selectObject(map));
        return "admin/menu.detail";
    }
    
    /**
     * 进入新增【菜单表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        //选择一级分类下拉框
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("grade", 1); 
        model.addAttribute("adminMenus", adminMenuService.selectObjectList(map));
        
        return "admin/menu.edit";
    }
    
    /**
     * 新增【菜单表】数据
     * @param adminMenu
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdminMenu(@ModelAttribute AdminMenu adminMenu, BindingResult result) {
        try {
            adminMenuService.insertAdminMenu(adminMenu, this.getAdminUser());
            LOGGER.info("用户【{}】添加菜单表数据【{}】成功", new Object[] { this.getUserId(), adminMenu } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加菜单表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminMenu, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【菜单表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer menuId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuId", menuId); 
        model.addAttribute("adminMenu", adminMenuService.selectObject(map));
        
        //选择一级分类下拉框
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("grade", 1); 
        model.addAttribute("adminMenus", adminMenuService.selectObjectList(map2));
        
        return "admin/menu.edit";
    }
    
    /**
     * 修改【菜单表】数据
     * @param adminMenu
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdminMenu(@ModelAttribute AdminMenu adminMenu, BindingResult result) {
        try {
            adminMenuService.update(adminMenu);
            LOGGER.info("用户【{}】修改菜单表数据【{}】成功", new Object[] { this.getUserId(), adminMenu } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改菜单表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminMenu, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【菜单表】数据
     * @param menuId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdminMenu(Integer menuId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("menuId", menuId); 
            adminMenuService.delete(map);
            LOGGER.info("用户【{}】删除菜单表数据【{}】成功", new Object[] { this.getUserId(), menuId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除菜单表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), menuId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    /**
     * 通过父级菜单id获取自己菜单列表
     * @author haoj 309444359@qq.com
     * @date 2016-9-1 下午3:05:08
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping(value = "getAdminMenuByPid", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getAdminMenuByPid(Model model, Integer pid) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("pid", pid); 
        List<AdminMenu> list = adminMenuService.selectObjectList(map);
        return ResultTO.newSuccessResultTO("获取成功！", list);
    }
    
}
