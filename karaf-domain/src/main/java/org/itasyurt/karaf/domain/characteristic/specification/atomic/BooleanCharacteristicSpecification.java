package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.itasyurt.jsonize.annotations.JsonSubtype;

@Entity
@DiscriminatorValue("BCS")
@JsonSubtype
public class BooleanCharacteristicSpecification extends AtomicCharacteristicSpecification {

}
