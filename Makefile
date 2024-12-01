JAVAC = javac
JAVA = java
JFLAGS = -d bin

SRC_DIR = src
BIN_DIR = bin

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Default target: Build the project
all:
	mkdir -p $(BIN_DIR)
	$(JAVAC) $(JFLAGS) $(SOURCES)

run: all
	$(JAVA) -classpath $(BIN_DIR) com.wora.jlox.Main $(ARGS)

clean:
	rm -rf $(BIN_DIR)

.PHONY: all run clean

