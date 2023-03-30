package com.example.covid_19.DTO;

public class ArticleDTO {
    Integer MA_ARTICLE,EYE_ARTICLE;
    String TITLE_ARTICLE,CONTENT_ARTICLE;

    public Integer getMA_ARTICLE() {
        return MA_ARTICLE;
    }

    public void setMA_ARTICLE(Integer MA_ARTICLE) {
        this.MA_ARTICLE = MA_ARTICLE;
    }

    public Integer getEYE_ARTICLE() {
        return EYE_ARTICLE;
    }

    public void setEYE_ARTICLE(Integer EYE_ARTICLE) {
        this.EYE_ARTICLE = EYE_ARTICLE;
    }

    public String getTITLE_ARTICLE() {
        return TITLE_ARTICLE;
    }

    public void setTITLE_ARTICLE(String TITLE_ARTICLE) {
        this.TITLE_ARTICLE = TITLE_ARTICLE;
    }

    public String getCONTENT_ARTICLE() {
        return CONTENT_ARTICLE;
    }

    public void setCONTENT_ARTICLE(String CONTENT_ARTICLE) {
        this.CONTENT_ARTICLE = CONTENT_ARTICLE;
    }
}
