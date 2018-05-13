package web.mvc.domain;

import java.util.Date;

public class Post {
    Integer id;
    String conversationId;
    String userId;
    String body;

    public Post() {
    }

    public Post(String conversationId, String userId, String postBody){
        this.conversationId = conversationId;
        this.userId = userId;
        this.body = postBody;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
