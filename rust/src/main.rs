use std::env;
use num_integer::Roots;


fn factorize(n: u128) -> Vec<u128> {
    let mut factors = Vec::new();
    let mut n = n;
    let mut i = 2;
    while i <= n.sqrt() + 1 {
        if n % i == 0 {
            factors.push(i);
            n /= i;
        } else {
            i += 1;
        }
    }
    factors
}


fn main() {
    
    let args: Vec<String> = env::args().collect();

    if args.len() < 2 {
        println!("Usage: {} <number>", args[0]);
        return;
    }

    let n = args[1].parse::<u128>().unwrap_or_else(
        |_| {
            println!("Error: {} is not a.", args[1]);
            std::process::exit(1);
        }
    );
    
    let factors = factorize(n);

    if factors.len() == 0 {
        println!("{} is prime.", n);
        return;
    }

    println!("{} = {}", n, factors.iter().map(|x| x.to_string()).collect::<Vec<String>>().join(" * "));

}
