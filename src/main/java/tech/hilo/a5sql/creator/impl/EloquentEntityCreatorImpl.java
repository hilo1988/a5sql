package tech.hilo.a5sql.creator.impl;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.dna.common.text.Inflector;
import tech.hilo.a5sql.creator.EntityCreator;
import tech.hilo.a5sql.util.IoUtils;
import tech.hilo.a5sql.valueobject.Column;
import tech.hilo.a5sql.valueobject.EntityInfo;
import tech.hilo.a5sql.valueobject.Table;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Eloquent(PHP laravelなど)用のエンティティ作成クラス
 */
public class EloquentEntityCreatorImpl implements EntityCreator {

    private final String TEMPLATE = IoUtils.readResourceFile("entityTemplate/eloquent.txt");

    private final Table table;

    public EloquentEntityCreatorImpl(Table table) {
        this.table = table;
    }

    @Override
    public EntityInfo write(String packageName, String baseClassName) {
        final String entityName = Inflector.getInstance().singularize(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName()));

        final String fillable = createFillable();

        String extend = "";
        if (StringUtils.isNotBlank(baseClassName)) {
            extend = String.format(" extends %s", baseClassName);
        }

        String entity = TEMPLATE.replace("{entityName}", entityName)
                .replace("{extends}", extend)
                .replace("{namespace}", packageName)
                .replace("{tableName}", table.getName())
                .replace("{fillable}", fillable);


        return new EntityInfo(entityName, entity, "php");
    }


    private String createFillable() {
        Iterator<String> it = table.getColumns().stream()
                .filter(c -> !c.isId())
                .map(c -> String.format("'%s'", c.getName()))
                .iterator();

        return StringUtils.join(it, ",\n");
    }
}
