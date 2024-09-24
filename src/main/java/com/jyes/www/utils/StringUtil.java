package com.jyes.www.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * String Utility Class
 * 
 * @author ZeroOne
 * @version 1.0, 2009.06.25
 * 
 */

public class StringUtil {

	protected StringUtil() {
		
	}

	/**
	 * 입력값이 null인지를 체크하여 "" 을 돌려줌
	 * 
	 * @param str
	 * @return
	 */
	public static String nvl(String str) {
		String nv = "";
		try {
			if (str == null || str.length() == 0 || str.equals("null")
					|| str.equals("NULL"))
				nv = "";
			else
				nv = str;
		} catch (Exception e) {
			System.out.println("Utilb.nvl" + e.toString());
		}
		return nv;
	}
	
	public static String nvl(String src, String s) {
		src = nvl(src);
		if ("".equals(src))
			return s;
		else
			return src;
	}
	
	public static String convertDateFormat(String regDate, String outputPattern){
		String result = "";
		
		if(regDate != null && !"".equals(regDate)){
			result = regDate;
			
			// DB에서 가져온 데이트 데이터 형태 
	        String inputPattern = "yyyy-MM-dd HH:mm:ss";
	        SimpleDateFormat inputFormatter = new SimpleDateFormat(inputPattern);
	        
	        // 치환 받은 패턴 형태 
	        SimpleDateFormat outputFormatter = new SimpleDateFormat(outputPattern);
	        
	        try {
	            // DB 데이트 데이터를 날짜로 변환
	            Date date = inputFormatter.parse(regDate);

	            // 날짜로 변환 된 데이트를 치환받을 형태로 변환
	            result = outputFormatter.format(date);
	        } catch (ParseException e) {
	        	System.err.print("convertDateFormat reg_date : " + regDate + " , outputPattern : " + outputPattern);
	            System.err.println("convertDateFormat: " + e.getMessage());
	            e.printStackTrace();
	            return "";
	        }
		}
		
		return result;
	}
	
	// 날짜 입력값으로부터 경과된 시간 계산
    public static String calculateTimeElapsed(String inputDate) {
        // 날짜 형식 지정 (예: "yyyy-MM-dd")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (inputDate == null || inputDate.isEmpty()) {
            return ""; // 입력값이 null이거나 빈 문자열일 때 빈 문자열 반환
        }

        LocalDate start;
        try {
            // inputDate를 LocalDate로 변환
            start = LocalDate.parse(inputDate, formatter);
        } catch (DateTimeParseException e) {
            // 날짜 패턴이 맞지 않을 경우 빈 문자열 반환
            return "";
        }

        // 현재 날짜 가져오기
        LocalDate now = LocalDate.now();

        // 두 날짜 사이의 기간 계산 (년, 월, 일)
        Period period = Period.between(start, now);

        // 기간을 년, 월, 일 단위로 가져오기
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        // 결과를 문자열로 포맷팅
        StringBuilder result = new StringBuilder();
        if (years > 0) {
            result.append(years).append("년 ");
        }
        if (months > 0) {
            result.append(months).append("개월 ");
        }
        if (days > 0) {
            result.append(days).append("일 ");
        }

        return result.toString().trim();
    }
	
	// 숫자를 한글 단위로 포맷팅하는 메소드
    public static String formatNumber(Long number) {
        if (number == null) {
            return ""; // null 값 처리
        }
        
        if (number == 0) {
            return "0"; // 0 값 처리
        }
        
        boolean isNegative = number < 0;
        number = Math.abs(number); // 음수 처리
        
        String formattedNumber = numberToKorean(number);
        
        return isNegative ? "-" + formattedNumber : formattedNumber;
    }

    // 숫자를 한글 단위로 포맷팅하는 메소드
    private static String numberToKorean(long number) {
        if (number == 0) return "0";

        String[] units = {"", "만", "억", "조", "경"};
        StringBuilder result = new StringBuilder();

        int unitIndex = 0;
        while (number > 0) {
            long part = number % 10000; // 마지막 4자리 추출
            if (part > 0) {
                result.insert(0, formatFourDigitPart(part) + units[unitIndex]);
            }
            number /= 10000; // 다음 4자리로 이동
            unitIndex++;
        }

        return result.toString();
    }

    // 4자리 숫자를 한글로 포맷팅하는 메소드
    private static String formatFourDigitPart(long part) {
        String[] unitNames = {"", "십", "백", "천"};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < unitNames.length; i++) {
            long digit = part % 10;
            if (digit > 0) {
                result.insert(0, digit + unitNames[i]);
            }
            part /= 10;
        }

        return result.toString();
    }
	
}