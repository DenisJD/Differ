.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app

run-test:
	./build/install/app/bin/app -f json file1.yaml file2.yaml

run-h:
	./build/install/app/bin/app -h

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

build:
	./gradlew clean
	./gradlew installDist

report:
	./gradlew jacocoTestReport

.PHONY: build