public class ComputerTesting {
    public static void main(String[] args) {
        Computer c1 = new Computer.Builder().BuildCpu("Intel i5 Processor").BuildRam("16").BuildStorage("512").build();

        System.out.println("Computer Specification : "+c1);
    }
}
