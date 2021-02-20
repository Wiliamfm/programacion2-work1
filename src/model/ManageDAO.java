package model;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ManageDAO {

  private File file;
  private ObjectInputStream in;
  private ObjectOutputStream ou;
  private FileReader fr;
  private BufferedReader br;

  public ManageDAO() {

  }
}
