{let m = new 5666;
   while !m > 1 {
    if (2*(!m / 2) == !m) {
     m := !m / 2
    } {
     m := 3*!m+1
    };
    println !m
  }
};;