/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOModel;


import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author asmaa
 */
/**
 * This is a DTO class representing the message and its information
 *
 */
public class MessageDTO implements Serializable{
            private static final long serialVersionUID = 6529685098267757690L;

  
    protected String msg;

    protected String from;

    protected String to;

       protected String fontFamily;

   
    protected int sizeFont;

    protected String color;

   
    public int getSizeFont() {
        return sizeFont;
    }

   
    public void setSizeFont(int sizeFont) {
        this.sizeFont = sizeFont;
    }
  
    public void setFrom(String from) {
        this.from = from;
    }

    
    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

  
    public MessageDTO(){
        
    }

  
    public MessageDTO(String msg, String from, String to) {
        this.msg = msg;
        this.from = from;
        this.to = to;
    }

   
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
  
    public String getFontFamily() {
        return fontFamily;
    }

 
    public String getColor() {
        return color;
    }

   
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    
    public void setColor(String color) {
        this.color = color;
    }

  
    public String getMsg() {
        return msg;
    }
    
    
    
}
