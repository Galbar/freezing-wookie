CC        = javac

IDIR      = src:dist/Desastres.jar
SDIR      = src/freezingwookie
ODIR      = src/freezingwookie
CFLAGS    = -classpath $(IDIR) -Xlint
XFLAGS    = -jar dist/AIMA.jar -cp $(IDIR)

EXEC      = freezingwookie.ControladorEstado

MAIN      = ControladorEstado
CLASSES   = Estado Helicoptero

OBJ       = $(patsubst %, $(ODIR)/%.class,$(MAIN)) $(patsubst %, $(ODIR)/%.class,$(CLASSES))

all: $(OBJ)

$(ODIR)/%.class: $(SDIR)/%.java
	$(CC) $(CFLAGS) $<

.PHONY: run clean

run: all
	java $(XFLAGS) $(EXEC)

clean:
	rm src/freezingwookie/*.class