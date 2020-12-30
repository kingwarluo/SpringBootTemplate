package com.kingwarluo.template.base.vo;


import java.io.Serializable;

/**
 * Result对象包含协议属性：
 * re:业务正常返回的消息体,每个服务使用专用Re对象，不可复用。属性命名规则同数据库命名。支持数据结构包装，例如：List<Re>，Set<Re>。
 * status:服务状态码，为int类型， 0 为成功，-1为业务异常（手机号格式不对，会员不存在等），-2为服务异常（sql语句异常等）。
 * code:业务语义状态码，用于做业务语义路由，和msg取其一设值即可。
 * msg:描述信息。用于返回业务异常信息和服务异常信息，不可用于设置日志堆栈信息，和code取其一设值即可。举例：业务信息： msg = "附近暂无司机" ，服务异常信息：msg="订单创建失败".
 *
 * @param <T>
 * @author
 * @since 2017年12月19日
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6029022489278479070L;

    /**
     * 成功
     */
    public final static int REMOTE_SUCCES_STATUS = 1;

    /**
     * 业务异常
     */
    public final static int REMOTE_BUSINESS_ERROR_STATUS = -1;

    /**
     * 服务异常
     */
    public final static int REMOTE_SERVICE_ERROR_STATUS = -2;

    /**
     * 服务状态码，为int类型， 1 为成功，-1为业务异常，-2为服务异常。
     */
    private int status = REMOTE_SUCCES_STATUS;
    /**
     * 描述信息。用于返回业务异常信息和服务异常信息，不可用于设置日志堆栈信息，和code取其一设值即可。举例：业务信息： msg = "附近暂无司机" ，服务异常信息：msg="订单创建失败".
     */
    private String msg;

    /**
     * 业务语义状态码，用于做业务语义路由，和msg取其一设值即可
     */
    private String code;

    /**
     * 业务正常返回的消息体,每个服务使用专用Re对象，不可复用。属性命名规则同数据库命名。支持数据结构包装，例如：List<Re>，Set<Re>。
     */
    private T data;

    /**
     * 异常堆栈信息，目前只取root和第一层异常信息
     */
    private String trace;

    public Result() {
    }

    /**
     * 得到服务异常Result.Result.msg字段值为"发生未知错误"
     */
    public static <T> Result<T> getServiceError() {
        return getServiceError("发生未知错误", null);
    }

    /**
     * 得到服务异常Result.
     * @param message 用于设置Result.msg字段
     * @param code    用于设置Result.code字段
     */
    public static <T> Result<T> getServiceError(String message, String code) {
        Result<T> result = new Result<T>();
        result.setStatus(REMOTE_SERVICE_ERROR_STATUS);
        result.setMsg(message);
        result.setCode(code);
        return result;
    }

    /**
     * 得到服务异常Result.
     * @param message 用于设置Result.msg字段
     * @param code    用于设置Result.code字段
     * @param trace    用于设置Result.trace字段
     */
    public static <T> Result<T> getServiceError(String message, String code, String trace) {
        Result<T> result = new Result<T>();
        result.setStatus(REMOTE_SERVICE_ERROR_STATUS);
        result.setMsg(message);
        result.setCode(code);
        result.setTrace(trace);
        return result;
    }

    /**
     * 得到正常结果.
     * @param re 正常结果
     */
    public static <T> Result<T> suc(T re) {
        return suc(re,"");
    }

    /**
     * 成功
     * @param re T 结果
     * @param msg String 消息
     * @author yiwei.zhou
     * @date 2019/5/31 19:52
     * @return
     */
    private static <T> Result<T> suc(T re,String msg) {
        return getResult(re,msg,REMOTE_SUCCES_STATUS);
    }

    /**
     * 成功
     * @param msg String
     * @author yiwei.zhou
     * @date 2019/5/31 19:52
     * @return
     */
//    public static <T> Result<T> suc(String msg) {
//        return suc(null,msg);
//    }

    /**
     * 业务失败
     * @param msg String 消息
     * @date 2019/5/31 19:46
     * @return
     */
    public static <T> Result<T> failBiz(String msg) {
        return getResult(null,msg,REMOTE_BUSINESS_ERROR_STATUS);
    }

    /**
     * 业务失败
     * @param re T 结果对象
     * @param msg String 消息
     * @date 2019/5/31 19:46
     * @return
     */
    public static <T> Result<T> failBiz(T re,String msg) {
        return getResult(re,msg,REMOTE_BUSINESS_ERROR_STATUS);
    }

    /**
     * 服务失败
     * @param re T 结果对象
     * @param msg String 消息
     * @date 2019/5/31 19:46
     * @return
     */
    public static <T> Result<T> failServer(T re,String msg) {
        return getResult(re,msg,REMOTE_SERVICE_ERROR_STATUS);
    }

    /**
     * 服务失败
     * @param msg String 消息
     * @date 2019/5/31 19:46
     * @return
     */
    public static <T> Result<T> failServer(String msg) {
        return getResult(null,msg,REMOTE_SERVICE_ERROR_STATUS);
    }

    /**
     * 返回结果
     * @param re T 结果对象
     * @param msg String 消息
     * @param status int 状态1 为成功，-1为业务异常，-2为服务异常
     * @author yiwei.zhou
     * @date 2019/5/31 19:45
     * @return
     */
    private static <T> Result<T> getResult(T re,String msg,int status) {
        Result<T> result = new Result<T>();
        result.setStatus(status);
        result.setData(re);
        result.setMsg(msg);
        return result;
    }

    /**
     * 设置正常结果.
     * @param re     正常结果
     * @param result 待设置对象
     */
    public static <T> void setSuccessResult(Result<T> result, T re) {
        result.setStatus(REMOTE_SUCCES_STATUS);
        result.setData(re);
    }

    /**
     * 得到业务异常Result.
     * @param msg  用于设置Result.msg字段
     * @param code 用于设置Result.code字段
     * @param trace    用于设置Result.trace字段
     */
    public static <T> Result<T> getBusinessException(String msg, String code, String trace) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setStatus(REMOTE_BUSINESS_ERROR_STATUS);
        result.setMsg(msg);
        result.setTrace(trace);
        return result;
    }

    /**
     * 得到业务异常Result.
     * @param msg  用于设置Result.msg字段
     * @param code 用于设置Result.code字段
     */
    public static <T> Result<T> getBusinessException(String msg, String code) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setStatus(REMOTE_BUSINESS_ERROR_STATUS);
        result.setMsg(msg);
        return result;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    /**
     * 请求是否成功，判断status值是否为{@link #REMOTE_SUCCES_STATUS}
     *
     * @return
     */
    public boolean isSuccess() {
        return getStatus() == Result.REMOTE_SUCCES_STATUS;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{")
                .append("status=").append(status)
                .append(", msg='").append(msg).append('\'')
                .append(", code='").append(code).append('\'')
                .append(", re=").append(data)
                .append(", trace='").append(trace).append('\'')
                .append('}');
        return sb.toString();
    }
}
