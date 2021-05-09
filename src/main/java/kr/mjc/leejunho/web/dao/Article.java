package kr.mjc.leejunho.web.dao;

import lombok.Data;

@Data
public class Article {
    int articleId;
    String title;
    String content;
    int userId;
    String name;
    String cdate;
    String udate;

    @Override
    public String toString() {
        return  "글 번호 : " +
                articleId +
                ", 제목='" + title + '\'' +
                ", 회원 번호=" + userId +
                ", 이름='" + name + '\'' +
                ", 작성일='" + cdate + '\'' +
                ", 수정일='" + udate + '\'' +
                '}';
    }
}