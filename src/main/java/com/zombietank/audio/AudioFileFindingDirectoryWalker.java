package com.zombietank.audio;

import static org.apache.commons.io.filefilter.FileFilterUtils.and;
import static org.apache.commons.io.filefilter.FileFilterUtils.fileFileFilter;
import static org.apache.commons.io.filefilter.FileFilterUtils.suffixFileFilter;

import java.io.FileFilter;

import org.apache.commons.io.IOCase;

import com.zombietank.io.FileFindingDirectoryWalker;
import com.zombietank.io.FileFunction;

public class AudioFileFindingDirectoryWalker extends FileFindingDirectoryWalker<SongFile> {
	private static final FileFilter FILTER = and(fileFileFilter(), suffixFileFilter("mp3", IOCase.INSENSITIVE));
	
	public AudioFileFindingDirectoryWalker(FileFilter fileFilter, FileFunction<SongFile> fileFunction) {
		super(FILTER, fileFunction);
	}
}
