// NativeHostHandler.java

// Copyright (C) 2013 Pedro Fernandes

// This program is free software; you can redistribute it and/or modify it under the terms of the GNU 
// General Public License as published by the Free Software Foundation; either version 2 of the 
// License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
// even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
// the GNU General Public License for more details. You should have received a copy of the GNU 
// General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 
// Temple Place, Suite 330, Boston, MA 02111-1307 USA

package Ipe.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class NativeHostHandler implements HttpHandler {

    private String rootUri;

    /**
     *
     * @param rootUri
     */
    public NativeHostHandler(String rootUri) {
        this.rootUri = rootUri;
    }

    /**
     *
     * @param is
     * @return
     * @throws IOException
     */
    private static byte[] toBytes(InputStream is) throws IOException {
        int len, size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1) {
                bos.write(buf, 0, len);
            }

            buf = bos.toByteArray();
        }

        return buf;
    }

    /**
     *
     * @param stream
     * @return
     * @throws Exception
     */
    private byte[] sendResponse(InputStream stream) throws Exception {
        if (stream == null) {
            throw new Exception("stream is null or empty.");
        }

        byte[] b = null;
        try {
            b = toBytes(stream);
            System.out.println("Response data length = " + b.length);

        } catch (IOException ex) {
            Logger.getLogger(NativeHostHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }

    /**
     *
     * @param url
     * @return
     */
    private InputStream getResourceName(String url) {
        if (url.equals("/")) {
            url = "/index.html";
        }

        String resourceName = url.substring(url.lastIndexOf("/") + 1, (url.length() - url.lastIndexOf("/")));
        return this.getClass().getClassLoader().getResourceAsStream(resourceName);
    }

    /**
     *
     * @param exchange
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("GET")) {
            // Get url
            String url = exchange.getRequestURI().toString();
            String contentType = "";
            byte[] b = null;

            // Get header
            Headers header = exchange.getResponseHeaders();

            try {
                if (url.equals("/") || url.endsWith(".html") || url.endsWith(".htm")) {
                    contentType = "text/html";
                    b = sendResponse(getResourceName(url));
                } else {
                    if (url.endsWith(".xap")) {
                        contentType = "application/xap";
                        b = sendResponse(getResourceName(url));
                    } else if (url.endsWith(".js")) {
                        contentType = "application/x-javascript";
                        b = sendResponse(getResourceName(url));
                    } else if (url.endsWith(".css")) {
                        contentType = "text/css";
                        b = sendResponse(getResourceName(url));
                    } else if (url.endsWith(".png")) {
                        contentType = "image/png";
                        b = sendResponse(getResourceName(url));
                    } else if (url.endsWith(".gif")) {
                        contentType = "image/gif";
                        b = sendResponse(getResourceName(url));
                    } else if (url.endsWith(".jpg")) {
                        contentType = "image/jpg";
                        b = sendResponse(getResourceName(url));
                    } else {
                        throw new Exception("Unknown request received " + url);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(NativeHostHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!contentType.equals("") && b != null) { // 200: OK
                header.set("Server", "Indigo");
                header.set("Accept-Ranges", "bytes");
                header.set("Content-Type", contentType);
                header.set("Content-Length", (new Integer(b.length)).toString());
                header.set("Connection", "Close");
                exchange.sendResponseHeaders(200, 0);
                
                try (OutputStream body = exchange.getResponseBody()) {
                    body.write(b);
                }

                System.out.println("Response content type = " + contentType);
            } else {
                header.set("Server", "Indigo");
                header.set("Connection", "Close");
                exchange.sendResponseHeaders(404, 0);
            }
        }
    }
}
