package org.xiwc.tool;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {

	@Test
	public void test01() {
		String reduce = Stream.of(new String[] { "1", "2" }).reduce("", (a, b) -> a + b);

		reduce = Stream.of(new String[] { "1", "2" }).collect(Collectors.joining(","));
		System.out.println(reduce);
	}
}
