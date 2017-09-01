# OSGL MVC

[![APL v2](https://img.shields.io/badge/license-Apache%202-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) 
[![Maven Central](https://img.shields.io/maven-central/v/org.osgl/genie.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22genie%22)
[![Build Status](https://travis-ci.org/osglworks/java-di.svg?branch=master)](https://travis-ci.org/osglworks/java-di)
[![codecov](https://codecov.io/gh/osglworks/java-di/branch/master/graph/badge.svg)](https://codecov.io/gh/osglworks/java-unit)
[![Javadocs](http://www.javadoc.io/badge/org.osgl/genie.svg?color=red)](http://www.javadoc.io/doc/org.osgl/osgl-ut)

OSGL MVC is a library provides MVC (Model/View/Controller) framework facilities:

* Result classes
    
    The classes that encapsulates HTTP response types including HTML, plain text, JSON, adhoc content and typical error responses like `404 Not Found`, `400 Bad Request` etc
    
* Annotations include

    - annotations mark an action handler with URL path and HTTP method specification
    - interceptor annotations like `Before`, `After` etc
    - Bind annotations that mark a binder to a request parameter
    - Resolve annotation that specify how to resolve a string value to a parameter type
    
* Utilities
    
    - An abstract Binder class as base for Binder implementation
    
* SPI/bridge classes/interfaces
    
    - ParamValueProvider - allow framework to provides a way to allow mvc library to access request parameters of current session context
    - ErrorPageRender - allow framework to inject logic to render an error page
    - MvcConfig - allow framework to customize a certain aspect of mvc library, e.g. specify the error page render implementation; customize JSON serialization etc 
