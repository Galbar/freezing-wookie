all: src/freezingwookie/*.java
	javac -classpath "src:dist/Desastres.jar" src/freezingwookie/ControladorEstado.java

.PHONY: run clean

run: all
	java -cp src:dist/Desastres.jar freezingwookie.ControladorEstado

clean:
	rm src/freezingwookie/*.class