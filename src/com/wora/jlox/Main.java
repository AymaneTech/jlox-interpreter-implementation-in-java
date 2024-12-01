package com.wora.jlox;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

class Main {
    private static boolean hadError = false;

    public static void main(String[] args) throws Exception {
        if (args.length > 1) {
            System.err.println("Usage: jlox file.lox");
        } else if (args.length == 1) {
            readFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void readFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    private static void runPrompt() {
        try (Scanner scanner = new Scanner(System.in)) {
            while(true) {
                System.out.print(">> ");
                String line = scanner.nextLine();
                if(line == null) break;

                run(line);
            }
        }
    }

    private static void run(String source) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();
        tokens.forEach(token -> System.out.println(token.type() + "    " +token.literal()));
    }
}
