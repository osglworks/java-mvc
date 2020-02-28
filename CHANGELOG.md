# OSGL MVC CHANGELOG

1.13.1
* Add default error message for response 503 #40
* When applying result, contentType must be set before calling `applyBeforeCommitHandler` #39

1.13.0 02/Jan/2020
* Update to osgl-1.23.0
* update osgl-http to 1.13.0

1.11.0 03/Nov/2019
* Support rendering in YAML #38
* update osgl-http to 1.11.0
* update osgl-tool to 1.21.0

1.10.0 19/Apr/2019
* update to osgl-tool-1.19.2
* Result rendering - result committed even `applyBeforeCommitHandler` raised exception #37

1.9.0 30/Oct/2018
* update to osgl-tool-1.18.0
* `ResponseContentType` shall be declared as `@Inherited` #36
* `MvcConfig.applyBeforeCommitHandler` called twice when error response generated #35
* Make `SessionFree` annotation be able to mark on a class #34

1.8.0 14/Jun/2018
* update osgl-tool to 1.15.1

1.7.2 7/Jun/2018
* `Result.addHeader` function not working as expected #33

1.7.1 19/May/2018
- update osgl-tool to 1.13.1
- update osgl-http to 1.7.0
- update osgl-storage to 1.6.0

1.7.0
- update osgl-tool to 1.12.0
- update osgl-http to 1.6.1

1.6.1
- `RenderJSON.of(Object)` logic error #31

1.6.0
- Refactor output mechanism - separating binary and char output #28
- `RenderBinary` - add methods to set attachment name and content type #30
- Add constructor to `RenderBinary` to accept `byte[]` parameter #29
- update osgl-tool to 1.9.0

1.5.3
- move BufferedOuput to osgl-tool
- update to osgl-tool-1.8.1
- update to osgl-http-1.5.1

1.5.2 17/Mar/2018
- Implement an alarm mechanism #27
- Add `BufferedOutput` to improve direct output response model performance #26
- Allow it uses string content producer for JSON/XML/HTML/TXT rendering #25
- Missing default error message for some error results #24
- RenderBinary: it shall set the length for File source #23

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
