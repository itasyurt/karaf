package org.itasyurt.karaf.domain.characteristic.specification.combined;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSummary;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.entity.BaseEntity;

@Entity
public class SpecOptionCombinedRelation extends BaseEntity {

	@ManyToOne
	@JsonSummary
	private SpecOption primary;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	@OrderColumn(name = "ix")
	@JsonDetail
	private List<SpecOptionRelatedOptions> relatedOptions = new ArrayList<SpecOptionRelatedOptions>();

	
	
	public SpecOption getPrimary() {
		return primary;
	}

	public void setPrimary(SpecOption primary) {
		this.primary = primary;
	}

	public List<SpecOptionRelatedOptions> getRelatedOptions() {
		return relatedOptions;
	}

	public void setRelatedOptions(List<SpecOptionRelatedOptions> relatedOptions) {
		this.relatedOptions = relatedOptions;
	}

	@Override
	protected Object[] equalsFields() {
	
		return null;
	}

	@Override
	protected Object[] hashCodeFields() {
		return null;
	}
}
