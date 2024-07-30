public class TestingEmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeManagementSystem test = new EmployeeManagementSystem(5);

        Employee e1 = new Employee("1", "Namrata Roy", "Manager", 80000);
        Employee e2 = new Employee("2", "Ritvik Sen", "Developer", 60000);
        Employee e3 = new Employee("3", "Tanushree Ghosh", "Designer", 55000);
        Employee e4 = new Employee("4", "Rajat Dutta", "Analyst", 75000);

        test.addEmployee(e1);
        test.addEmployee(e2);
        test.addEmployee(e3);
        test.addEmployee(e4);


        test.traverseEmployees();

        System.out.println("Search for Employee ID 2: " + test.searchEmployee("2"));

        test.deleteEmployee("2");
        test.traverseEmployees();
    }
}
