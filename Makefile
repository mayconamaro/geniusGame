
target: all

all: 
	javac -d bin/ src/genius/controle/*.java src/genius/gui/*.java src/genius/sons/*.java
	cp src/genius/gui/gmail.jpg bin/genius/gui/
	cp src/genius/sons/*.wav bin/genius/sons/
	echo "target: run\n\nrun:\n\tjava genius.gui.TelaDeJogo" >> bin/Makefile

run: 
	$(MAKE) -C bin/
