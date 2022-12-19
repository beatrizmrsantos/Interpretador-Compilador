.class public Header
.super java/lang/Object
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V

       ; set limits used by this method
       .limit locals  10
       .limit stack 256
       ; initialize SL variable to null
       aconst_null
       astore_3
       ; START   

		new frame_0
		dup
		invokespecial frame_0/<init>()V
		dup
		aload_3
		putfield frame_0/sl Ljava/lang/Object;
		astore_3
		new ref_of_int
		dup
		invokespecial ref_of_int/<init>()V
		dup
		sipush 5666
		putfield ref_of_int/v I
		putfield frame_0/v0 Ljava/lang/Object;
		L1:
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		getfield ref_of_int/v I
		sipush 1
		if_icmpgt L3
		iconst_0
		goto L4
		L3:
		iconst_1
		L4:
		ifeq L2
		sipush 2
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		getfield ref_of_int/v I
		sipush 2
		idiv
		imul
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		getfield ref_of_int/v I
		if_icmpeq L7
		iconst_0
		goto L8
		L7:
		iconst_1
		L8:
		ifeq L5
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		getfield ref_of_int/v I
		sipush 2
		idiv
		dup
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		swap
		putfield ref_of_int/v I
		goto L6
		L5:
		sipush 3
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		getfield ref_of_int/v I
		imul
		sipush 1
		iadd
		dup
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		swap
		putfield ref_of_int/v I
		L6:
		pop
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload_3
		getfield frame_0/v0 Ljava/lang/Object;
		checkcast ref_of_int
		getfield ref_of_int/v I
		invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
		invokevirtual java/io/PrintStream/println(I)V
		pop
		goto L1
		L2:
		aload_3
		getfield frame_0/sl Ljava/lang/Object;
		astore_3

		; END
       return
.end method
