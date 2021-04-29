package com.microstudy.common.to;

import lombok.Data;

import java.util.Date;

@Data
public class CalendarTo {

    private String type;
    private Date date;
    private String status;

}
