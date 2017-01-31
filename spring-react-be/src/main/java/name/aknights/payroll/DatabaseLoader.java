/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package name.aknights.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Greg Turnquist
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository employees;

	@Autowired
	public DatabaseLoader(EmployeeRepository employeeRepository) {
		this.employees = employeeRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.employees.save(new Employee("Frodo", "Baggins", "ring bearer"));
		this.employees.save(new Employee("Bilbo", "Baggins", "burglar"));
		this.employees.save(new Employee("Gandalf", "the Grey", "wizard"));

		this.employees.save(new Employee("Samwise", "Gamgee", "gardener"));
		this.employees.save(new Employee("Merry", "Brandybuck", "pony rider"));
		this.employees.save(new Employee("Peregrin", "Took", "pipe smoker"));
	}
}