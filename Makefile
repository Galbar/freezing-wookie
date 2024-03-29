CC        = javac

IDIR      = "src:dist/Desastres.jar:dist/AIMA.jar"
SDIR      = src/freezingwookie
ODIR      = src/freezingwookie
CFLAGS    = -classpath $(IDIR)
XFLAGS    = -cp $(IDIR)

EXEC      = freezingwookie.ControladorEstado

MAIN      = ControladorEstado

CLASSES   = \
           Estado \
           Helicoptero \
           Operador \
           Final \
           FuncionHeuristica \
           Solucionador \
           Viaje \
           InputOutput


OBJ       = $(patsubst %, $(ODIR)/%.class,$(MAIN)) $(patsubst %, $(ODIR)/%.class,$(CLASSES))

all: $(OBJ)

$(ODIR)/%.class: $(SDIR)/%.java
	$(CC) $(CFLAGS) $<

.PHONY: run clean geometry2D

run: all
	java $(XFLAGS) $(EXEC)

clean:
	rm src/freezingwookie/*.class src/cc/alessio/geometry2D/*.class
