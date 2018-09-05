package com.bluemobi.to;

import com.appcore.model.AbstractObject;
import com.bluemobi.constant.BaseConstant;

/**
 * @Description 返回结果传输对象 后台管理 以及 手机接口 都采用改对象返回结果
 * status  0成功  1失败 2session过期， 由于公司历史遗留问题，为了跟前端以及php保持一致，这里暂时使用0成功1失败。
 *         状态统一使用BaseConstant里面的STATUS_SUCCESS、STATUS_FAILURE和STATUS_TOKEN_INVALID便于后期修改
 * msg  返回附加消息
 * data 返回数据体
 * @author haojian 309444359@qq.com
 * @date 2015-11-8 下午12:24:17
 * 
 */
public class ResultTO extends AbstractObject {

    private static final long serialVersionUID = 1L;
    //返回状态  0成功  1失败 2session过期，状态统一使用BaseConstant里面的STATUS_SUCCESS、STATUS_FAILURE和STATUS_TOKEN_INVALID
    private int status;
    //附加消息
    private String msg;
    //数据体
    private Object data;

    public ResultTO() {

    }

    private ResultTO(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    /**
     * 成功，且无特殊附加消息时使用
     * @author haojian
     * @date 2015-11-8 下午12:33:36 
     * @param data
     * @return
     * @return ResultTO
     */
    public static ResultTO newSuccessResultTO(Object data) {
        return ResultTO.newSuccessResultTO("success", data);
    }
    /**
     * 失败，且无特殊附加消息时使用
     * @author haojian
     * @date 2015-11-8 下午12:34:16 
     * @param data
     * @return
     * @return ResultTO
     */
    public static ResultTO newFailResultTO(Object data) {
        return ResultTO.newFailResultTO("failure", data);
    }
    /**
     * session过期，且无特殊附加消息时使用
     * @author haojian
     * @date 2015-11-8 下午12:34:22 
     * @param data
     * @return
     * @return ResultTO
     */
    public static ResultTO newSessionInvalidResultTO(Object data) {
        return ResultTO.newSessionInvalidResultTO("session invalid", data);
    }
    /**
     * 成功 时候使用
     * @author haojian
     * @date 2015-11-8 下午12:35:22 
     * @param msg
     * @param data
     * @return
     * @return ResultTO
     */
    public static ResultTO newSuccessResultTO(String msg, Object data) {
        return new ResultTO(BaseConstant.STATUS_SUCCESS, msg, data);
    }
    /**
     * 失败时候使用
     * @author haojian
     * @date 2015-11-8 下午12:35:43 
     * @param msg
     * @param data
     * @return
     * @return ResultTO
     */
    public static ResultTO newFailResultTO(String msg, Object data) {
        return new ResultTO(BaseConstant.STATUS_FAILURE, msg, data);
    }

    /**
     * session 过期时候使用
     * @author haojian
     * @date 2015-11-8 下午12:35:56 
     * @param msg
     * @param data
     * @return
     * @return ResultTO
     */
    public static ResultTO newSessionInvalidResultTO(String msg, Object data) {
        return new ResultTO(BaseConstant.STATUS_SESSION_INVALID, msg, data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String error) {
        this.msg = error;
    }

    public Object getData() {
        if(data==null){
            data = "{}";
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
