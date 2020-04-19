package ch.hftm.vsblog;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair pair = kpg.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        Base64.Encoder encoder = Base64.getEncoder();

        String outFile = "src/main/resources/META-INF/jwt";
        Writer out = new FileWriter(outFile + ".key");
        out.write("-----BEGIN RSA PRIVATE KEY-----\n");
        out.write(encoder.encodeToString(privateKey.getEncoded()));
        out.write("\n-----END RSA PRIVATE KEY-----\n");
        out.close();

        out = new FileWriter(outFile + ".pub");
        out.write("-----BEGIN RSA PUBLIC KEY-----\n");
        out.write(encoder.encodeToString(publicKey.getEncoded()));
        out.write("\n-----END RSA PUBLIC KEY-----\n");
        out.close();

        // byte[] publicKeyByte = publicKey.getEncoded();
        // FileOutputStream keyfos = new FileOutputStream("src/main/resources/META-INF/public_key.pem");
        // keyfos.write(publicKeyByte);
        // keyfos.close();

        // byte[] privateKeyByte = privateKey.getEncoded();
        // keyfos = new FileOutputStream("src/main/resources/META-INF/private_key.pem");
        // keyfos.write(privateKeyByte);
        // keyfos.close();

        System.err.println("Private key format: " + privateKey.getFormat());
// prints "Private key format: PKCS#8" on my machine

System.err.println("Public key format: " + publicKey.getFormat());
// prints "Public key format: X.509" on my machine
    }

}