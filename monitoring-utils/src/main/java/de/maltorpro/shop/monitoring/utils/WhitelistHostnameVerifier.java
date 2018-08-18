package de.maltorpro.shop.monitoring.utils;

import java.util.HashSet;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

// singleton
public enum WhitelistHostnameVerifier implements HostnameVerifier {
    // these hosts get whitelisted
    INSTANCE("localhost", "config-server");

    private Set<String> whitelist = new HashSet<>();
    private HostnameVerifier defaultHostnameVerifier = HttpsURLConnection
            .getDefaultHostnameVerifier();

    WhitelistHostnameVerifier(String... hostnames) {
        for (String hostname : hostnames) {
            whitelist.add(hostname);
        }
    }

    @Override
    public boolean verify(String host, SSLSession session) {
        if (whitelist.contains(host)) {
            return true;
        }
        // important: use default verifier for all other hosts
        return defaultHostnameVerifier.verify(host, session);
    }
}
