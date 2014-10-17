CC        = javac

IDIR      = "src:dist/Desastres.jar:dist/AIMA.jar"
SDIR      = src/freezingwookie
ODIR      = src/freezingwookie
CFLAGS    = -classpath $(IDIR) -Xlint
XFLAGS    = -cp $(IDIR)

EXEC      = freezingwookie.VoronoiTest

MAIN      = VoronoiTest
CLASSES   = VoronoiTest 

OBJ       = $(patsubst %, $(ODIR)/%.class,$(MAIN)) $(patsubst %, $(ODIR)/%.class,$(CLASSES))

all: $(OBJ) geometry2D

geometry2D:
	javac src/cc/alessio/geometry2D/*.java

$(ODIR)/%.class: $(SDIR)/%.java
	$(CC) $(CFLAGS) $<

.PHONY: run clean geometry2D

run: all
	java $(XFLAGS) $(EXEC)

clean:
	rm src/freezingwookie/*.class src/cc/alessio/geometry2D/*.class