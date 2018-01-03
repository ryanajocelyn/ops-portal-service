/**
 * 
 */
package com.cognizant.pmo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * @author 238209
 *
 */
@Configuration
public class AdLdapConfiguration {
	
	@Value("${ldap.url}")
	private String ldapUrl;
	
	@Value("${ldap.base}")
	private String ldapBase;
	
	@Value("${ldap.user}")
	private String ldapUser;
	
	@Value("${ldap.password}")
	private String ldapPass;
	
	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(ldapUrl);
		//contextSource.setBase(ldapBase);
		contextSource.setUserDn(ldapUser);
		contextSource.setPassword(ldapPass);		
		
		contextSource.afterPropertiesSet();
		
		return contextSource;
	}
	
	@Bean
	public LdapTemplate ldapTemplate(LdapContextSource contextSource) {
		return new LdapTemplate(contextSource);
	}
}
