package me.hektortm.woSSystems.utils;

import static me.hektortm.woSSystems.utils.Letters.*;
import static me.hektortm.woSSystems.utils.Letters.STAR;

public class Parsers {
    public String parseUni(String s) {
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            Letters letterEnum = null;

            // Map each character to the corresponding enum value
            if (Character.isLetter(c)) {
                letterEnum = Letters.valueOf(String.valueOf(c).toUpperCase());
            } else if (Character.isDigit(c)) {
                switch (c) {
                    case '0': letterEnum = Letters.ZERO; break;
                    case '1': letterEnum = Letters.ONE; break;
                    case '2': letterEnum = Letters.TWO; break;
                    case '3': letterEnum = Letters.THREE; break;
                    case '4': letterEnum = Letters.FOUR; break;
                    case '5': letterEnum = Letters.FIVE; break;
                    case '6': letterEnum = Letters.SIX; break;
                    case '7': letterEnum = Letters.SEVEN; break;
                    case '8': letterEnum = Letters.EIGHT; break;
                    case '9': letterEnum = Letters.NINE; break;
                }
            } else if (c == '_') {
                letterEnum = Letters.UNDERSCORE;
            } else if (c == '-') {
                letterEnum = Letters.DASH;
            } else if (c == '"') {
                letterEnum = QUOTE;
            } else if (c == '&') {
                letterEnum = AMPERSAND;
            } else if (c == '(') {
                letterEnum = BRACKET_OPEN;
            } else if (c == ')') {
                letterEnum = BRACKET_CLOSED;
            } else if (c == ':') {
                letterEnum = COLON;
            } else if (c == '=') {
                letterEnum = EQUALS;
            } else if (c == '!') {
                letterEnum = EXCLAMATION;
            } else if (c == '#') {
                letterEnum = HASHTAG;
            } else if (c == '+') {
                letterEnum = PLUS;
            } else if (c == '?') {
                letterEnum = QUESTION;
            } else if (c == '/') {
                letterEnum = SLASH;
            } else if (c == ';') {
                letterEnum = SEMICOLON;
            } else if (c == '%') {
                letterEnum = PERCENTAGE;
            } else if (c == '.') {
                letterEnum = DOT;
            } else if (c == ',') {
                letterEnum = COMMA;
            } else if (c == '*') {
                letterEnum = STAR;
            }

            // Append the Unicode value or the original character if no mapping exists
            if (letterEnum != null) {
                result.append(letterEnum.getLetter());
            } else {
                result.append(c); // Keep non-mapped characters as is
            }
        }

        return result.toString();
    }

    public static String parseUniStatic(String s) {
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            Letters letterEnum = null;

            // Map each character to the corresponding enum value
            if (Character.isLetter(c)) {
                letterEnum = Letters.valueOf(String.valueOf(c).toUpperCase());
            } else if (Character.isDigit(c)) {
                switch (c) {
                    case '0': letterEnum = Letters.ZERO; break;
                    case '1': letterEnum = Letters.ONE; break;
                    case '2': letterEnum = Letters.TWO; break;
                    case '3': letterEnum = Letters.THREE; break;
                    case '4': letterEnum = Letters.FOUR; break;
                    case '5': letterEnum = Letters.FIVE; break;
                    case '6': letterEnum = Letters.SIX; break;
                    case '7': letterEnum = Letters.SEVEN; break;
                    case '8': letterEnum = Letters.EIGHT; break;
                    case '9': letterEnum = Letters.NINE; break;
                }
            } else if (c == '_') {
                letterEnum = Letters.UNDERSCORE;
            } else if (c == '-') {
                letterEnum = Letters.DASH;
            } else if (c == '"') {
                letterEnum = QUOTE;
            } else if (c == '&') {
                letterEnum = AMPERSAND;
            } else if (c == '(') {
                letterEnum = BRACKET_OPEN;
            } else if (c == ')') {
                letterEnum = BRACKET_CLOSED;
            } else if (c == ':') {
                letterEnum = COLON;
            } else if (c == '=') {
                letterEnum = EQUALS;
            } else if (c == '!') {
                letterEnum = EXCLAMATION;
            } else if (c == '#') {
                letterEnum = HASHTAG;
            } else if (c == '+') {
                letterEnum = PLUS;
            } else if (c == '?') {
                letterEnum = QUESTION;
            } else if (c == '/') {
                letterEnum = SLASH;
            } else if (c == ';') {
                letterEnum = SEMICOLON;
            } else if (c == '%') {
                letterEnum = PERCENTAGE;
            } else if (c == '.') {
                letterEnum = DOT;
            } else if (c == ',') {
                letterEnum = COMMA;
            } else if (c == '*') {
                letterEnum = STAR;
            }

            // Append the Unicode value or the original character if no mapping exists
            if (letterEnum != null) {
                result.append(letterEnum.getLetter());
            } else {
                result.append(c); // Keep non-mapped characters as is
            }
        }

        return result.toString();
    }

}
