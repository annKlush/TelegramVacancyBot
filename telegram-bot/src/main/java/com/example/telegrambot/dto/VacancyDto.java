package com.example.telegrambot.dto;

import com.opencsv.bean.CsvBindByName;
import org.springframework.stereotype.Component;

@Component
public class VacancyDto {
    @CsvBindByName(column = "Id")
    private String id;
    @CsvBindByName(column = "Title")
    private String title;
    @CsvBindByName(column = "Short description")
    private String shortDescription;
    @CsvBindByName(column = "Link")
    private String link;
    @CsvBindByName(column = "Long description")
    private String longDesc;
    @CsvBindByName(column = "Salary")
    private String salary;

    @CsvBindByName(column = "Company")
    private String company;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
        if(salary.equals("-")){
            this.salary="Not specified";
        }
    }


    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public VacancyDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VacancyDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
