package com.zombietank.pictures;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class PictureFindingDirectoryWalkerTest {
	private PictureFindingDirectoryWalker pictureFindingDirectoryWalker;
	
	@Before
	public void setup() {
		pictureFindingDirectoryWalker = new PictureFindingDirectoryWalker();
	}
	@Test
	public void test() throws Exception {
		System.out.println(pictureFindingDirectoryWalker.find(new File("C:\\Users\\tom\\Pictures")));
	}

}
