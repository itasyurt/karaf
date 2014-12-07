package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.itasyurt.jsonize.annotations.JsonSummary;
import org.itasyurt.karaf.domain.NamedEntity;
import org.itasyurt.karaf.domain.entity.VersionedEntity;
import org.itasyurt.karaf.domain.text.Text;

@MappedSuperclass
public abstract class CharacteristicSpecification extends VersionedEntity implements NamedEntity {



}
