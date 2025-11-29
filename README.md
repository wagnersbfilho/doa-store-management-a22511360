## DOA Store Management System

#### Student Information
    - Wagner Filho
    - a22511360
    
#### Project Description
    - This Project is a console-based Java application that manages the core operations of Jewelry  Store. The application is a lightweight solution that does not depend on database systems.

#### Package structure explanation
pt.ipp.estg.doa.store <br>
│<br>
├── employees<br>
│   ├── Employee.java<br>
│   ├── Salesperson.java<br>
│   ├── Manager.java<br>
│   └── EmployeeManager.java (CRUD operations)<br>
│<br>
├── jewelry<br>
│   ├── Jewelry.java<br>
│   ├── Necklace.java<br>
│   ├── Earring.java<br>
│   ├── Ring.java<br>
│   ├── JewelryType.java (enum)<br>
│   ├── Category.java (enum)<br>
│   └── JewelryManager.java (CRUD operations)<br>
│<br>
├── customers<br>
│   ├── Customer.java<br>
│   └── CustomerManager.java (CRUD operations)<br>
│<br>
├── orders<br>
│   ├── Order.java<br>
│   ├── OrderItem.java<br>
│   ├── OrderStatus.java (enum)<br>
│   └── OrderManager.java (CRUD operations)<br>
│<br>
├── payments<br>
│   ├── Payment.java<br>
│   ├── PaymentMethod.java (enum)<br>
│   └── PaymentManager.java (CRUD operations)<br>
│<br>
├── utils<br>
│   ├── CSVUtil.java<br>
│   ├── ValidationUtil.java<br>
│   ├── Persistable.java (interface)<br>
│   └── Searchable.java (interface)<br>
│<br>
└── Main.java (entry point with console menu)<br>

##### Package Descriptions
- employees: All employee-related classes and management logic
- jewelry: Jewelry hierarchy and inventory management
- customers: Customer entity and management
- orders: Order processing, order items, and status management
- payments: Payment processing and method handling
- utils: Utility classes, interfaces, and helper methods
- Main: Application entry point with user interface (console menu)

#### Key features implemented

#### References used

    