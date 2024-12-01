package com.wora.jlox;

enum TokenType {
    // single-characters
    LEFT_PAREN, RIGT_PAREN, LEFT_BRACE, RIGHT_BRACE, 
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR, 

    // one or two charcters =, ==, !, != ..
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // literals
    IDENTIFIR, STRING, NUMBER,

    // keywords
    IF, ELSE, ELSE_IF,
    AND, OR,
    FUN, WHILE, FOR,
    TRUE, FALSE, NIL,
    PRINT, VAR, RETURN,
    CLASS, THIS, SUPER,

    EOF
}
