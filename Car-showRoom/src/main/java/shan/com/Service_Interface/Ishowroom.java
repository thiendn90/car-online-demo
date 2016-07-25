package shan.com.Service_Interface;

import shan.com.util.RestResponeDto;

public interface Ishowroom {
  public RestResponeDto showAll();
  public RestResponeDto search(int showId);
}
