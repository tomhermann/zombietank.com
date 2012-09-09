package com.zombietank.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.DirectoryWalker;

public class FileFindingDirectoryWalker<T> extends DirectoryWalker<T> {
	private final FileFunction<T> fileFunction;

	@Inject
	public FileFindingDirectoryWalker(FileFilter fileFilter, FileFunction<T> fileFunction) {
		super(fileFilter, -1);
		this.fileFunction = fileFunction;
	}
	
	public List<T> find(File startDirectory) throws IOException {
		List<T> results = new ArrayList<T>();
		walk(startDirectory, results);
		return results;
	}
	

	@Override
	protected void handleFile(File file, int depth, Collection<T> results) throws IOException {
		results.add(fileFunction.apply(file));
	}
}
