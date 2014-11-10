package org.itasyurt.karaf.core.characteristic.specification.atomic.combined;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.itasyurt.karaf.core.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.core.entity.BaseEntity;

@Entity
public class SpecOptionCombinedRelation extends BaseEntity {

	@ManyToOne
	private SpecOption primary;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	@OrderColumn(name = "ix")
	private List<SpecOptionRelatedOptions> relatedOptions = new ArrayList<SpecOptionRelatedOptions>();
}
