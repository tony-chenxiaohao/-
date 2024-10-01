package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书信息对象 books
 * 
 * @author 图书管理系统
 * @date 2024-09-28
 */
public class Books extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书编号 */
    @Excel(name = "图书编号")
    private Long id;

    /** 图书标题 */
    @Excel(name = "图书标题")
    private String title;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 出版社 */
    @Excel(name = "出版社")
    private String publisher;

    /** 出版日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出版日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishDate;

    /** ISBN号码 */
    @Excel(name = "ISBN号码")
    private String isbn;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverImage;

    /** 图书描述 */
    @Excel(name = "图书描述")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public String getPublisher() 
    {
        return publisher;
    }
    public void setPublishDate(Date publishDate) 
    {
        this.publishDate = publishDate;
    }

    public Date getPublishDate() 
    {
        return publishDate;
    }
    public void setIsbn(String isbn) 
    {
        this.isbn = isbn;
    }

    public String getIsbn() 
    {
        return isbn;
    }
    public void setCoverImage(String coverImage) 
    {
        this.coverImage = coverImage;
    }

    public String getCoverImage() 
    {
        return coverImage;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("author", getAuthor())
            .append("publisher", getPublisher())
            .append("publishDate", getPublishDate())
            .append("isbn", getIsbn())
            .append("coverImage", getCoverImage())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
