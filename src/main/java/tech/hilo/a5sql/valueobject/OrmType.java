package tech.hilo.a5sql.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.chrono.JapaneseChronology;
import java.util.Arrays;
import java.util.List;

/**
 * 言語タイプエンティティ
 */
@Data
public class OrmType {

    public static final int JPA = 1;

    public static final int ELOQUENT = 2;

    private final int id;

    private final String name;



    public static List<OrmType> getTypeList() {
        return Arrays.asList(new OrmType(JPA, "JPA"),
                new OrmType(ELOQUENT, "eloquent"));
    }

}
