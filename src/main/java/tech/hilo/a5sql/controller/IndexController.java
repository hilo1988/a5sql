package tech.hilo.a5sql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.hilo.a5sql.form.A5SqlForm;

import javax.servlet.http.HttpServletResponse;

/**
 * インデックスコントローラ
 * Created by hilo on 2016/12/10.
 */
@Controller
public class IndexController {

    @RequestMapping()
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/csv", method = RequestMethod.POST, produces = "application/zip")
    public void parseCsv(@ModelAttribute A5SqlForm form, HttpServletResponse response) {

    }
}
