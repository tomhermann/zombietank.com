package com.zombietank.pictures;

import static org.apache.commons.io.filefilter.FileFilterUtils.and;
import static org.apache.commons.io.filefilter.FileFilterUtils.fileFileFilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import com.zombietank.io.FileFindingDirectoryWalker;
import com.zombietank.io.FileFunction;

public class PictureFindingDirectoryWalker extends FileFindingDirectoryWalker<PictureDescriptor> {
	private static final List<String> SUFFIXES = Arrays.asList("GIF", "PNG", "JPEG", "BMP", "WBMP");
	private static final FileFilter FILTER = and(fileFileFilter(), new SuffixFileFilter(SUFFIXES, IOCase.INSENSITIVE));

	public PictureFindingDirectoryWalker() {
		super(FILTER, new PictureFunction());
	}

	private static class PictureFunction extends FileFunction<PictureDescriptor> {
		@Override
		public PictureDescriptor handle(File file) throws IOException {
			PictureDescriptor descriptor = new PictureDescriptor();
			BufferedImage image = ImageIO.read(file);
			descriptor.setFile(file);
			descriptor.setHeight(image.getHeight());
			descriptor.setWidth(image.getWidth());
			return descriptor;
		}
	}
}
