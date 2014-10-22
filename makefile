JAVAC=javac
JAVA=java
MAIN=MainFrame
sources = $(wildcard *.java)
classes = $(sources:.java=.class)
CLASSPATH= .:itextpdf-5.4.1.jar 
all:	$(classes)

clean :
		rm -f *.class

%.class : %.java
		 $(JAVAC) -classpath $(CLASSPATH) $<
run: $(MAIN).class
	     java -cp $(CLASSPATH) $(MAIN)
jar:
		 jar cfm DNAPen.jar MANIFEST.MF *.class *.java


