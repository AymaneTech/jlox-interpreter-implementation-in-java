package com.wora.jlox;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: jlox file.lox");
        } else if (args.length == 1) {
            readFile(args[0]);
        } else {
            System.out.println("this feature not implemented yet!");
        }
    }

    public static void readFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String file = new String(bytes, Charset.defaultCharset());
        System.out.println(file);
    }
}
