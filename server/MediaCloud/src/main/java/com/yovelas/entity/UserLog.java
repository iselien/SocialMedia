package com.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLog {
    private int id;
    private int userId;
    private String createTime;
    private String descript;
    private String serverName;
    private String serverPort;
    private String clientHost;
    private String clientAddress;
    private String clientPort;
    private String localName;
    private String localPort;
    private String requestUrl;
    private String requestQuery;
    private String requestMethod;
    private String requestProtocol;
    private String requestScheme ;
    private String requestOrigin ;
    private String requestReferer;
    private String requestCookie;
    private String requestConnection;
    private String requestContent_length;
    private String requestContent_type;
    private String requestAccept;
    private String requestAccept_encoding;
    private String requestAccept_language;
    private String userAgent;

    public UserLog(HttpServletRequest req,int userId,String descript, Object responseBody) {
        this.userId = userId;
        this.descript = descript;

        this.serverName = req.getServerName();
        this.serverPort = String.valueOf(req.getServerPort());
        this.clientHost = req.getRemoteHost();
        this.clientAddress = req.getRemoteAddr();
        this.clientPort = String.valueOf(req.getRemotePort());
        this.localName = req.getLocalName();
        this.localPort = String.valueOf(req.getLocalPort());
        this.requestUrl = req.getRequestURL().toString();
        this.requestQuery = req.getQueryString();
        this.requestMethod = req.getMethod();
        this.requestProtocol = req.getProtocol();
        this.requestScheme = req.getScheme();
        this.requestOrigin = req.getHeader("origin");
        this.requestReferer = req.getHeader("referer");
        this.requestCookie = req.getHeader("cookie");
        this.requestConnection = req.getHeader("connection");
        this.requestContent_length = req.getHeader("content-length");
        this.requestContent_type = req.getHeader("content-type");
        this.requestAccept = req.getHeader("accept");
        this.requestAccept_encoding = req.getHeader("accept-encoding");
        this.requestAccept_language = req.getHeader("accept_aanguage");
        this.userAgent = req.getHeader("user-agent");
    }
}
