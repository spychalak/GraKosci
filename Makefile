
MAIN = Main2

run:
	mvn exec:java -Dexec.mainClass="kosci.si.$(MAIN)"

