all:
	jflex src/LexicalAnalyzer.flex
	javac -d bin -cp src/ src/Main.java
	jar cfe dist/part2.jar Main -C bin .

testing:
	java -jar dist/part2.jar -wt tree.tex test/euclid.co
	pdflatex tree.tex > /dev/null

clean:
	rm -f src/LexicalAnalyzer.java
	rm -f src/*.class
	rm -f src/*.java~
	rm -rf dist/*
	rm -rf bin/*
	rm -rf doc/javadoc

javadoc:
	cd src; javadoc -private -d ../doc/javadoc *.java

main:
	cd src; javac Main.java; java Main ../test/euclid.co; cd ..;