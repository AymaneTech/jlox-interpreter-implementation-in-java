JAVAC = javac
JAVA = java
JFLAGS = -d bin -classpath bin

SRC_DIR = src/main/java
BIN_DIR = bin

SOURCES := $(shell find $(SRC_DIR) -name "*.java")
CLASSES := $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SOURCES))

# Default target: Build the project
all: $(CLASSES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	mkdir -p $(dir $@)
	$(JAVAC) $(JFLAGS) $<

run: all
	$(JAVA) -classpath $(BIN_DIR) com.wora.jlox.Main $(ARGS)

clean:
	rm -rf $(BIN_DIR)

.PHONY: all run clean

