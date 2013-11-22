// NativeHostServer.java

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

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public final class NativeHostServer {

    private HttpServer server = null;
    private String url;
    private int port = 8090;

    /**
     *
     * @param applicationUrl
     * @throws URISyntaxException
     */
    public NativeHostServer(String applicationUrl) throws URISyntaxException {
        url = (applicationUrl == null ? "/" : applicationUrl);

        try {
            // find available port
            port = findAvailablePort();

            // load host handler
            NativeHostHandler handler = new NativeHostHandler(this.getRootUri().toString());

            // create server
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext(url, handler); // process all requests
            server.setExecutor(Executors.newCachedThreadPool()); // creates a default executor
            server.start();

            System.out.println("Server is listening on port " + port);

        } catch (IOException ex) {
            Logger.getLogger(NativeHostServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void stop() {
        server.stop(0);
        System.out.println("Server stopped listening on port " + port);
    }

    /**
     *
     * @return @throws URISyntaxException
     */
    public URI getRootUri() throws URISyntaxException {
        return new URI("http://127.0.0.1:" + port + url);
    }

    /**
     *
     * @return @throws IOException
     */
    private int findAvailablePort() throws IOException {
        int availablePort = 0;

        ServerSocket s = new ServerSocket(0);
        availablePort = s.getLocalPort();
        s.close();

        return availablePort;
    }
}
