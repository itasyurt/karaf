package org.itasyurt.karaf.domain.characteristic.specification.combined;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.entity.BaseEntity;

@Entity
public class SpecOptionCombinedRelation extends BaseEntity {

	@ManyToOne
	private SpecOption primary;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	@OrderColumn(name = "ix")
	private List<SpecOptionRelatedOptions> relatedOptions = new ArrayList<SpecOptionRelatedOptions>();

	@Override
	protected Object[] equalsFields() {
	
		return null;
	}

	@Override
	protected Object[] hashCodeFields() {
		return null;
	}
}
