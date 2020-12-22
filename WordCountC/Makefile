CC      := gcc-10
CCFLAGS := -std=c17 -W{all,extra} -g

all: WordCount

WordCount: WordCount.c
	$(CC)   $(CCFLAGS)   -o WordCount   WordCount.c

PHONY:
clean:
	rm -f  WordCount
	rm -rf WordCount.dSYM
