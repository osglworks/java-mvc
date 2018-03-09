# OSGL MVC CHANGELOG

1.5.1 10/Mar/2018
- Make `RenderText` output encoding (charset) by default #22

1.5.0 4/Mar/2018
- update to osgl-tool-1.7.0
- update to osgl-http-1.4.0
- Allow stream based output to response #21
- Allow developer to attach any payload to Error response #20

1.4.0
- update to osgl-tool-1.5

1.3.0
- It shall use `MvcConfig.jsonMediaTypeProvider()` for RenderJson singleton #19
- Introduce quality control into the project #18
- Add tag annotation to allow developer creating their own `@Bind` annotation #17 
- Allow developer to set error code on `ErrorResult` #13 
- Missing API in some error result to accept Throwable type parameter #12 

1.2.0
- Add corresponding Result classes for more 4xx and 5xx responses #11
- Add API to `Result` for header and cookie manipulation #10
- Support different semantic for new redirection responses
- update osgl-http to 1.1.3

1.1.1
- Add WsAction #7 
- Catch up to osgl-http 1.0.5

1.0.7
- Catch up to osgl-http 1.0.4

1.0.6
- Localized error message not translated #6 

1.0.5
- update osgl-http dependency to 1.0.3

1.0.4
- ensure response is always commited #5 

1.0.3
- take out version range. See https://issues.apache.org/jira/browse/MNG-3092
- Allow `@With` to be used on method

1.0.2
- #4 IE does not support application/json content

1.0.1
- Fix #3 Make it possible to output charset encoding for JSON result

1.0.0
- baseline from 0.9

0.9.0-SNAPSHOT
- add PatchAction annotation
- allow RenderContent sub class to pass H.Status parameter into constructor
- Deprecate NoResult, add NoContent to replace it

0.8.0-SNAPSHOT
- update to tool 0.10.0
- update to http 0.5.0

0.7.0-SNAPSHOT
- update osgl-http to 0.4.0-SNAPSHOT

0.6.1-SNAPSHOT
- update osgl-http to 0.3.4-SNAPSHOT

0.6.0-SNAPSHOT
- Bind annotation now accept an array of Binder implementations
- Add ActionUtil annotation
- Support for localized Error Result message
- Improve result throw/catch flow performance by introducing general static result mechanism

0.5.1-SNAPSHOT
- Update osgl-http from 0.3.0-SNAPSHOT to 0.3.1-SNAPSHOT

0.5.0-SNAPSHOT
- Bind interface change: accept bean as session cached data
- Add SessionFree annotation

0.4.2-SNAPSHOT
- Add Created and Accepted result
- Add ResponseStatus annotation

0.4.1-SNAPSHOT
- Add MethodNotAllowed and NotImplemented Result type

0.4.0-SNAPSHOT
- update osgl-tool to 0.8

0.3.3-SNAPSHOT
- update osgl-tool to 0.7.1-SNAPSHOT

0.3.2-SNAPSHOT
- Fix Unauthorized result issue
- Add public access method to RenderContent for testing purpose
- Allow @Action annotation to be used without value specified, in which case
  it is used as a marker of method subject to bytecode enhancement by the
  underline framework

0.3.1-SNAPSHOT
- fix RenderText constructor treat arg as arg array issue

0.3.0-SNAPSHOT
- Binder now expose parameter from a ParameterValueProvider interface instead of H.Request
- Update Action annotations signature to allow an action method be mapped to multiple paths at the same time

0.2.4-SNAPSHOT
- Update ogl-http version
- ErrorResult constructor now is public
- add INSTANCE to BadRequest

0.2.3-SNAPSHOT
- Update osgl-tool and osgl-http version

0.2.2-SNAPSHOT
- Add pass accept parameter to RenderText

0.2.1-SNAPSHOT
- Add default message to Error Results

0.2-SNAPSHOT
- upgrade to osgl-tool 0.4-SNAPSHOT
- Annotations now moved to annotatons package

0.1-SNAPSHOT
- base verion when history log started
