/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 20241123L;
    private int yearPublished, pageCount;
    private String title, author, id, code;

    public Book() {
    }

    public Book(String id, String title, String author, int yearPublished, int pageCount) {
        this.id = id;
        this.yearPublished = yearPublished;
        this.pageCount = pageCount;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", yearPublished=" + yearPublished + ", pageCount=" + pageCount + ", title=" + title + ", titleSize=" + title.length() + ", author=" + author + '}';
    }
    
}
