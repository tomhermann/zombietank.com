package com.zombietank.pictures;

import java.io.File;

import com.google.common.base.Objects;

public class PictureDescriptor {
	private File file;
	private int height;
	private int width;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("file", file).add("width", width).add("height", height).toString();
	}
}
