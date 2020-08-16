package cn.dreamchan.common.core.biz;


/**
 * 通用返回结果
 *
 * @author DreamChan
 */
public class R {

	private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";
	private static final String DEFAULT_FAILURE_MESSAGE = "操作失败";
	private static final boolean DEFAULT_SUCCESS = true;
	private static final boolean DEFAULT_FAILURE = false;


	public static ResObject success() {
		return new ResObject(ResCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS, DEFAULT_SUCCESS_MESSAGE);
	}


	public static ResObject success(Object data) {
		ResObject resObject = new ResObject(ResCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS, DEFAULT_SUCCESS_MESSAGE);
		resObject.setData(data);
		return resObject;
	}

	public static ResObject success(String message) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.SUCCESS.getCode());
		resObject.setIsSuccess(DEFAULT_SUCCESS);
		resObject.setMsg(message);
		return resObject;
	}

	public static ResObject success(String message, Object data) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.SUCCESS.getCode());
		resObject.setIsSuccess(DEFAULT_SUCCESS);
		resObject.setMsg(message);
		resObject.setData(data);
		return resObject;
	}

	public static ResObject failure() {
		return new ResObject(ResCodeEnum.FAILURE.getCode(), DEFAULT_FAILURE, DEFAULT_FAILURE_MESSAGE);
	}

	public static ResObject failure(ResCodeEnum resCodeEnum) {
		ResObject resObject = new ResObject();
		resObject.setCode(resCodeEnum.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(resCodeEnum.getMsg());
		return resObject;
	}

	public static ResObject failure(String message) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.FAILURE.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(message);
		return resObject;
	}


	public static ResObject failure(ResCodeEnum resCodeEnum, String message) {
		ResObject resObject = new ResObject();
		resObject.setCode(resCodeEnum.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(message);
		return resObject;
	}

	public static ResObject toRes(int rows){
		return rows > 0 ? success() : failure();
	}

	public static ResObject toRes(boolean res){
		return res == true ? success() : failure();
	}

}
