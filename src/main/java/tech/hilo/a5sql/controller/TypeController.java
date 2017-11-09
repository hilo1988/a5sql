package tech.hilo.a5sql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.hilo.a5sql.form.TypeForm;

@Controller
@RequestMapping("type")
public class TypeController {

    @RequestMapping
    public String index() {
        return "type/list";
    }

    @RequestMapping("input")
    public String input() {
        return "type/input";
    }


    @RequestMapping("edit/{id}")
    public String edit(@PathVariable String id) {
        return "type/edit";
    }

    public String register(@ModelAttribute TypeForm form) {
        return "redirect:complete";
    }

    public String update(@ModelAttribute TypeForm form) {
        return "redirect:complete";
    }

    public String complete() {
        return "type/list";
    }
}
