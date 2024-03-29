<<<<<<< HEAD
/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.test.mapping;

import java.io.Serializable;



public class UserConfId  implements Serializable{
	
	private static final long serialVersionUID = -161134972658451944L;

	private Long user;

	private ConfId conf;
	
	public UserConfId(){
	}

	public UserConfId(Long user, ConfId conf) {
		this.user = user;
		this.conf = conf;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}


	public ConfId getConf() {
		return conf;
	}

	public void setConf(ConfId conf) {
		this.conf = conf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conf == null) ? 0 : conf.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserConfId other = (UserConfId) obj;
		if (conf == null) {
			if (other.conf != null)
				return false;
		} else if (!conf.equals(other.conf))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
=======
/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.test.mapping;

import java.io.Serializable;



public class UserConfId  implements Serializable{
	
	private static final long serialVersionUID = -161134972658451944L;

	private Long user;

	private ConfId conf;
	
	public UserConfId(){
	}

	public UserConfId(Long user, ConfId conf) {
		this.user = user;
		this.conf = conf;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}


	public ConfId getConf() {
		return conf;
	}

	public void setConf(ConfId conf) {
		this.conf = conf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conf == null) ? 0 : conf.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserConfId other = (UserConfId) obj;
		if (conf == null) {
			if (other.conf != null)
				return false;
		} else if (!conf.equals(other.conf))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
>>>>>>> origin/master
}