package com.jaswanth.bookapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name="books")
@ApiModel(description = "This table holds books information.")
public class Book
{
    @Id
    @ApiModelProperty(notes="This is a Book Id. It shall be unique.")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String bookName;
    private String bookauthor;
    private String bookavailability;

//    public long getbookId() {
//        return bookId;
//    }
//
//    public void setbookId(String bookId) {
//        this.bookId = Long.parseLong(bookId);
//    }
//
//    public String getbookName() {
//        return bookName;
//    }
//
//    public void setbookName(String bookName) {
//        this.bookName = bookName;
//    }
//
//    public String getbookauthor() {
//        return bookauthor;
//    }
//
//    public void setbookauthor(String bookauthor) {
//        this.bookauthor = bookauthor;
//    }
//
//    public String getbookavailability() {
//        return bookavailability;
//    }
//
//    public void setbookavailability(String bookavailability) {
//        this.bookavailability = bookavailability;
//    }
}
