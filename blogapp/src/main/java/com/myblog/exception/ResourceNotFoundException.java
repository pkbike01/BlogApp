package com.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    //Here, I create the variable which i want to display on the ui;
    private String resourceName;
    private String fieldName;
    private long fieldValue;


    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));

        //it will give me (Post not found with id : 1)
        //String.format does /*(%s=resourceName),(%s=fieldName),(%s=fieldValue)*/
        this.resourceName = resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;

    }//it is setter.

    public String getResourceName() {

        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
//@ResponseStatus(value = HttpStatus.NOT_FOUND)
//public class ResourceNotFoundException {
//
//    //here, i need to create the variable which i want to display on ui;
//    private String resourceName;
//    private String fieldName;
//    private long fieldValue;
//
//
//    //now we need to create a constructor with args.
//    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue) {
//        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
//
//        //it will give me (Post not found with id : 1)
//        //String.format does /*(%s=resourceName),(%s=fieldName),(%s=fieldValue)*/
//        this.resourceName = resourceName;
//        this.fieldName=fieldName;
//        this.fieldValue=fieldValue;
//
//    }//it is setter.
//
//
//
//
//    public String getResourceName() {
//        return resourceName;
//    }
//
//
//    public String getFieldName() {
//        return fieldName;
//    }
//
//
//    public long getFieldValue() {
//        return fieldValue;
//    }
//
//
//
//
//}