#include <stdio.h>
#include <string.h>
#include <stddef.h>
#include <assert.h>
#define MAXBUF 1024

size_t counter[64];
char chars[sizeof counter / sizeof(size_t)][MAXBUF / 2];
size_t size = 0;

void pseudoHashValueIncByKey(const char*);

int main(int argc, char* argv[])
{
  if (argc != 2)
  {
    printf("Usage: %s [%s]\n", argv[0], "input-file");
    return 1;
  }

  FILE* fp = fopen(argv[1], "r");
  if (!fp)
  {
    fprintf(stderr, 
      "Input file `%s` doesn't exist or can\'t open.\n", argv[1]);
    return -1;
  }

  char line[MAXBUF];
  while (fgets(line, MAXBUF, fp))
  {
    size_t lineLen = strlen(line);
    while (line[lineLen - 1] == '\n')
    {
      line[lineLen - 1] = '\0';
      --lineLen;
    }
    size_t wordHead = 0;
    for (size_t i = 0; i != lineLen; ++i)
    {
      if (line[i] == ',')
      {
        if (i - wordHead == 0) continue;
        line[i] = '\0';
        pseudoHashValueIncByKey(line + wordHead);
        wordHead = i + 1;
      }
    }
    pseudoHashValueIncByKey(line + wordHead);
  }

  for (size_t i = 0; i != size; ++i)
  {
    printf("%s\t%lu\n", chars[i], counter[i]);
  }

  fclose(fp);
}

void pseudoHashValueIncByKey(const char* key)
{
  assert(size <= sizeof counter / sizeof(size_t));

  size_t i;
  for (i = 0; i != size; ++i)
  {
    if (strcmp(chars[i], key) == 0)
    {
      ++counter[i];
      break;
    }
  }
  if (i == size)
  {
    strcpy(chars[size++], key);
    ++counter[i];
  }
}
