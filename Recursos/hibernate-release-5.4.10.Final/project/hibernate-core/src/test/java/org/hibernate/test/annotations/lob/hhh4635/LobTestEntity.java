<<<<<<< HEAD
/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.test.annotations.lob.hhh4635;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table( name = "lob_test" )
public class LobTestEntity {

	@Id
	private Long id;
	
	@Lob
	private Blob lobValue;
	
	@Column( name = "qwerty", length = 4000 )
	private String qwerty;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLobValue(Blob lobValue) {
		this.lobValue = lobValue;
	}

	public Blob getLobValue() {
		return lobValue;
	}

	public void setQwerty(String qwerty) {
		this.qwerty = qwerty;
	}

	public String getQwerty() {
		return qwerty;
	}
	
}
=======
/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.test.annotations.lob.hhh4635;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table( name = "lob_test" )
public class LobTestEntity {

	@Id
	private Long id;
	
	@Lob
	private Blob lobValue;
	
	@Column( name = "qwerty", length = 4000 )
	private String qwerty;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLobValue(Blob lobValue) {
		this.lobValue = lobValue;
	}

	public Blob getLobValue() {
		return lobValue;
	}

	public void setQwerty(String qwerty) {
		this.qwerty = qwerty;
	}

	public String getQwerty() {
		return qwerty;
	}
	
}
>>>>>>> origin/master
