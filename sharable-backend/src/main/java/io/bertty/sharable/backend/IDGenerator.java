package io.bertty.sharable.backend;

import org.apache.commons.lang3.RandomStringUtils;

public class IDGenerator {

  public static String generate(){
    return IDGenerator.generate(10);
  }
  public static String generate(int lenght){
    return RandomStringUtils.randomAlphanumeric(10);
  }
}
