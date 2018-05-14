package com.wemboo.boilerplate.filter;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;


import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapName;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;



/**
 * My Auth Realm
 * Author osh
 * 6.5.2018
 */
public class MyRealm extends AuthorizingRealm {

	/**
	 * Logger for the Class.
	 * TODO: Use thru CDI
	 */
	private final Logger log = LoggerFactory.getLogger(MyRealm.class);


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {

		log.info("doGetAuthorizationInfo: User principals:"+principals.toString());

		final String username = (String) getAvailablePrincipal(principals);
		final Set<String> roles = getRoles(username);
		final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roles);

		log.info("SimpleAuthorizationInfo: "+info.toString());
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
			throws AuthenticationException {
		log.info("doGetAuthenticationInfo: User authtoken: "+token.toString());


		SimpleAuthenticationInfo info = null;
		try {
			if (token instanceof UsernamePasswordToken) {
				final UsernamePasswordToken userToken = (UsernamePasswordToken) token;

				if (!executeLogin(userToken.getUsername(), new String(userToken.getPassword()))) {
					log.info("Invalid username or password.");
					throw new AuthenticationException("Unknown username " + userToken.getUsername() + " or password.");
				}

				log.info("Found user with username [{}]", token.getPrincipal());
				info = new SimpleAuthenticationInfo(userToken.getUsername(), userToken.getPassword(), getName());

			} else {
				throw new AuthenticationException("Unkown AuthenticationToken");
			}
		} catch (final Exception e) {
			log.debug(e.getMessage()); 
			throw new AuthenticationException(e);
		}
		return info;

	}



	/**
	 *  Check username and password
	 */
	public boolean executeLogin(String username, String passwd) {

		log.debug("Start Authentification");

		if(username == null || passwd == null){
			return false;
		}else if(username.equals("test") && passwd.equals("test")){
			return true;
		}
		return false;
	}

	/**
	 *  Get user roles
	 */
	private Set<String> getRoles(final String username) {

		log.debug("Get user roles");

		final Set<String> roles = new HashSet<String>();


		//TODO REMOVEME on the production env
		if (username.equals("test")) {
			roles.add("ADMIN");
		}

		return roles;
	}


	/**
	 * Gets the Permissions list
	 * @param username
	 * @return Set with all Permissions
	 */
	private Set<String> getUserPermissions(final String username) {

		//TODO
		final Set<String> stringPermission = new HashSet<String>();		
		return stringPermission;
	}

}
