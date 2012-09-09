package com.zombietank.audio.functions;

import java.io.File;

import javax.inject.Inject;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.zombietank.audio.SongFile;

@Component
public class TaggedFileFunction implements Function<File, SongFile> {
	private PersistableTagFunction tagFunction;

	@Inject
	public TaggedFileFunction(PersistableTagFunction tagFunction) {
		this.tagFunction = tagFunction;
	}

	@Override
	public SongFile apply(File file) {
		SongFile taggedFile = new SongFile(file);
		try {
			AudioFile audioFile = AudioFileIO.read(file);
			taggedFile.setTag(tagFunction.apply(audioFile));
		} catch (Throwable e) {
			taggedFile.setError(e);
		}
		return taggedFile;
	}
}
