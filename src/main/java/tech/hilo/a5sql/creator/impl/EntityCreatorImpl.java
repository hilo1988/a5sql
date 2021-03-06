package tech.hilo.a5sql.creator.impl;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import tech.hilo.a5sql.creator.EntityCreator;
import tech.hilo.a5sql.util.IoUtils;
import tech.hilo.a5sql.valueobject.Column;
import tech.hilo.a5sql.valueobject.EntityInfo;
import tech.hilo.a5sql.valueobject.JavaType;
import tech.hilo.a5sql.valueobject.Table;

import java.awt.image.PackedColorModel;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class EntityCreatorImpl implements EntityCreator {
	
	private static final String PACKAGE_FORMAT = "import %s;";
	
	private static final String TEMPLATE = IoUtils.readResourceFile("entityTemplate.txt");
	
	private final Table table;
	
	public EntityCreatorImpl(Table table) {
		this.table = table;
	}

	@Override
	public EntityInfo write(String packageName, String baseClassName) {
		String entityName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName());
		final String packages;
		String fields = createFields();

		final String extend;
		final String equalsAndHashCode;
		if (StringUtils.isNotBlank(baseClassName)) {
		    extend = String.format(" extends %s", baseClassName);
		    packages  = createPackages("lombok.EqualsAndHashCode");
            equalsAndHashCode = "@EqualsAndHashCode(callSuper = false)";
        } else {
            extend = " implements Serializable";
            packages = createPackages("java.io.Serializable");
            equalsAndHashCode = "";
        }
		
		String entity = TEMPLATE.replaceAll("\\{entityName\\}", entityName)
				.replaceAll("\\{imports\\}", packages)
				.replaceAll("\\{fields\\}", fields)
                .replaceAll("\\{extends\\}", extend)
                .replaceAll("\\{equalsAndHashCode\\}", equalsAndHashCode)
				.replaceAll("\\{tableName\\}", table.getName())
				.replaceAll("\\{package\\}", packageName);


        return new EntityInfo(entityName, entity, "java");
	}
	
	
	/**
	 * パッケージインポート作成
	 */
	private String createPackages(String... importPackages) {
		Set<String> packages = table.getColumns()
		.stream()
		.map(Column::getJavaType)
		.filter(type -> StringUtils.isNotBlank(type.getPackageName()))
		.map(JavaType::getPackageName)
		.collect(Collectors.toSet());

		table.getColumns()
                .stream()
                .filter(Column::isSerial)
                .forEach(c -> {
                    packages.add("javax.persistence.GeneratedValue");
                    packages.add("javax.persistence.GenerationType");
                });

        table.getColumns()
                .stream()
                .filter(Column::isId)
                .forEach(c -> packages.add("javax.persistence.Id"));



		if (ArrayUtils.isNotEmpty(importPackages)) {
			packages.addAll(Arrays.asList(importPackages));
        }

		return StringUtils.join(packages.stream()
                .distinct()
                .map(p -> String.format(PACKAGE_FORMAT, p))
                .iterator(), "\n");
		
	}
	
	private String createFields() {
		StringBuilder sb = new StringBuilder();
		table.getColumns()
		.forEach(c -> {
			sb.append(String.format("/** %s */\n", c.getComment()));
			if (c.isId()) {
				sb.append("@Id\n");
			}
			if (c.isSerial()) {
				sb.append("@GeneratedValue(strategy = GenerationType.AUTO)\n");
			}
			sb.append(c.getAnnotation());
			sb.append("\n");
			sb.append(c.getField());
			sb.append("\n\n");
		});
		
		List<String> tabList = Arrays.stream(sb.toString().split("\\n"))
			.map(line -> StringUtils.isBlank(line) ? "" : "\t".concat(line))
			.collect(Collectors.toList());
			
		return StringUtils.join(tabList, "\n");
	}
	
}
