# MakeFile for the RayTracer project By Al Natour Mazen
JAVAC=javac
JAVA=java
CLASSPATH=src
SRCDIR=src
BINDIR=bin
MAINCLASS=main.RayTracerMain

# Default target
all:
	 @echo ">> Compiling the project.."
	 @mkdir -p $(BINDIR)
	 @mkdir -p outputImages
	 @find $(SRCDIR) -name "*.java" -print | xargs $(JAVAC) -d $(BINDIR) -cp $(CLASSPATH)
	 @echo ">> Compilation done"

# Run the project
run: all
	 @echo ">> Running the project.."
	 @$(JAVA) -cp $(BINDIR) $(MAINCLASS)

# Clean the project
clean:
	 @echo ">> Cleaning the project.."
	 rm -rf $(BINDIR)
	 @echo ">> Cleaning done"