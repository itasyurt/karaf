package org.itasyurt.karaf.dao.common;

import org.itasyurt.karaf.domain.entity.VersionedEntity;

public interface VersionedEntityDao<T extends VersionedEntity> extends BaseDao<T> {

	T findByCode(String code);

}
