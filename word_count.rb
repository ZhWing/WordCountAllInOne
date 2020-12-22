#!ruby
ARGC = ARGV.length
if ARGC != 1
  puts 'Usage: ./word_count.rb [input-file]'
  exit 1
end

counter = Hash.new(0)

IO.readlines(ARGV[0]).each do |line|
  line.strip.split(',').each do |word|
    counter[word] += 1
  end
end

for k, v in counter
  puts "#{k}\t#{v}"
end
