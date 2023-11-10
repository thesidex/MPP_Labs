package prob3;

import java.util.function.BiPredicate;

public class MyClass {
	int x;
	String y;

	public MyClass(int x, String y) {
		this.x = x;
		this.y = y;
	}

	// Method using method reference this::equals
    public boolean myMethod(MyClass cl) {
        BiPredicate<MyClass, MyClass> equalsPredicate = MyClass::equals;
        return equalsPredicate.test(this, cl);
    }

	@Override
	public boolean equals(Object ob) {
		if (ob == null) return false;
		if (ob.getClass() != getClass()) return false;
		MyClass mc = (MyClass) ob;
		return mc.x == x && mc.y.equals(y);
	}

	public static void main(String[] args) {
		MyClass myclass = new MyClass(1, "A");
		MyClass myclass1 = new MyClass(1, "B");
		
        // Testing equality using myMethod
        System.out.println("myclass is equal to myclass1: " + myclass.myMethod(myclass)); // true
        System.out.println("myclass is equal to myclass2: " + myclass.myMethod(myclass1)); // false
   
	}
}