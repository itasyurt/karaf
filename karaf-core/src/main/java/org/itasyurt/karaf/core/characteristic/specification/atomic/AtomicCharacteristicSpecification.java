package org.itasyurt.karaf.core.characteristic.specification.atomic;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AtomicCharacteristicSpecification extends CharacteristicSpecification {

}
