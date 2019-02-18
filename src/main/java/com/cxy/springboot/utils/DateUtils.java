package com.cxy.springboot.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具
 * @author cc
 * @date Mar 12, 2009
 */
public class DateUtils {
	/**
	 *
	 *@author :cc
	 *@date: Sep 27, 2011
	 *@param date
	 *@return
	 *注释：日期字符串获取Calendar对象
	 *yyyyMMdd
	 */
	public static Calendar getCalendar(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(date));
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *
	 *@author :cc
	 *@date: Nov 15, 2010
	 *@param nowDate
	 *@return
	 *注释：获取上个周期的日期----根据日期格式不同，返回上个周期的日期（日、月、年）
	 */
	public static String geLastCycledDate(String nowDate) {
		try {
			Integer.parseInt(nowDate);
			if (nowDate.length() == 4) {
				SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy");
				Date date1 = myFormatter.parse(nowDate);
				Calendar c = Calendar.getInstance();
				c.setTime(date1);
				c.add(Calendar.YEAR, -1);
				return myFormatter.format(c.getTime());
			} else if (nowDate.length() == 6) {
				SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMM");
				Date date1 = myFormatter.parse(nowDate);
				Calendar c = Calendar.getInstance();
				c.setTime(date1);
				c.add(Calendar.MONTH, -1);
				return myFormatter.format(c.getTime());
			} else if (nowDate.length() == 8) {
				SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
				Date date1 = myFormatter.parse(nowDate);
				Calendar c = Calendar.getInstance();
				c.setTime(date1);
				c.add(Calendar.DATE, -1);
				return myFormatter.format(c.getTime());
			} else {
				return nowDate;
			}
		} catch (Exception e) {
			return nowDate;
		}
	}
	/**
	 * 返回前n天的日期 格式yyyymmdd
	 * @param i
	 * @return
	 */
	public static String getDateBefore(int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String date = formatter.format(c.getTime());
		return date;
	}
	public static String getDateBefore(int i, String fmt) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -i);
		SimpleDateFormat formatter = new SimpleDateFormat(fmt);
		String date = formatter.format(c.getTime());
		return date;
	}
	/**
	 * 返回指定日期前n天的日期 格式yyyymmdd
	 * @param i
	 * @return
	 */
	public static String getDateBefore(String date, int i) {
		try {
			if (date.length() == 6) {
				date = DateUtils.getLastDateByMonth(date);
			}
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
			Date date1 = myFormatter.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(date1);
			c.add(Calendar.DATE, -i);
			return myFormatter.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 同比日期
	 * @param dateStr
	 * @return
	 */
	public static String getDateTong(String dateStr) {
		try {
			SimpleDateFormat myFormatter = null;
			if (dateStr.length() == 6) {
				myFormatter = new SimpleDateFormat("yyyyMM");
				Date date = myFormatter.parse(dateStr);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.MONTH, -1);
				return myFormatter.format(c.getTime());
			} else if (dateStr.length() == 8) {
				myFormatter = new SimpleDateFormat("yyyyMMdd");
				Date date = myFormatter.parse(dateStr);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DATE, -1);
				return myFormatter.format(c.getTime());
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 环比日期
	 * @param dateStr
	 * @return
	 */
	public static String getDateHuan(String dateStr) {
		try {
			SimpleDateFormat myFormatter = null;
			if (dateStr.length() == 6) {
				myFormatter = new SimpleDateFormat("yyyyMM");
				Date date = myFormatter.parse(dateStr);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.YEAR, -1);
				return myFormatter.format(c.getTime());
			} else if (dateStr.length() == 8) {
				myFormatter = new SimpleDateFormat("yyyyMMdd");
				Date date = myFormatter.parse(dateStr);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.MONTH, -1);
				return myFormatter.format(c.getTime());
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 *
	 *@author :cc
	 *@date: Jun 26, 2009
	 *@param i
	 *@return
	 *注释：返回前n个月的当天yyyyMMdd
	 */
	public static String getDayBeforeMonth(int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String date = formatter.format(c.getTime());
		return date;
	}
	/**
	 *
	 *@author :cc
	 *@date: Jun 26, 2009
	 *@param i
	 *@return
	 *注释：返回前n個月日期：yyyyMM
	 */
	public static String getMonthBefore(int i) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String date = formatter.format(c.getTime());
		return date;
	}
	/**
	 *
	 *@author :cc
	 *@date: Jun 26, 2009
	 *@param i
	 *@return
	 *注释：返回前n個月日期：yyyyMM
	 * @throws ParseException
	 */
	public static String getMonthBefore(String dateStr, int i) {
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat formatter = null;
			if (dateStr.length() == 8)
				formatter = new SimpleDateFormat("yyyyMMdd");
			else
				formatter = new SimpleDateFormat("yyyyMM");
			Date date = formatter.parse(dateStr);
			c.setTime(date);
			c.add(Calendar.MONTH, -i);
			return formatter.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String getDayBefore(String dateStr, int i) {
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat formatter = null;
			formatter = new SimpleDateFormat("yyyyMMdd");
			Date date = formatter.parse(dateStr);
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, -i);
			return formatter.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 返回每月的第一天
	 * @author cc
	 * @date Mar
	12, 2009
	 * @param "yyyyMM"
	 * @return
	 */
	public static String getFirstDateByMonth(String datestr) {
		return String.valueOf(Integer.valueOf(datestr).intValue() * 100 + 01);
	}
	/**
	 * 返回每月的最后一天
	 * @author cc
	 * @date Mar 12, 2009
	 * @param "yyyyMM"
	 * @return
	 */
	public static String getLastDateByMonth(String datestr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(datestr + "01");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.DAY_OF_MONTH, -1);
			return new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		} catch (Exception e) {
			return "19990101";
		}
	}
	/**
	 *
	 *@author :cc
	 *@date: Jul 9, 2010
	 *@return
	 *注释：
	 */
	public static String getDate() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = formatter.format(c.getTime());
		return date;
	}
	/**
	 *
	 *@author :cc
	 *@date: Jul 9, 2010
	 *@return
	 *注释：
	 */
	public static String getDate(String format) {
		if ("yyyyMMdd".equalsIgnoreCase(format))
			return DateUtils.getDateBefore(0);
		else if ("yyyyMM".equalsIgnoreCase(format))
			return DateUtils.getMonthBefore(0);
		else
			return DateUtils.getDate();
	}
	/**
	 *
	 *@author :cc
	 *@date: May 20, 2011
	 *@param dateStr
	 *@return
	 *注释：获得指定的月有几个周
	 */
	public static int getMonNum(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		try {
			Date date = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
			cal.set(Calendar.DATE, 1);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
			int c = cal.get(cal.WEEK_OF_MONTH);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 *
	 *@author :cc
	 *@date: Jul 20, 2011
	 *@param format
	 *@return
	 *注释：
	 */
	public static String getCurrentDate(String format) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String date = formatter.format(c.getTime());
		return date;
	}
	/**
	 *
	 *@author :cc
	 *@date: Jul 20, 2011
	 *@param
	 *@return
	 *注释：获取当前年的第一个月
	 */
	public static String getFirstMonthByMonth(String date) {
		return date.substring(0, 4) + "01";
	}
	/**
	 *
	 *@author :cc
	 *@date: Jul 21, 2011
	 *@param date
	 *@return
	 *注释：返回指定日期是周几
	 */
	public static int getDayForWeek(String date) {
		int dayForWeek = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(date));
			if (c.get(Calendar.DAY_OF_WEEK) == 1) {
				dayForWeek = 7;
			} else {
				dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dayForWeek;
	}
	/**
	 * 获取日期时间天差
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDifferenceOfDays(Date startDate, Date endDate) {
		try {
			Calendar firstDay = Calendar.getInstance();
			Calendar lastDay = Calendar.getInstance();
			firstDay.setTime(startDate);
			lastDay.setTime(endDate);
			//算出天数总差值
			long allDays = ((lastDay.getTimeInMillis()) - (firstDay.getTimeInMillis())) / (1000 * 24 * 60 * 60);
			return allDays;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取日期时间天差
	 * @param startDate  字符串格式yyyyMMdd
	 * @param endDate    字符串格式yyyyMMdd
	 * @return
	 */
	public static long getDifferenceOfDays(String startDate, String endDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(startDate);
			date2 = format.parse(endDate);
			return getDifferenceOfDays(date1, date2) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取日期时间月差
	 * @param startDate  字符串格式yyyyMM
	 * @param endDate    字符串格式yyyyMM
	 * @return
	 */
	public static long getDifferenceOfMonths(String startDate, String endDate) {
		try {
			if (startDate.equals(endDate))
				return 1;
			int monthsub = (NumberUtils.toInt(endDate.substring(0, 4), 0) - NumberUtils.toInt(startDate.substring(0, 4), 0)) * 12 + (NumberUtils.toInt(endDate.substring(4, 6), 0)
					- NumberUtils.toInt(startDate.substring(4, 6), 0)) + 1;
			return monthsub;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 *
	 *@author :cc
	 *@date: Sep 30, 2011
	 *@param startDate
	 *@param endDate
	 *@return
	 *注释：
	 */
	public static long getDifferenceOfDate(String startDate, String endDate) {
		try {
			if (startDate.length() == 8 && endDate.length() == 8)
				return DateUtils.getDifferenceOfDays(startDate, endDate);
			else if (startDate.length() == 6 && endDate.length() == 6)
				return DateUtils.getDifferenceOfMonths(startDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 *
	 *  Description：返回日期之间的list
	 *  date:2014-3-30 - 下午4:34:29
	 *  @author cc
	 *  @tags @param statDate
	 *  @tags @param endDate
	 *  @tags @param dataCycle
	 *  @tags @return
	 */
	public static List<String> getDifferenceOfDate(String statDate, String endDate, String dataCycle) {
		List<String> dateList = new ArrayList<String>();
		if ("1".equals(dataCycle)) {
			long count = DateUtils.getDifferenceOfDays(statDate, endDate);
			for (int i = 0; i < count; i++) {
				dateList.add(DateUtils.getDateBefore(statDate, -i));
			}
		} else if ("2".equals(dataCycle)) {
			long count = DateUtils.getDifferenceOfMonths(statDate, endDate);
			for (int i = 0; i < count; i++) {
				dateList.add(DateUtils.getMonthBefore(statDate, -i));
			}
		}
		return dateList;
	}
	/**
	 *
	 *@author :cc
	 *@date: Oct 19, 2011
	 *@param date
	 *@param weekId
	 *@return
	 *注释：获取每月第几周是全年的第几周
	 */
	public static long getWeekNum(String date, String weekId) {
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(myFormatter.parse(date + "01"));
			//不用while循环，防止参数错误异常
			int cnt = 0;
			for (int i = 1; i < 100; i++) {
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					if (++cnt == NumberUtils.toInt(weekId)) {
						return cal.get(Calendar.WEEK_OF_YEAR) - 1;
					}
				}
				cal.set(Calendar.DAY_OF_MONTH, i + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 计算时间差 date1-date2
	 * @param date1  格式yyyyMMdd HH
	 * @param date2  格式yyyyMMdd HH
	 * @return XX
	 */
	public static String getHourDiff(String date1, String date2) {
		String ret = "-";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH");
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			long diff = d1.getTime() - d2.getTime();
			long day = diff / (1000 * 60 * 60 * 24);
			long hour = diff / (1000 * 60 * 60) - day * 24;
			//			if(day==0){
			//				ret = ""+hour +"小时";
			//			}else{
			//				ret = ""+ day+"天"+hour+"小时";
			//			}
			ret = (day * 24 + hour) + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 计算时间大差  date1 - date2
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String getDayDiff(String date1, String date2) {
		String ret = "-";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			long diff = d1.getTime() - d2.getTime();
			long day = diff / (1000 * 60 * 60 * 24);
			//			long hour = diff / (1000 * 60 * 60) - day * 24;
			//			if(day==0){
			//				ret = ""+hour +"小时";
			//			}else{
			//				ret = ""+ day+"天"+hour+"小时";
			//			}
			ret = (day) + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 计算时间差 date1-date2
	 * @param date1  格式yyyyMMdd HH
	 * @param date2  格式yyyyMMdd HH
	 * @return XX天XX小时
	 */
	public static String getDayHourDiff(String date1, String date2) {
		String ret = "-";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH");
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			long diff = d1.getTime() - d2.getTime();
			long day = diff / (1000 * 60 * 60 * 24);
			long hour = diff / (1000 * 60 * 60) - day * 24;
			if (day == 0) {
				ret = "" + hour + "小时";
			} else {
				ret = "" + day + "天" + hour + "小时";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取当月最大周
	 * @param month  yyyyMM
	 * @return
	 */
	public static int getMaxWeekNum(String month) {
		int num = 4;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(myFormatter.parse(month));
			int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			SimpleDateFormat myFormatter1 = new SimpleDateFormat("yyyyMMdd");
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(myFormatter1.parse(month + 01));
			cal1.setFirstDayOfWeek(Calendar.MONDAY);
			int week = 0;
			for (int i = 1; i <= day; i++) {
				if (cal1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					week++;
					//log.debug(myFormatter1.format(cal1.getTime()));
				}
				cal1.add(Calendar.DAY_OF_MONTH, 1);
			}
			num = week;
		} catch (ParseException e) {
			num = 4;
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * 根据这月的第几周获取 年的周
	 * @param month
	 * @param num
	 * @return
	 */
	public static int getWeekOFYear(String month, int num) {
		int res = 0;
		int week = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(myFormatter.parse(month + 01));
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			while (true) {
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					week++;
					if (week == num) {
						res = cal.get(Calendar.WEEK_OF_YEAR) - 1;
						//log.debug(myFormatter.format(cal.getTime()));
						break;
					}
				}
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 根据这月的第几周获取 结束时间
	 * @param month
	 * @param num
	 * @return
	 */
	public static String getEndDate(String month, int num) {
		String res = null;
		int week = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(myFormatter.parse(month + 01));
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			while (true) {
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					week++;
					if (week == num) {
						System.out.println(myFormatter.format(cal.getTime()));
						cal.add(Calendar.DAY_OF_MONTH, 6);
						res = myFormatter.format(cal.getTime());
						break;
					}
				}
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 *
	 *  Description：
	 *  date:May 30, 2013 - 7:41:44 PM
	 *  @author cc
	 *  @tags @param statDate
	 *  @tags @return
	 */
	public static String formatDate(String statDate) {
		//校验日期合法性。
		if (null == statDate || "".equals(statDate)) {
			return "";
		}
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sf3 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf4 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			if (statDate.length() == 6) {
				return sf2.format(sf1.parse(statDate));
			}
			if (statDate.length() == 8) {
				return sf4.format(sf3.parse(statDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}
	/**
	 *
	 * @param statDate
	 * @return
	 */
	public static String formatDate2(String statDate) {
		//校验日期合法性。
		if (null == statDate || "".equals(statDate)) {
			return "";
		}
		if (statDate.length() == 8) {
			return NumberUtils.toInt(statDate.substring(4, 6)) + "/" + statDate.substring(6, 8);
		} else
			return statDate;
	}
	public static void main(String[] args) throws Exception {
		//System.out.println(StringUtils.join(DateUtils.getDifferenceOfDate("201407", "201407", "2")));
		System.out.println(DateUtils.formatDate2("20160821"));
		System.out.println(DateUtils.getDayBefore("20160821", 7));
	}

	/**
	 * 获取当前系统年份
	 * @return
	 */
	public static String getCurrentYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return sdf.format(date);
	}
}
