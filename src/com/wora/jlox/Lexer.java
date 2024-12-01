package com.wora.jlox;

import static com.wora.jlox.TokenType.*;

import java.util.ArrayList;
import java.util.List;

class Lexer {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start;
    private int current;
    private int line = 1;

    public Lexer(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while (!isAtEof()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = nextChar();
        switch (c) {
            case '(' -> addToken(LEFT_PAREN);
            case ')' -> addToken(RIGHT_PAREN);
            case '{' -> addToken(LEFT_BRACE);
            case '}' -> addToken(RIGHT_BRACE);
            case ',' -> addToken(COMMA);
            case '.' -> addToken(DOT);
            case '-' -> addToken(MINUS);
            case '+' -> addToken(PLUS);
            case ';' -> addToken(SEMICOLON);
            case '*' -> addToken(STAR);

            case '!' -> addToken(match('=') ? BANG_EQUAL : BANG);
            case '=' -> addToken(match('=') ? EQUAL_EQUAL : EQUAL);
            case '<' -> addToken(match('=') ? LESS_EQUAL : LESS);
            case '>' -> addToken(match('=') ? GREATER_EQUAL : GREATER);

            case '/' -> {
                if (match('/')) {
                    skipComment();
                    return;
                }
                addToken(SLASH);
            }

            case '"' -> readString();
            case '\t', '\r', ' ' -> {
            }
            case '\n' -> line++;

            default -> {
                if (isDigit(c)) {
                    readNumber();
                } else {
                    // TODO: create a global error handler
                    System.out.println("Unexpected character");
        }
    }

    private void readString() {
        while (peek() != '"' && !isAtEof()) {
            if (peek() == '\n')
                line++;
            nextChar();
        }

        if (isAtEof()) {
            // TODO: Create a globa error handler
            System.out.println("String is not complete");
            return;
        }

        nextChar();
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    private void readNumber() {
        while (isDigit(peek()))
            nextChar();
        if (peek() == '.' && isDigit(peekNext())) {
            nextChar();

            while (isDigit(peek()))
                nextChar();
        }
        addToken(STRING, Double.parseDouble(source.substring(start, current)));
    }
    /*
     * this method used to detect if there is two charcters
     */
    private boolean match(char expected) {
        if (source.charAt(current) != expected)
            return false;

        current++;
        return true;
    }

    private void addToken(TokenType type) {
        tokens.add(new Token(type, "", null, line));
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private char nextChar() {
        current++;
        return source.charAt(current - 1);
    }

    private boolean isAtEof() {
        return current >= source.length();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void skipComment() {
        while (peek() != '\n' && !isAtEof())
            nextChar();
    }

    private char peek() {
        if (isAtEof())
            return '\0';
        return source.charAt(current);
    }

    private char peekNext() {
        if (source.charAt(current + 1) >= source.length())
            return '\0';
        return source.charAt(current + 1);
    }
}
