package tech.hilo.a5sql.creator;

import tech.hilo.a5sql.valueobject.EntityInfo;

public interface EntityCreator {

    EntityInfo write(String packageName, String baseClassName) ;
}

