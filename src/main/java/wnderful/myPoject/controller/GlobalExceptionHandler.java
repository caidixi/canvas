package wnderful.myPoject.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wnderful.myPoject.exception.InitializeException;
import wnderful.myPoject.exception.LoadException;
import wnderful.myPoject.exception.SaveException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=InitializeException.class)
    @ResponseBody
    public void initializeExceptionHandler()
    {
        System.out.println("文件夹初始化错误，请检查文件路径");
    }

    @ExceptionHandler(value=SaveException.class)
    @ResponseBody
    public void saveExceptionHandler()
    {
        System.out.println("文件存储错误");
    }

    @ExceptionHandler(value=LoadException.class)
    @ResponseBody
    public void loadExceptionHandler()
    {
        System.out.println("文件读取错误");
    }
}
