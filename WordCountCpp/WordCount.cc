#include <iostream>       // basic I/O
#include <fstream>        // std::fstream
#include <string>         // std::string
#include <map>            // std::map
#include <algorithm>      // std::for_each
#include <iterator>       // std::begin, std::end, std::ostream_iterator

int main(int argc, char* argv[])
{
  if (argc != 2) {
    std::cout << "Usage: " << argv[0] << " [input-file]" << std::endl;
    return 1;
  }
  std::fstream fs (argv[1], fs.in);
  if (!fs) {
    std::cerr << "Input file `" << argv[1] << "`"
              << " doesn't exist or can\'t open." << std::endl;
    return -1;
  }

  std::map<std::string, ::size_t> counter;
  std::string perline;
  while (std::getline(fs, perline)) {
    ::size_t head = 0, rear = -1;
    // split line
    while ((rear = perline.find(",", rear + 1)) != std::string::npos) {
      auto word = perline.substr(head, rear - head);
      ++counter[word];           // add each word
      head = rear + 1;           // alter head
    }
    // and add the last word
    auto last_one = perline.substr(head);
    ++counter[last_one];
  }
  // Output results
  std::for_each(std::begin(counter), std::end(counter), 
    [](auto&& p) {
      std::cout << p.first << "\t" << p.second << std::endl;
    }
  );
  return 0;
}
