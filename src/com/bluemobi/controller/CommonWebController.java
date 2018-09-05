package com.bluemobi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import com.bluemobi.to.ResultTO;

@Controller
@RequestMapping("")
public class CommonWebController extends AbstractBackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonWebController.class);
	/**
	 * 404错误页面
	 * 
	 * @author kevin
	 * @date 2015年12月14日下午1:40:43
	 * @version
	 * @param model
	 * @return
	 */
	@RequestMapping("404")
	public String pageNotFound(Model model) {
		initIndex(model);
		return "404";
	}

	/**
	 * 异常信息处理(目前只针对ajax的调用异常)
	 * 
	 * @author kevin
	 * @date 2015年12月14日下午1:47:44
	 * @version
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("error")
	@ResponseBody
	public ResultTO parseErrorMessage(HttpServletRequest request, HttpServletResponse response) {
		Exception ex = (Exception) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);
		LOGGER.error(ex.getMessage());
		return ResultTO.newFailResultTO(ex.getMessage(), null);
	}

	/**
	 * 登陆后的主页地址
	 * 
	 * @author HeWeiwen 2015-6-11
	 * @param model
	 * @return
	 */
	@RequestMapping("service")
	public String getContinue(Model model) {

		LOGGER.info("进入主页面...");

		// 加载公共数据
		initIndex(model);

		// 当月订单总数
		model.addAttribute("totalOrder", 12000);
		// 待发货订单
		model.addAttribute("sendOrder", 5600);
		// 待退货订单
		model.addAttribute("backOrder", 1600);
		// 待退款订单
		model.addAttribute("refundsOrder", 800);

		// 当月投诉总数
		model.addAttribute("totalComplaint", 1300);
		// 待回复投诉
		model.addAttribute("replyComplaint", 375);

		// 当月消息总数
		model.addAttribute("totalMessage", 2950);
		// 未读消息
		model.addAttribute("unreadMessage", 960);

		// 当前上架商品总数
		model.addAttribute("totalGoods", 200);

		// 当前入住商户总数
		model.addAttribute("totalMerchant", 300);

		return "index.index";
	}
	/**
	 * 首页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String index(HttpServletRequest request,Model model) {
		return "web/index";
	}
}
