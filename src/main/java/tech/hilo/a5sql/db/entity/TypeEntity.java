package tech.hilo.a5sql.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 型エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeEntity implements Serializable {

    private static final long serialVersionUID = -7199237829723891125L;

//    @Id
    private Long id;

    /** DB上の型 */
    private String dbType;

    /** 言語の型 */
    private String languageType;

    /** DBの種類 */
    private Integer dbKind;

    /** 言語の種類 */
    private Integer languageKind;
}
