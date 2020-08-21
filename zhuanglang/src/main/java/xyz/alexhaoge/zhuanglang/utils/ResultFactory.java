package xyz.alexhaoge.zhuanglang.utils;


public class ResultFactory {

    private ResultFactory() {
        throw new IllegalStateException("ResultFactory cannot be instantiated");
      }

    public static Result buildSuccessResult(Object data) {
        return buildResult(200, "成功", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(400, message, null);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}