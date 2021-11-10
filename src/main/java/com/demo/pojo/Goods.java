package com.demo.pojo;

import lombok.Data;

@Data
public class Goods {

  private long gid;
  private String gname;
  private String gimg;
  private double gprice;
  private long gstock;
  private String maintainDate;
  private String classify;
  private String gdescribe;

  public Goods() {
  }

  public Goods(String gname, double parseDouble, int parseInt, String maintainDate, Object o, String gdescribe) {
      this.gid = Long.parseLong(null);
      this.gname = gname;
      this.gimg = "";
      this.gprice = gprice;
      this.gstock = gstock;
      this.maintainDate = maintainDate;
      this.classify = classify;
      this.gdescribe = gdescribe;
    }

}
