package com.fengwenyi.javalib.http;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
public class Response {

    private int code;

    private String msg;

    private Body body;

    public static class Body implements Closeable {

        private InputStream body;

        @Override
        public void close() throws IOException {
            body.close();
        }

        public InputStream getBody() {
            return body;
        }

        public Body setBody(InputStream body) {
            this.body = body;
            return this;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Body getBody() {
        return body;
    }

    public Response setBody(Body body) {
        this.body = body;
        return this;
    }
}
