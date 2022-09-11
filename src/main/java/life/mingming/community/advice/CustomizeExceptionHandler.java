package life.mingming.community.advice;

import com.alibaba.fastjson.JSON;
import life.mingming.community.dto.ResultDTO;
import life.mingming.community.exception.CustomizeErrorCode;
import life.mingming.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {//在这里区分是传输json形式的报错信息还是传递html也就是页面形式的报错
    @ExceptionHandler(Exception.class)
    public ModelAndView handle(HttpServletRequest request, Throwable ex, Model model , HttpServletResponse response)   {

        HttpStatus status = getStatus(request);
        String contentType=request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO=null;
            //返回Json
            if(ex instanceof CustomizeException){
                resultDTO=ResultDTO.errorOf((CustomizeException) ex);
            }else {
                resultDTO= ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        }else {
            //错误页面跳转
            if(ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");
        }

            }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status=null;
        if(code!=null){
            status = HttpStatus.resolve(code);
        }
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
