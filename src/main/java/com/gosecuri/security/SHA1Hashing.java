package com.gosecuri.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Hashing {

    private static final String algorithm = "SHA-1";
    private final MessageDigest md;

    public SHA1Hashing() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance(algorithm);
    }

    public String hash(final String str) {
        md.update(StandardCharsets.UTF_8.encode(str));
        return String.format("%032x", new BigInteger(1, md.digest()));
    }
}
