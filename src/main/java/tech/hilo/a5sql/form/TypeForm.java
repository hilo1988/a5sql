package tech.hilo.a5sql.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class TypeForm implements Serializable {

    private static final long serialVersionUID = 5161669311298000438L;

    private Integer id;

    private String javaType;

    private String sqlType;
}
