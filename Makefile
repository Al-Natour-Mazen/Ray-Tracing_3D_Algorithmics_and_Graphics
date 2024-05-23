# Variables
JAVAC=javac
JAVA=java
CLASSPATH=src
SRCDIR=src
BINDIR=bin
MAINCLASS=RayTracerMain

# Default target
all: compile

# Compile the project
compile:
	mkdir -p $(BINDIR)
	$(JAVAC) -d $(BINDIR) -cp $(CLASSPATH) $(SRCDIR)/*.java

# Run the project
run: compile
	$(JAVA) -cp $(BINDIR) $(MAINCLASS)

# Clean the project
clean:
	rm -rf $(BINDIR)