# security_sample
This is a java based project to understand the top threats &amp; vulnerabilities and methods to prevent them.

Types of threats and vulnerabilities handled:
 
 SQL Injection:
    Attacker can trick a database server into running an arbitrary, unauthorized, unintended SQL query by piggybacking extra SQL 		
	elements on top of an existing, predefined query
   Prevention:
   Use Prepared Statements (Parameterized Queries) for making database calls from the application.
   Escape all User Supplied Input. 
   Have Positive or 'Whitelist" input validation in place. If not feasible at least have a “Blacklist” input validation in place. 

XPATH Injection:
  XPath Injections operate on web sites that uses user-supplied information to construct an XPath query for XML
  By sending intentionally malformed information into the web site, an attacker can find out how the XML data is structured, or access data that he may not normally have access to. He may even be able to elevate his privileges on the web site if the XML data is being used for authentication 
  Prevention:
  Escape the user input to make it safe to include in a dynamically constructed query

Cross-Site Scripting (XSS):
  XSS flaws occur when an application includes user supplied data in a page sent to the browser without properly validating or escaping that content. 
  Prevention:
   Properly escape all untrusted data based on the HTML context (body, attribute, JavaScript, CSS, XML, Json or URL) that the data will be placed into.  
    Make use of OWASP Java Encoder to address the same

Cross-Site Request Forgery (CSRF):
  A CSRF attack forces a logged-on victim’s browser to send a forged HTTP request, including the victim’s session cookie and any other automatically included authentication information, to a vulnerable web application
  Prevention:
  Implement Synchronizer Token Pattern: Have a unique token in a hidden field. This causes the value to be sent in the body of the HTTP request 
  Implement checking mechanism to verify that the token sent as part of request is valid. If token is not valid then don’t process on the request
  Implement CAPTCHA

Sensitive Data Exposure:
  Many web applications do not properly protect sensitive data, such as credit cards, tax IDs, and authentication credentials. 
  Attackers may steal or modify such weakly protected data to conduct credit card fraud, identity theft, or other crimes
  Sensitive data deserves extra protection such as encryption at rest or in transit, as well as special precautions when exchanged with the browser
  Prevention:
  Make sure you encrypt all sensitive data at rest and in transit in a manner that defends against these threats.
  Don’t store sensitive data unnecessarily. Discard it as soon as possible. Data you don’t have can’t be stolen.
  Ensure passwords are stored with an algorithm specifically designed for password protection, such as bcrypt, PBKDF2, or scrypt

      // Hash a password for the first time
	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

	// gensalt's log_rounds parameter determines the complexity
	// the work factor is 2**log_rounds, and the default is 10
	String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

	// Check that an unencrypted password matches one that has
	// previously been hashed
	if (BCrypt.checkpw(candidate, hashed))
		System.out.println("It matches");
	else
		System.out.println("It does not match");
    
    
