package de.ar.rgk.model.abstracts;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class HibernateDataObject implements IHibernateDataObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;

	@Version
	@Column(name = "version")
	private int version;

	@Override
	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@SuppressWarnings("unused")
	private void setVersion(int version) {
		this.version = version;
	}

	protected HibernateDataObject() {
		// Muss bleiben wegen Hibernate
	}
}