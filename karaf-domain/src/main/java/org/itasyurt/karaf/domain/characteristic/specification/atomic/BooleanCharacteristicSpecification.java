package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BCS")
public class BooleanCharacteristicSpecification extends AtomicCharacteristicSpecification {

}
