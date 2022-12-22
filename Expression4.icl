{let x= new 2; let y= new(!x+2); 
	{let z= new(!y+!x); 
		while !z <= 12 
		{z := !z+2};
	println (!z*!x)
	} 
};;