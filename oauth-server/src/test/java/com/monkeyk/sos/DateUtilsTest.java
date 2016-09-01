/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.monkeyk.sos;

import com.monkeyk.sos.utils.DateUtils;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/*
  * @author Shengzhao Li
  */
public class DateUtilsTest {


    @Test
    public void convert() throws ParseException {
        Date localDate = DateUtils.simpleDateFormat.parse("2015-04-03 12:30:22");
        /*LocalDateTime localDateTime = LocalDateTime.of(2015, 4, 3, 12, 30, 22);

        final LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);

        final Timestamp timestamp = Timestamp.valueOf(localDateTime);
        assertNotNull(timestamp);
        System.out.println(timestamp);


        final String text = DateUtils.toDateTime(localDateTime);
        assertEquals(text,"2015-04-03 12:30:22");*/

    }

}