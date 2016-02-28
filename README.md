# AdminRiG

#Requirements

A webserver, such as Tomcat, is needed to run jarRig.war

#Installation

This instruction is based on configuring Tomcat v.7.0.68.
Download Tomcat v.7.0.68 and extract. Add new user by adding 
```xml
<tomcat-users>
  <role rolename="manager-gui"/>
	<user username="admin" password="admin" roles="manager-gui"/>
</tomcat-users>
```
