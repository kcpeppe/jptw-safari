# DO NOT EDIT THIS FILE - See: https://eclipse.dev/jetty/documentation/

[description]
Enables use of the apache implementation of JSP.

[environment]
ee8

[depend]
ee8-servlet
ee8-annotations

[ini]
eclipse.jdt.ecj.version?=3.37.0
ee8.jsp.impl.version?=9.0.83.1

[lib]
lib/ee8-apache-jsp/org.eclipse.jdt.ecj-${eclipse.jdt.ecj.version}.jar
lib/ee8-apache-jsp/org.mortbay.jasper.apache-el-${ee8.jsp.impl.version}.jar
lib/ee8-apache-jsp/org.mortbay.jasper.apache-jsp-${ee8.jsp.impl.version}.jar
lib/jetty-ee8-apache-jsp-${jetty.version}.jar
