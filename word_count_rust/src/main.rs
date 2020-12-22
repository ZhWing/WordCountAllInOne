use std::fs;
use std::env;
use std::process;
use std::collections::HashMap;

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() != 2 {
        eprintln!("Usage: {} [input-file]", &args[0]);
        process::exit(1);
    }

    let filename = &args[1];
    let contents = fs::read_to_string(filename).unwrap();

    let mut counter = HashMap::new();

    for line in contents.lines() {
        for word in line.split(',').collect::<Vec<&str>>() {
            let count = counter.entry(word).or_insert(0);
            *count += 1;
        }
    }

    for (k, v) in &counter {
        println!("{}\t{}", k, v);
    }
}
