package com.example.pd.handler.models;

import com.example.pd.exceptions.InvalidClientException;
import com.example.pd.utils.ClientRegexUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientModel {
    private static final List<String> IP_WHITELIST;
//         한번 정해지면 런타임에서 수정이 불가능함. = UnmodifiableArrayList

    static {
        ArrayList<String> upWhitelist = new ArrayList<>();
        upWhitelist.add("127.0.0.1");
        upWhitelist.add("112.158.5.121");
        IP_WHITELIST = Collections.unmodifiableList(upWhitelist);
    }

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final String ip;
    private final String userAgent;
    private final String requestUri;

    public ClientModel(HttpServletRequest request, HttpServletResponse response) throws InvalidClientException {
        this.request = request;
        this.response = response;

        if(ClientModel.IP_WHITELIST.stream().anyMatch(x -> x.equals(this.request.getRemoteAddr()))) {
            this.ip = this.request.getRemoteAddr();
        }else {
            this.ip = request.getHeader("CF-Connecting-IP");
        }
        this.userAgent = request.getHeader("User-Agent");
        this.requestUri = request.getRequestURI();

        if(!ClientRegexUtil.checkIp(this.ip) ||
                !ClientRegexUtil.checkUserAgent(this.userAgent) ||
                !ClientRegexUtil.checkRequestUri(this.requestUri)) {
            throw new InvalidClientException(String.format("IP : %s\nUser Agent : %s\nRequest URI : %s",
                    this.ip == null ? "(NULL)" : this.ip,
                    this.userAgent == null ? "(NULL)" : this.userAgent,
                    this.requestUri == null ? "(NULL)" : this.requestUri));
        }
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public String getUrlPrefix() {
        // <Context path="" ... />
        // "s://d:p"
//        String[] urlArray = this.request.getRequestURL().toString().split("/");
//        return String.format("%s//%s%s", urlArray[0], urlArray[2]);

        String port;
        if((this.request.getScheme().equals("http") &&
                this.request.getServerPort() == 80) ||
                (this.request.getScheme().equals("https") &&
                        this.request.getServerPort() == 443)) {
            port = "";
        } else {
            port = String.format(":%d", this.request.getServerPort());
        }
        return String.format("%s://%s%s%s",
                this.request.getScheme(),
                this.request.getServerName(),
                port,
                this.request.getContextPath());
    }
}
