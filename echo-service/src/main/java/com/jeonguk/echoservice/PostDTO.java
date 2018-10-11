package com.jeonguk.echoservice;

import lombok.Data;

public class PostDTO {
    @Data
    public static class ReqPost {
        private String title;
        private String userName;
        private String content;
    }

    @Data
    public static class ResPost {
        private Long id;
        private String title;
        private String content;
        private String userName;
        private String createdAt;
    }
}
