package tech.hilo.a5sql.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.hilo.a5sql.valueobject.OrmType;
import tech.hilo.a5sql.facade.A5SqlFacade;
import tech.hilo.a5sql.factory.Factory;
import tech.hilo.a5sql.form.A5SqlForm;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * インデックスコントローラ
 * Created by hilo on 2016/12/10.
 */
@Controller
public class IndexController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @RequestMapping()
    public String index(Model model) {
        model.addAttribute("ormList", OrmType.getTypeList());
        return "index";
    }

    @RequestMapping(value = "/csv", method = RequestMethod.POST)
    public void parseCsv(@ModelAttribute A5SqlForm form, HttpSession session, HttpServletResponse response) throws IOException{
        logger.debug(String.format("form:%s", form));
        logger.debug("form:%s", form);

        if (form.getZip() == null || form.getZip().isEmpty()) {
            // TODO
            throw new RuntimeException("zipがない");
        }

        A5SqlFacade facade = Factory.getA5SqlFacade(form, session);


        byte[] zip = facade.convert();
        response.setHeader("content-disposition",
                String.format("inline; filename=entities_%s.zip",
                        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
        try (OutputStream out = response.getOutputStream()) {
            out.write(zip);
            out.flush();
        }
    }
}
