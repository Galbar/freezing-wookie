all:
	javac -classpath dist/Desastres.jar IA/freezingwookie/Estado.java

.PHONY: run

run: all
	java -cp .:dist/Desastres.jar IA.freezingwookie.Estado