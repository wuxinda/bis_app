package com.bluemobi.controller.system;
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
import com.bluemobi.po.system.SystemBank;
import com.bluemobi.service.system.SystemBankService;
import com.bluemobi.to.ResultTO;



/**
 * 【银行基础数据，银行编号，银行名称】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-10
 * 
 */
@Controller
@RequestMapping("systemBank")
public class SystemBankController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemBankController.class);
    
    @Autowired
    private SystemBankService systemBankService;
    

    
    /**
     * 进入【银行基础数据，银行编号，银行名称】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "system/bank.index";
    }
    
    /**
     * 分页查询【银行基础数据，银行编号，银行名称】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = systemBankService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【银行基础数据，银行编号，银行名称】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", id); 
        model.addAttribute("systemBank", systemBankService.selectObject(map));
        return "system/bank.detail";
    }
    
    /**
     * 进入新增【银行基础数据，银行编号，银行名称】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "system/bank.edit";
    }
    
    /**
     * 新增【银行基础数据，银行编号，银行名称】数据
     * @param systemBank
     * @return ResultTO
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addSystemBank(@ModelAttribute SystemBank systemBank, BindingResult result) {
        try {
            systemBankService.insert(systemBank);
            LOGGER.info("用户【{}】添加银行基础数据，银行编号，银行名称数据【{}】成功", new Object[] { this.getUserId(), systemBank } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加银行基础数据，银行编号，银行名称数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), systemBank, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【银行基础数据，银行编号，银行名称】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id); 
        model.addAttribute("systemBank", systemBankService.selectObject(map));
        return "system/bank.edit";
    }
    
    /**
     * 修改【银行基础数据，银行编号，银行名称】数据
     * @param systemBank
     * @return ResultTO
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editSystemBank(@ModelAttribute SystemBank systemBank, BindingResult result) {
        try {
            systemBankService.update(systemBank);
            LOGGER.info("用户【{}】修改银行基础数据，银行编号，银行名称数据【{}】成功", new Object[] { this.getUserId(), systemBank } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改银行基础数据，银行编号，银行名称数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), systemBank, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【银行基础数据，银行编号，银行名称】数据
     * @param id
     * @return ResultTO
     * @author AutoCode
     * @date 2016-10
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteSystemBank(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("id", id); 
            systemBankService.delete(map);
            LOGGER.info("用户【{}】删除银行基础数据，银行编号，银行名称数据【{}】成功", new Object[] { this.getUserId(), id });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除银行基础数据，银行编号，银行名称数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), id, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
