package com.zombietank.config.db;

import com.google.common.base.Objects;

public class Script {
	private final String contents;
	private final String path;
	private final String checksum;
	private boolean system;

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

	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("path", path).add("system", system).add("checksum", checksum).toString();
	}
}
