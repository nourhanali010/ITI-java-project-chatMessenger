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
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private int userId;
    private int chatId;
    private Date timeStamp;
    private String msg;
    protected String from;
    protected String to;

    public MessageDTO() {
    }

    public MessageDTO(String msg, String from, String to) {
        this.msg = msg;
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /**
     *
     * @param userId .
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @param chatId .
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     *
     * @param Date .
     */
    public void setTimeStamp(int Date) {
        this.timeStamp = timeStamp;
    }

    /**
     *
     * @param msg .
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return .
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return .
     */
    public int getChatId() {
        return chatId;
    }

    /**
     *
     * @return .
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     *
     * @return .
     */
    public String getMsg() {
        return msg;
    }

}
