package fr.ebiz.cdb.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * DefaultErrorHandler.
     *
     * @param req req
     * @param e   exception
     * @return ModelAndView
     * @throws Exception exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("500");
        return mav;
    }

    /**
     * NotFoundHandler.
     *
     * @return 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound() {
        return "404";
    }

}
