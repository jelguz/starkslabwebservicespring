package com.finastra.starkslab.webservice.provider;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.SizeLimitExceededException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.finastra.starkslab.webservice.model.User;

public class LDAPProvider {

    public User validateLogin(String id, String userPassword) {
    	User user = new User();
    	user.setId(id);
    	
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://manvswmisysdc01.misys.global.ad:389/DC=misys,DC=global,DC=ad");
        env.put(Context.REFERRAL, "follow");

        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "jelene.guzman@misys.com"); /// Change this to your email e.g. hommer.mallari@misys.com
        env.put(Context.SECURITY_CREDENTIALS, "yourPasswordHere");   ///Change this to your LAN/windows password

        DirContext ctx;
        try {
           ctx = new InitialDirContext(env);
        } catch (NamingException e) {
           throw new RuntimeException(e);
        }

        NamingEnumeration<SearchResult> results = null;

        try {
           SearchControls controls = new SearchControls();
           controls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Search Entire Subtree
           controls.setCountLimit(1);   //Sets the maximum number of entries to be returned as a result of the search
           controls.setTimeLimit(5000); // Sets the time limit of these SearchControls in milliseconds

           String searchString = "(&(objectCategory=user)(sAMAccountName=" + id + "))";

           results = ctx.search("", searchString, controls);

           if (results.hasMore()) {

               SearchResult result = (SearchResult) results.next();
               String dn = result.getNameInNamespace();
               
               env.put(Context.SECURITY_PRINCIPAL, dn);
               env.put(Context.SECURITY_CREDENTIALS, userPassword);
               
               new InitialDirContext(env); // Exception will be thrown on Invalid case
               
               user.setFirstName(getFirstName(result));
               user.setLastName(getLastName(result));
               user.setEmail(getEmail(result));
               user.setSuccessfulLogin(true);
               
               return user;
           } 
           else 
        	   user.setSuccessfulLogin(false);
               return user;

        } catch (AuthenticationException e) { // Invalid Login

        	user.setSuccessfulLogin(false);
            return user;
        } catch (NameNotFoundException e) { // The base context was not found.

        	user.setSuccessfulLogin(false);
            return user;
            
        } catch (SizeLimitExceededException e) {
        	
            throw new RuntimeException("LDAP Query Limit Exceeded, adjust the query to bring back less records", e);
        
        } catch (NamingException e) {
        	
           throw new RuntimeException(e);
           
        } finally {

           if (results != null) {
              try { results.close(); } catch (Exception e) { /* Do Nothing */ }
           }

           if (ctx != null) {
              try { ctx.close(); } catch (Exception e) { /* Do Nothing */ }
           }
        }
    }
    
    private String getLastName(SearchResult result) {
    	String[] a = result.getName().split(",");
        String lastName = a[0].substring(3, a[0].length()-1);
        return lastName;
    }
    
    private String getFirstName(SearchResult result) {
    	String[] a = result.getName().split(",");
        String firstName = a[1];
        return firstName;
    }
    
    private String getEmail(SearchResult result) {
    	String email = "";
    	Attributes attrs = result.getAttributes();
        @SuppressWarnings("rawtypes")
		NamingEnumeration e = attrs.getAll();
        Attribute attr1;
		try {
			attr1 = (Attribute) e.next();
			email = (String) attr1.get(0);
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		return email;
    }
}