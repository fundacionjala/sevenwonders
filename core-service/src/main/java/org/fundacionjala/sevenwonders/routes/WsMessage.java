/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.routes;


/**
 * Created by Luis Gumucio on 8/25/2016.
 */

public class WsMessage {

    private String header;
    private String sender;
    private String senderTimestamp;
    private String content;
    private String contentType;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderTimestamp() {
        return senderTimestamp;
    }

    public void setSenderTimestamp(String senderTimestamp) {
        this.senderTimestamp = senderTimestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "WsMessage [header=" + header + ", sender=" + sender
                + ", senderTimestamp=" + senderTimestamp + ", content="
                + content + ", contentType=" + contentType + "]";

    }
}