/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cusomItem.chatMsg;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author asmaa
 */
public class MessageModel {
     private String name;
    private String msg;
    private String timeStamp ;
    private String img;

     /**
     * Constructor of this class
     * @param msg  //
     */
    public MessageModel(String msg) {
        this.msg = msg;
        
    }

    /**
     *
     * @return //
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name //
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return //
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg //
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return  //
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     *
     * @param timeStamp  //
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     *
     * @return //
     */
    public String getImg() {
        return img;
    }

    /**
     *
     * @param img //
     */
    public void setImg(String img) {
        this.img = img;
    }
    
   

    

}