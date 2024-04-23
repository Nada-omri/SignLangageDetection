package com.example.signyourway;

public class ModelClass {
    private String ans;
    private String oA;
    private String oB;
    private String oC;
    private String oD;

    private String tURL;
    ModelClass(){

    }
    public ModelClass(String img, String oA, String oB, String oC, String oD, String ans) {
        tURL= img;
        this.oA = oA;
        this.oB = oB;
        this.oC = oC;
        this.oD = oD;
        this.ans = ans;
    }

    // Getter for Question
    public String gettURL() {
        return tURL;
    }

    public void settURL(String imageUrl) {
        this.tURL = imageUrl;
    }

    // Getter for oA
    public String getOA() {
        return oA;
    }

    // Setter for oA
    public void setOA(String oA) {
        this.oA = oA;
    }

    // Getter for oB
    public String getOB() {
        return oB;
    }

    // Setter for oB
    public void setOB(String oB) {
        this.oB = oB;
    }

    // Getter for oC
    public String getOC() {
        return oC;
    }

    // Setter for oC
    public void setOC(String oC) {
        this.oC = oC;
    }

    // Getter for oD
    public String getOD() {
        return oD;
    }

    // Setter for oD
    public void setOD(String oD) {
        this.oD = oD;
    }

    // Getter for ans
    public String getAns() {
        return ans;
    }

    // Setter for ans
    public void setAns(String ans) {
        this.ans = ans;
    }
}
