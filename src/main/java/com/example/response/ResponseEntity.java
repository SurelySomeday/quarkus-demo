package com.example.response;

/**
 * @author yanxin
 * @Description:
 */
public class ResponseEntity <T>{
    public String code="0000";
    public String message="success";
    public T data;

    public ResponseEntity(){

    }

    public ResponseEntity(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseEntity(String code,String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public static ResponseEntity ok(Object data) {
        return new ResponseEntity<>(data);
    }

    public static ResponseEntity ok(String message, Object data) {
        return new ResponseEntity<>(message,data);
    }

    public static ResponseEntity unknownError(String message, Object data) {
        return new ResponseEntity<>(message,data);
    }

    public static ResponseEntity custom(String code,String message, Object data) {
        return new ResponseEntity<>(code,message,data);
    }


}
