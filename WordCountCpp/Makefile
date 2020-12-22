CC      := g++-10
CCFLAGS := -std=c++20 -W{all,extra} -g

all: WordCount

WordCount: WordCount.cc
	$(CC)   $(CCFLAGS)   -o WordCount   WordCount.cc

PHONY:
clean:
	rm -f  WordCount
	rm -rf WordCount.dSYM
