#!python
import sys

if __name__ == '__main__':

  argv = sys.argv
  argc = len(sys.argv)

  if argc != 2:
    print("Usage: %s [%s]" % (sys.argv[0], 'input-file'))
    exit(1)

  counter = dict()
  
  with open(sys.argv[1], 'r') as fp:
    lines = fp.readlines()
    for line in lines:
      words = filter(lambda x: x, line.strip().split(','))
      for word in words:
        counter.setdefault(word, 0)
        counter[word] += 1
  
  for k, v in counter.items():
    print('%s\t%d' % (k, v))
