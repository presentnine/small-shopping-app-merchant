package com.sososhopping.merchant.board.dto;

public class BoardRegisterDto {
    String title;
    String content;
    String writingType;

    public BoardRegisterDto(String title, String content, String writingType) {
        this.title = title;
        this.content = content;
        this.writingType = writingType;
    }
}
