package de.maltorpro.shop.server.turbine;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

import de.maltorpro.shop.monitoring.utils.SSLUtils;

@SpringBootApplication
@EnableTurbine
public class TurbineApplication {

    public static void main(String[] args)
            throws KeyManagementException, NoSuchAlgorithmException {

        String certificateCheck = System.getProperty("certificateCheck");

        if (StringUtils.equals(certificateCheck, "false")
                || StringUtils.equals(certificateCheck, "0")) {

            SSLUtils.turnOffSslChecking();
        }

        SpringApplication.run(TurbineApplication.class, args);
    }
}
