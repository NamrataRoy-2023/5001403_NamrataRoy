public class Computer {
    private String cpu;
    private String ram;
    private String storage;

    public Computer(Builder builder){    //prevent direct instantiation
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

    public static class Builder{         // nested static Builder class
        private String cpu;
        private String ram;
        private String storage;

        public Builder BuildCpu(String cpu){
            this.cpu = cpu;
            return this;
        }

        public Builder BuildRam(String ram){
            this.ram = ram;
            return this;
        }

        public Builder BuildStorage(String storage){
            this.storage = storage;
            return this;
        }

        public Computer build(){                   // build() returns an instance
            return new Computer(this);
        }
    }

    public String toString(){
        return "Computer{ CPU = "+cpu+", RAM = "+ram+", STORAGE = "+storage+" }";
    }
}

