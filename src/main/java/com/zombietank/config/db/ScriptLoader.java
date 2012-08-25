package com.zombietank.config.db;

import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.zombietank.io.InputSuppliers;

public class ScriptLoader {
	private final ResourceLoader resourceLoader;

	public ScriptLoader() {
		this(new DefaultResourceLoader());
	}

	public ScriptLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Script loadScript(String path) throws IOException {
		MessageDigest messageDigest = md5Digest();
		DigestInputStream inputStream = new DigestInputStream(resourceLoader.getResource(path).getInputStream(), messageDigest);
		String contents = CharStreams.toString(InputSuppliers.newReaderSupplier(inputStream, Charsets.UTF_8));
		String checksum = new BigInteger(1, messageDigest.digest()).toString(16);
		return new Script(path, contents, checksum);
	}
	
	private static MessageDigest md5Digest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
