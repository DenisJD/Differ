.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app

run-test:
	./build/install/app/bin/app src/main/resources/file3.json src/main/resources/file4.json

run-h:
	./build/install/app/bin/app -h

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

build:
	./gradlew clean
	./gradlew installDist

.PHONY: build