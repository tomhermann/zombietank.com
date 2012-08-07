package com.zombietank.config.db;

import com.google.common.base.Objects;

public class Script {
	private final String contents;
	private final String path;
	private final String checksum;

	public Script(String path, String contents, String checksum) {
		this.path = path;
		this.contents = contents;
		this.checksum = checksum;
	}

	public String getContents() {
		return contents;
	}

	public String getPath() {
		return path;
	}

	public String getChecksum() {
		return checksum;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("path", path).add("checksum", checksum).toString();
	}
}
