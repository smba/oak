/*
 * Copyright (c) 1998-2015 Caucho Technology -- all rights reserved
 *
 * This file is part of Resin(R) Open Source
 *
 * Each copy or derived work must preserve the copyright notice and this
 * notice unmodified.
 *
 * Resin Open Source is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * Resin Open Source is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Resin Open Source; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.caucho.quercus.lib.date;

import com.caucho.quercus.QuercusContext;
import com.caucho.quercus.env.ConstStringValue;
import com.caucho.quercus.env.Env;
import com.caucho.quercus.env.StringValue;
import com.caucho.util.QDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kaz Nishimura
 */
public class DateTimeTest {

    private static final StringValue DATE_FORMAT =
            new ConstStringValue("Y-m-d");

    private Env env;

    @Before
    public void setUp() {
        env = new Env(new QuercusContext());
    }

    @After
    public void tearDown() {
        env = null;
    }

    @Test
    public void testSetISODate() {
        DateTime dateTime = new DateTime(new QDate(), new DateTimeZone(env));
        dateTime.setISODate(2008, 2, 1);
        assertEquals(
                "2008-01-07", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2008, 2, 7);
        assertEquals(
                "2008-01-13", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2008, 2, 8);
        assertEquals(
                "2008-01-14", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2008, 53, 7);
        assertEquals(
                "2009-01-04", dateTime.format(env, DATE_FORMAT).toString());
        // The followings are taken from Wikipedia page
        // <https://en.wikipedia.org/wiki/ISO_week_date>.
        dateTime.setISODate(2004, 53, 6);
        assertEquals(
                "2005-01-01", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2004, 53, 7);
        assertEquals(
                "2005-01-02", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2005, 52, 6);
        assertEquals(
                "2005-12-31", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2007, 1, 1);
        assertEquals(
                "2007-01-01", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2007, 52, 7);
        assertEquals(
                "2007-12-30", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2008, 1, 1);
        assertEquals(
                "2007-12-31", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2008, 52, 7);
        assertEquals(
                "2008-12-28", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2009, 1, 4);
        assertEquals(
                "2009-01-01", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2009, 53, 4);
        assertEquals(
                "2009-12-31", dateTime.format(env, DATE_FORMAT).toString());
        dateTime.setISODate(2009, 53, 7);
        assertEquals(
                "2010-01-03", dateTime.format(env, DATE_FORMAT).toString());
    }
}