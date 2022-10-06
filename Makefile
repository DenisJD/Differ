.DEFAULT_GOAL := build-run

run-dist:
	make -C app run-dist

run-test:
	make -C app run-test

run-h:
	make -C app run-h

lint:
	make -C app lint

build:
	make -C app build

report:
	make -C app report

.PHONY: build