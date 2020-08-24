package nityaIncCoding;

public class InnerClasses {

	public static void main(String[] args) {

		A a = new InnerClasses().new A();
		C c = new InnerClasses().new C();

		a.b.c = c.b.a;
	}

	class A {
		B b = new InnerClasses().new B();
	}

	class B {
		String c = "";
		String a = "";
	}

	class C {
		B b = new InnerClasses().new B();
	}

}
