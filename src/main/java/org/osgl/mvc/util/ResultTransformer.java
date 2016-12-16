package org.osgl.mvc.util;

import org.osgl.http.H;
import org.osgl.storage.impl.SObject;

/**
 * A `ResultTransformer` allows an additional logic to capture
 * the output of a {@link org.osgl.mvc.result.Result}, process it
 * and then output to the {@link org.osgl.http.H.Response}
 */
public interface ResultTransformer {

    /**
     * Process the binary content and output to response
     * @param binaryContent the binary content as an output of result
     * @param response the response to which the transformed result will be written
     */
    void transform(SObject binaryContent, H.Response response);

    /**
     * Process the text content and output to response
     * @param textContent the text content as an output of result
     * @param response the response to which the transformed result will be written
     */
    void transform(String textContent, H.Response response);
}
