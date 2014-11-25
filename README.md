karaf
=====
karaf(Characteristic Framework) is an Entity framework based on dynamic characteristics. It aims to enable a user(or a client API) to create entities with dynamic attributes(characteristic) and dynamic relations with other entities.

The core implementation is JPA based, integration with other parties will be through REST JSON services. The web application(not available yet) will be a Spring enabled Java Web Application that runs on Jetty(or another servlet container).

In addition to this karaf-framework acts as a incubation for some features(JPA Entity Cloning. Custom Json Serialization etc.)

The implementation is currently based on JPA, EclipseLink and Spring Framework. karaf-framework has several submodules:

*karaf-domain and karaf-core are the main modules for the Characteristic Framework Implementation is JPA 2.0 and EclipseLink based.
*jsonize is a lite JSON serialization/deserialization library that aims to convert karaf entities from/to JSON
*util is a utility module that is aimed to include several general-purpose implementations.
karaf 
