package uk.me.eddies.demo.testusejartest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Integration test making use of a jar containing {@link DummyObject}.
 */
public class DummyObjectIntegrationTest {

	private static final File BUILT_JAR = new File("build/libs/test-use-jar-test.jar");
	private static final String DUMMY_OBJECT_PATH_IN_JAR = "uk/me/eddies/demo/testusejartest/DummyObject.class";
	
	@Test
	@Ignore("Un-ignore this to verify that failing tests cause build failure.")
	public void failingTestShouldFailBuild() {
		fail();
	}
	
	@Test
	public void jarShouldExist() {
		assertThat(BUILT_JAR.isFile(), equalTo(true));
	}
	
	@Test
	public void jarShouldContainDummyObject() throws ZipException, IOException {
		try (
				ZipFile jar = new ZipFile(BUILT_JAR);
		) {
			assertThat(
					jar.stream().anyMatch(entry -> entry.getName().equals(DUMMY_OBJECT_PATH_IN_JAR)),
					equalTo(true));
		}
	}
}
