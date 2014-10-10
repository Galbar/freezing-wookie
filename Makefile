CC        = javac

IDIR      = src:dist/Desastres.jar
SDIR      = src/freezingwookie
ODIR      = src/freezingwookie
CFLAGS    = -classpath $(IDIR) -Xlint
LIBS      = 

EXEC      = freezingwookie.ControladorEstado

MAIN      = ControladorEstado
CLASSES   = Estado Helicoptero

OBJ       = $(patsubst %, $(ODIR)/%.class,$(MAIN)) $(patsubst %, $(ODIR)/%.class,$(CLASSES))

all: $(OBJ)

$(ODIR)/%.class: $(SDIR)/%.java
	$(CC) $(CFLAGS) $<

.PHONY: run clean

run: all
	java -cp src:dist/Desastres.jar freezingwookie.ControladorEstado

clean:
	rm src/freezingwookie/*.class