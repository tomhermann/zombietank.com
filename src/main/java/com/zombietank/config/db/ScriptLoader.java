package com.zombietank.config.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

public class ScriptLoader {
	private final ResourceLoader resourceLoader;

	public ScriptLoader() {
		this(new DefaultResourceLoader());
	}

	public ScriptLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Script loadScript(String path) throws IOException {
		InputStream inputStream = null;
		try {
			MessageDigest messageDigest = md5Digest();
			inputStream = new DigestInputStream(resourceLoader.getResource(path).getInputStream(), messageDigest);
			String contents = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
			String md5 = new BigInteger(1, messageDigest.digest()).toString(16);
			return new Script(path, contents, md5);
		} finally {
			Closeables.closeQuietly(inputStream);
		}
	}
	
	private static MessageDigest md5Digest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
