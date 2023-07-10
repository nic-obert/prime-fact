use std::env;
use num_integer::Roots;


fn factorize(mut n: u128) -> Vec<u128> {
    let mut factors = Vec::new();

    // 2 is the only even prime
    while n % 2 == 0 {
        factors.push(2);
        n /= 2;
    }

    let mut i = 3;
    while i <= n.sqrt() + 1 {
        if n % i == 0 {
            factors.push(i);
            n /= i;
        } else {
            i += 2;
        }
    }

    if n > 2 {
        factors.push(n);
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
