package com.zombietank.audio.functions;

import java.util.Iterator;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.zombietank.audio.PersistableTag;

@Component
public class PersistableTagFunction implements
		Function<AudioFile, PersistableTag> {

	@Override
	public PersistableTag apply(AudioFile audioFile) {
		return null;
	}

}
