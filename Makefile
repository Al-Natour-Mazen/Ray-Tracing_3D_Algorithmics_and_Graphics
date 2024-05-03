JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/Main.java \
	src/objects/Checkerboard.java \
	src/objects/IntersectableObject.java \
	src/objects/Plane.java \
	src/objects/Sphere.java \
	src/scenes/Light.java \
	src/scenes/Scene.java \
	src/utils/Color.java \
	src/utils/JavaTga.java \
	src/utils/Vec3.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) raytracing/*.class
	$(RM) raytracing/*/*.class