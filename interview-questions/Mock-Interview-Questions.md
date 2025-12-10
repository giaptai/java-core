# Mock Interview Questions - Based on Your CV

> **Dành cho:** Nguyen Giap Tai - Back-end Developer
> **Vị trí:** Intern / Fresher / Entry-level Java Developer
> **CV Highlights:** Java Core/OOP, Spring Boot basics, BankSim project, Apache Kafka thesis

---

## PHẦN 1: GIỚI THIỆU BẢN THÂN (5-10 phút)

### Câu 1: "Tell me about yourself" / "Giới thiệu về bản thân"

**Mục đích:** Kiểm tra communication skills, tóm tắt background

**Cách trả lời tốt:**
```
"My name is Nguyen Giap Tai, I graduated from Saigon University with a degree
in Software Engineering (GPA 7.55/10).

I'm passionate about back-end development, especially Java. During my studies,
I worked on a thesis project about Apache Kafka and built a high-performance
banking simulation system called BankSim using Java, PostgreSQL, and 3-layer
architecture.

I've done a front-end internship at Famisoft where I worked with ReactJS, and
I'm currently working as an entry-level developer at Gotik, developing platforms
with NextJS and PostgreSQL.

I'm looking for a back-end Java position where I can apply my Java Core, OOP
knowledge, and continue learning Spring Boot and microservices."
```

**Lưu ý:**
- Ngắn gọn 1-2 phút
- Highlight: Java skills + BankSim project + Current experience
- Kết thúc với career goal liên quan đến position đang apply

---

### Câu 2: "Why do you want to work as a back-end developer?"

**Cách trả lời:**
```
"I enjoy working on the server-side logic and data management. During my
BankSim project, I found it fascinating to optimize database connections
and handle concurrency - reducing transaction time from 8.9ms to 0.9ms.

I also studied Apache Kafka for my thesis, which gave me insights into
distributed systems and event-driven architecture. These experiences made
me realize I want to focus on back-end development."
```

---

## PHẦN 2: TECHNICAL QUESTIONS - JAVA CORE

### Câu 3: "Explain OOP principles with examples from your projects"

**Họ muốn nghe:**
- 4 principles: Encapsulation, Inheritance, Polymorphism, Abstraction
- Real examples từ BankSim

**Gợi ý trả lời:**

**Encapsulation:**
```
"In my BankSim project, I encapsulated account data with private fields
and public getters/setters:

class Account {
    private String accountNumber;
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }
}

This prevents direct access to balance and ensures validation."
```

**Inheritance:**
```
"I used inheritance for different account types:

class Account { ... }
class SavingsAccount extends Account { ... }
class CheckingAccount extends Account { ... }

This allowed code reuse while adding specific behaviors."
```

**Polymorphism:**
```
"I implemented different transaction types with polymorphism:

interface Transaction {
    void execute();
}

class DepositTransaction implements Transaction { ... }
class WithdrawTransaction implements Transaction { ... }

This made the transaction processing flexible and extensible."
```

---

### Câu 4: "Your BankSim project reduced transaction time from 8.9ms to 0.9ms. How did you achieve that?"

**Đây là ĐIỂM MẠNH trong CV! Họ sẽ hỏi chi tiết!**

**Cách trả lời chi tiết:**

```
"I made several optimizations:

1. Connection Pooling with HikariCP:
   - Before: Creating new DB connection for each transaction
   - After: Reusing connections from pool
   - This eliminated connection overhead (~5-7ms per connection)

2. Prepared Statements:
   - Precompiled SQL queries
   - Reduced parsing time

3. Transaction Batching:
   - Grouped multiple operations in single transaction
   - Reduced commit overhead

4. Concurrency Control:
   - Used proper locking mechanisms
   - Prevented deadlocks while maintaining performance

The biggest impact was HikariCP - it's a high-performance JDBC connection
pool that maintains ready-to-use connections."
```

**Follow-up questions họ có thể hỏi:**
- "What is HikariCP? Why not use other connection pools?"
- "How did you handle concurrency issues?"
- "How did you measure the performance improvement?"

---

### Câu 5: "Explain the 3-layer architecture you used in BankSim"

**Trả lời:**

```
"I used the standard 3-layer architecture:

1. Presentation Layer (UI):
   - Swing components for user interface
   - Handles user input and displays results
   - Example: JButton for transactions, JTable for account list

2. Business Logic Layer (Service):
   - Contains core business rules
   - Example: AccountService, TransactionService
   - Validates transactions, calculates interest, checks balance

3. Data Access Layer (DAO):
   - Communicates with PostgreSQL database
   - Example: AccountDAO, TransactionDAO
   - CRUD operations with JDBC

Benefits:
- Separation of concerns
- Easy to test each layer independently
- Easy to change database or UI without affecting business logic
"
```

**Diagram bạn có thể vẽ:**
```
[Swing UI]
    ↓
[Service Layer]
    ↓
[DAO Layer]
    ↓
[PostgreSQL]
```

---

### Câu 6: "What is the difference between ArrayList and LinkedList?"

**Cơ bản nhưng quan trọng!**

**Trả lời:**

```
ArrayList:
- Internal: Dynamic array
- Access: O(1) - fast random access by index
- Insert/Delete: O(n) - need to shift elements
- Best for: Frequent access, less modification

LinkedList:
- Internal: Doubly-linked list
- Access: O(n) - must traverse from head
- Insert/Delete: O(1) - just change pointers (at beginning/end)
- Best for: Frequent insertions/deletions

In my BankSim project, I used ArrayList for account lists because
I mostly read data and rarely modify the list."
```

---

### Câu 7: "Explain HashMap internal working"

**Intermediate question - thể hiện hiểu sâu!**

**Trả lời:**

```
HashMap uses array + linked list (or tree) structure:

1. Storage:
   - Array of buckets (default 16)
   - Each bucket can hold multiple entries

2. put() operation:
   - Calculate hash code of key
   - Calculate index: index = hashCode % array.length
   - If bucket empty: add entry
   - If bucket has entries: check equals(), update or add

3. Hash Collisions:
   - Before Java 8: Linked list
   - Java 8+: Tree if entries > 8 (for better performance)

4. Load Factor & Resizing:
   - Default load factor: 0.75
   - When size > capacity * 0.75 → resize (double capacity)

Example in my code:
Map<String, Account> accounts = new HashMap<>();
accounts.put("ACC001", account); // O(1) average
```

---

## PHẦN 3: TECHNICAL QUESTIONS - SPRING BOOT

### Câu 8: "You mentioned basic Spring Boot. What components do you know?"

**Trả lời (theo CV: Core, MVC, Security, Data JPA/Hibernate):**

```
"I have basic knowledge of:

1. Spring Core:
   - Dependency Injection (DI) and Inversion of Control (IoC)
   - @Component, @Service, @Repository, @Autowired

2. Spring MVC:
   - @RestController, @RequestMapping
   - Building REST APIs with @GetMapping, @PostMapping
   - Request/Response handling

3. Spring Security:
   - Basic authentication and authorization
   - Configure security rules

4. Spring Data JPA:
   - JpaRepository for CRUD operations
   - Custom queries with @Query
   - Entity mapping with @Entity, @Table, @Column

5. Lombok:
   - @Data, @Getter, @Setter to reduce boilerplate

6. Swagger:
   - API documentation
   - @ApiOperation, @ApiModel

I'm currently expanding my knowledge by building small projects with
these technologies."
```

---

### Câu 9: "What is Dependency Injection? Give an example"

**Fundamental concept!**

**Trả lời:**

```
"Dependency Injection is a design pattern where objects receive their
dependencies from external sources instead of creating them.

Without DI (Tight Coupling):
class AccountService {
    private AccountDAO dao = new AccountDAO(); // Tightly coupled
}

With DI (Loose Coupling):
@Service
class AccountService {
    @Autowired
    private AccountDAO dao; // Injected by Spring
}

Benefits:
- Easy to test (can inject mock objects)
- Easy to change implementation
- Loose coupling

Types of DI in Spring:
1. Constructor Injection (recommended)
2. Setter Injection
3. Field Injection (@Autowired)
"
```

---

### Câu 10: "What is the difference between @Component, @Service, @Repository?"

**Trả lời:**

```
All are Spring stereotype annotations for component scanning, but
have semantic differences:

@Component:
- Generic stereotype for any Spring-managed component
- Base annotation

@Service:
- Specialization of @Component
- Used for service layer (business logic)
- Example: AccountService, TransactionService

@Repository:
- Specialization of @Component
- Used for DAO layer (data access)
- Provides additional exception translation (SQL exceptions → Spring's DataAccessException)
- Example: AccountRepository, TransactionRepository

In my BankSim architecture:
- @Repository for AccountDAO
- @Service for AccountService
- @RestController for REST endpoints
"
```

---

## PHẦN 4: DATABASE & SQL

### Câu 11: "What databases have you worked with?"

**Trả lời (theo CV: MySQL, PostgreSQL):**

```
"I've worked with both MySQL and PostgreSQL:

PostgreSQL (main):
- Used in BankSim project for transaction handling
- Chose it for better concurrency support and ACID compliance
- Integrated with HikariCP for connection pooling

MySQL:
- Used in learning projects
- Familiar with basic operations

I'm comfortable writing SQL queries, creating tables, indexes,
and optimizing queries for performance."
```

---

### Câu 12: "Write a SQL query to find the top 3 customers with highest total transaction amount"

**Practical coding question!**

**Trả lời:**

```sql
SELECT
    customer_id,
    SUM(amount) as total_amount
FROM transactions
GROUP BY customer_id
ORDER BY total_amount DESC
LIMIT 3;

-- With customer details:
SELECT
    c.customer_id,
    c.name,
    SUM(t.amount) as total_amount
FROM customers c
JOIN transactions t ON c.customer_id = t.customer_id
GROUP BY c.customer_id, c.name
ORDER BY total_amount DESC
LIMIT 3;
```

**Giải thích:**
- GROUP BY: Nhóm theo customer
- SUM(): Tính tổng amount
- ORDER BY DESC: Sắp xếp giảm dần
- LIMIT 3: Lấy top 3

---

### Câu 13: "What is a database transaction? ACID properties?"

**Trả lời:**

```
"A transaction is a sequence of operations performed as a single
logical unit of work.

In my BankSim project, a money transfer is a transaction:
1. Deduct from sender account
2. Add to receiver account

Both must succeed or both must fail.

ACID Properties:

A - Atomicity:
- All operations succeed or all fail
- No partial transactions
- Example: Transfer succeeds completely or rolls back

C - Consistency:
- Database remains in valid state
- Example: Total money in system stays constant after transfer

I - Isolation:
- Concurrent transactions don't interfere
- Example: Two transfers from same account don't cause race condition

D - Durability:
- Committed transactions persist even after system failure
- Example: After successful transfer, data is saved permanently

In my BankSim, I used:
- BEGIN TRANSACTION
- COMMIT (if success)
- ROLLBACK (if error)
"
```

---

## PHẦN 5: PROJECT DEEP DIVE - BANKSIM

### Câu 14: "Walk me through your BankSim project architecture"

**Đây là câu hỏi QUAN TRỌNG NHẤT! Chuẩn bị kỹ!**

**Trả lời chi tiết:**

```
"BankSim is a high-performance banking transaction simulator built with Java.

Architecture:
1. 3-Layer Architecture:
   - Presentation: Swing UI (JFrame, JTable, JButton)
   - Business Logic: Service classes (AccountService, TransactionService)
   - Data Access: DAO classes with JDBC

2. Core Features:
   - Account Management: Create, view, update accounts
   - Transactions: Deposit, withdraw, transfer
   - Transaction History: View past transactions

3. Technical Highlights:

   a) HikariCP Connection Pooling:
      - Maintains pool of ready connections
      - Reduced transaction time from 8.9ms to 0.9-1.07ms

   b) Concurrency Control:
      - Synchronized methods for thread-safe operations
      - Database row-level locking
      - Prevents race conditions

   c) PostgreSQL Integration:
      - Tables: accounts, transactions, customers
      - Foreign keys for data integrity
      - Indexes on frequently queried columns

4. Performance Optimization:
   - Prepared Statements: Prevent SQL injection + faster
   - Batch Processing: Multiple operations in one transaction
   - Connection Pooling: Eliminate connection overhead

5. Error Handling:
   - Custom exceptions: InsufficientBalanceException
   - Transaction rollback on errors
   - User-friendly error messages in UI

Current Status: In progress, continuously improving
"
```

**Demo flow bạn có thể trình bày:**
```
User clicks "Transfer"
  → UI validates input
  → TransactionService.transfer()
    → Check sender balance
    → Begin transaction
    → Deduct from sender (AccountDAO.updateBalance)
    → Add to receiver (AccountDAO.updateBalance)
    → Log transaction (TransactionDAO.insert)
    → Commit
  → UI shows success message
```

---

### Câu 15: "What challenges did you face in BankSim and how did you solve them?"

**Behavioral + Technical!**

**Trả lời:**

```
"Challenge 1: Concurrency Issues
Problem: Multiple simultaneous transfers from same account caused race conditions
Solution:
- Used SELECT FOR UPDATE in SQL for row-level locking
- Synchronized critical sections in Java
- Tested with concurrent threads to verify

Challenge 2: Poor Performance
Problem: Each transaction took 8-9ms, too slow for simulation
Solution:
- Analyzed with timing logs
- Identified connection creation as bottleneck
- Implemented HikariCP connection pooling
- Result: 90% improvement (0.9-1.07ms)

Challenge 3: Transaction Consistency
Problem: System crash during transfer could leave inconsistent state
Solution:
- Wrapped operations in database transactions
- Used try-catch-finally with rollback
- Added transaction logging for audit trail

These challenges taught me the importance of:
- Performance profiling before optimization
- Thread safety in concurrent systems
- Transaction management for data consistency
"
```

---

## PHẦN 6: THESIS - APACHE KAFKA

### Câu 16: "Tell me about your Apache Kafka thesis project"

**Trả lời:**

```
"My thesis was about Apache Kafka and its application in event-driven systems.

What I learned:

1. Kafka Fundamentals:
   - Distributed streaming platform
   - Pub-Sub messaging system
   - High throughput, low latency

2. Key Concepts:
   - Topics: Categories of messages
   - Producers: Publish messages to topics
   - Consumers: Subscribe and process messages
   - Partitions: For parallel processing

3. Practical Application:
   - Built a demo system using Kafka
   - Simulated real-time data streaming
   - Implemented producers and consumers in Java

4. Use Cases I studied:
   - Event sourcing
   - Log aggregation
   - Real-time analytics
   - Microservices communication

Score: 8.2/10.0

This thesis gave me understanding of distributed systems and
event-driven architecture, which is valuable for modern back-end
development."
```

---

### Câu 17: "When would you use Kafka vs traditional message queues?"

**Follow-up technical question:**

**Trả lời:**

```
Use Kafka when:
- High throughput needed (millions of messages/sec)
- Need to replay messages (Kafka stores messages)
- Multiple consumers need same message (pub-sub)
- Building event-driven microservices
- Real-time streaming analytics

Use traditional queues (RabbitMQ, ActiveMQ) when:
- Point-to-point messaging (one consumer per message)
- Don't need message replay
- Complex routing rules needed
- Lower throughput is acceptable

In my thesis, I chose Kafka for:
- Scalability
- Message persistence
- Multiple consumers scenario
"
```

---

## PHẦN 7: WORK EXPERIENCE

### Câu 18: "Tell me about your experience at Gotik and Famisoft"

**Trả lời:**

```
"Gotik (Current - Entry-level Developer):
- Researched CRM workflows and delivered UX/UI designs in Visily
- Currently developing full-stack platform with NextJS and PostgreSQL
- Learning Odoo ERP system
- Designing UX/UI for Coffee Shop project

Key Takeaway: Gaining experience in full-stack development and
understanding how back-end connects with front-end.

Famisoft (Front-end Intern, Feb-Apr 2025):
- Integrated lazy-loading with PrimeReact Skeleton in Next.js
  → Improved UX, reduced flicker
- Built booking page with ReactJS + Redux + Formik
  → Better validation, 10% drop-off reduction
- Redesigned dashboards in ReactJS + TypeScript
  → Finished 2 days early

Key Takeaway: Learned to work in a team, follow coding standards,
and deliver features on schedule.

Even though these were front-end focused, they helped me understand:
- How APIs should be designed (from consumer perspective)
- Importance of performance optimization
- User-focused development
"
```

---

### Câu 19: "You have front-end experience. Why switch to back-end?"

**Trả lời:**

```
"While I enjoyed front-end work, I realized my true passion is back-end:

1. Problem-Solving:
   - I enjoy optimizing database queries and system performance
   - My BankSim optimization (8.9ms → 0.9ms) was very satisfying

2. Data and Logic:
   - I prefer working with data structures, algorithms, and business logic
   - My Kafka thesis showed me I like distributed systems

3. Front-end Experience is Valuable:
   - I understand what front-end needs from APIs
   - I can design better REST APIs
   - Full-stack understanding makes me a better back-end developer

4. Career Goal:
   - I want to specialize in Java back-end development
   - Eventually work with microservices, Spring Boot, and cloud platforms

My front-end experience complements my back-end skills rather than
conflicting with them."
```

---

## PHẦN 8: CODING CHALLENGE (LIVE CODING)

### Câu 20: "Implement a method to transfer money between accounts"

**Họ có thể cho bạn viết code trực tiếp!**

**Solution:**

```java
public class BankService {

    public void transfer(Account from, Account to, double amount)
            throws InsufficientBalanceException {

        // Validation
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (from.getBalance() < amount) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Current: " + from.getBalance()
                + ", Required: " + amount
            );
        }

        // Perform transfer
        from.withdraw(amount);
        to.deposit(amount);

        // Log transaction
        logTransaction(from, to, amount);
    }
}

class Account {
    private String accountNumber;
    private double balance;

    public synchronized void withdraw(double amount) {
        this.balance -= amount;
    }

    public synchronized void deposit(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

**Giải thích cho interviewer:**
- Validation trước khi transfer
- Custom exception cho business logic
- Synchronized methods cho thread safety
- Transaction logging cho audit

---

### Câu 21: "Find duplicate numbers in an array"

**Basic algorithm question:**

```java
// Method 1: Using HashSet - O(n) time, O(n) space
public List<Integer> findDuplicates(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    Set<Integer> duplicates = new HashSet<>();

    for (int num : nums) {
        if (seen.contains(num)) {
            duplicates.add(num);
        }
        seen.add(num);
    }

    return new ArrayList<>(duplicates);
}

// Method 2: Using Stream API
public List<Integer> findDuplicates(int[] nums) {
    return Arrays.stream(nums)
        .boxed()
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .filter(e -> e.getValue() > 1)
        .map(Map.Entry::getKey)
        .toList();
}

// Example:
// Input: [1, 2, 3, 2, 4, 5, 3]
// Output: [2, 3]
```

---

## PHẦN 9: BEHAVIORAL QUESTIONS

### Câu 22: "Tell me about a time you faced a difficult problem"

**STAR Method: Situation, Task, Action, Result**

**Example answer:**

```
Situation:
"In my BankSim project, I noticed transaction performance was poor -
around 8-9ms per transaction, which was too slow for a simulation system."

Task:
"I needed to optimize the system to handle more transactions per second."

Action:
"First, I profiled the code to find bottlenecks. I discovered that creating
new database connections for each transaction was the main issue.

I researched connection pooling solutions and chose HikariCP because of its
high performance. I:
1. Added HikariCP dependency
2. Configured connection pool (min/max connections, timeout)
3. Modified DAO layer to use pooled connections
4. Tested with concurrent transactions
5. Measured performance improvement"

Result:
"Transaction time reduced from 8.9ms to 0.9-1.07ms - a 90% improvement.
This allowed the system to handle 10x more transactions per second."

Learning:
"I learned the importance of profiling before optimizing, and that the
right tool (HikariCP) can make a huge difference."
```

---

### Câu 23: "How do you handle deadlines when working on projects?"

**Trả lời:**

```
"I follow these practices:

1. Break Down Tasks:
   - Divide project into smaller tasks
   - Estimate time for each task
   - Example: In Famisoft, I broke dashboard redesign into components

2. Prioritize:
   - Focus on critical features first
   - Example: In booking page, I implemented validation before styling

3. Communicate Early:
   - If I'm stuck, I ask for help quickly
   - Update team on progress regularly

4. Time Management:
   - I use Pomodoro technique for focused work
   - Avoid context switching

Example from Famisoft:
- Dashboard redesign had 1-week deadline
- I finished 2 days early by:
  * Breaking into 3 dashboards (clients, products, employees)
  * Reusing components across dashboards
  * Testing as I developed
"
```

---

### Câu 24: "What do you do when you don't know something?"

**Trả lời:**

```
"I follow a systematic approach:

1. Try to Solve:
   - Google the error message or concept
   - Check official documentation
   - Example: When learning HikariCP, I read official docs first

2. Break Down:
   - Split complex problem into smaller parts
   - Example: Kafka thesis - I learned components separately

3. Experiment:
   - Write small test code to understand
   - Example: Tested HikariCP with different configurations

4. Ask for Help:
   - If stuck after 1-2 hours, I ask seniors or community
   - Stack Overflow, Reddit, GitHub issues

5. Document:
   - Write notes for future reference
   - Example: I document solutions in my personal wiki

Recent Example:
When learning Spring Security, I didn't understand JWT. I:
- Read Spring Security docs
- Followed tutorial with code
- Built a simple login system
- Now I understand it well
"
```

---

## PHẦN 10: QUESTIONS FOR INTERVIEWER

### Câu 25: "Do you have any questions for us?"

**LUÔN CÓ CÂU HỎI! Cho thấy bạn quan tâm!**

**Good questions to ask:**

**About the Role:**
1. "What would be my typical day-to-day responsibilities?"
2. "What technologies and frameworks does the team use?"
3. "Will I work on existing projects or new projects?"
4. "What is the team structure? Who would I work with?"

**About Learning:**
5. "Are there opportunities for learning and training?"
6. "How does the company support junior developers?"
7. "Is there a mentorship program?"

**About Projects:**
8. "What kind of projects would I be working on?"
9. "What is the development workflow? (Agile, Scrum?)"
10. "How do you handle code reviews?"

**About Growth:**
11. "What does the career path look like for this position?"
12. "How do you measure success for this role?"

**About Company:**
13. "What are the biggest challenges the team is facing?"
14. "What do you enjoy most about working here?"

---

## PHẦN 11: TIPS FOR SUCCESS

### Before Interview:

**1. Review Your CV Thoroughly:**
- Có thể explain mọi thứ trong CV
- Chuẩn bị examples cụ thể
- Nhớ metrics: 8.9ms → 0.9ms, 10% drop-off reduction, etc.

**2. Prepare BankSim Demo:**
- Có video demo sẵn
- Có thể live demo nếu họ hỏi
- Giải thích architecture trong 5 phút

**3. Review Java Fundamentals:**
- OOP principles với examples
- Collections (ArrayList, HashMap, etc.)
- Exception handling
- Multithreading basics

**4. Review Spring Boot Basics:**
- DI/IoC
- @Annotations
- REST API basics
- JPA basics

**5. Review SQL:**
- Basic queries (SELECT, JOIN, GROUP BY)
- Transactions
- Indexes

---

### During Interview:

**1. STAR Method for Behavioral:**
- Situation
- Task
- Action
- Result

**2. Think Out Loud:**
- Khi coding, explain your thinking
- "I'm thinking of using HashMap because..."

**3. Ask Clarifying Questions:**
- Nếu không hiểu, hỏi lại
- "Could you clarify what you mean by...?"

**4. Be Honest:**
- Nếu không biết: "I haven't worked with that yet, but I'm eager to learn"
- Đừng BS

**5. Show Enthusiasm:**
- Talk about what you learned
- Show you're passionate about coding

---

### Technical Questions You Should Know:

**Java Core:**
- [ ] OOP 4 principles
- [ ] ArrayList vs LinkedList
- [ ] HashMap internal working
- [ ] Exception handling (try-catch-finally)
- [ ] Interface vs Abstract class
- [ ] equals() and hashCode()

**Collections:**
- [ ] List, Set, Map differences
- [ ] When to use which collection?
- [ ] HashSet vs TreeSet
- [ ] HashMap vs TreeMap

**Database:**
- [ ] SQL queries (JOIN, GROUP BY, aggregate functions)
- [ ] ACID properties
- [ ] Indexes
- [ ] Transactions

**Spring Boot:**
- [ ] What is Spring Boot?
- [ ] Dependency Injection
- [ ] @Component, @Service, @Repository
- [ ] REST API with Spring MVC
- [ ] JPA basics

---

## PHẦN 12: COMMON MISTAKES TO AVOID

### ❌ Don't:

1. **Say "I don't know" and stop**
   - ✅ Instead: "I haven't worked with that, but I understand similar concepts like..."

2. **Memorize answers**
   - ✅ Instead: Understand concepts and explain naturally

3. **Badmouth previous employers**
   - ✅ Instead: Focus on what you learned

4. **Interrupt the interviewer**
   - ✅ Instead: Listen carefully, then answer

5. **Give one-word answers**
   - ✅ Instead: Explain with examples

6. **Overcomplicate answers**
   - ✅ Instead: Start simple, then add details if asked

7. **Say "It's in my CV"**
   - ✅ Instead: Answer the question even if it's in CV

8. **Panic when stuck on coding**
   - ✅ Instead: Think out loud, ask for hints

---

## PHẦN 13: CONFIDENCE BOOSTERS

### Your Strengths (Based on CV):

✅ **Strong Java Foundation:**
- Java Core, OOP
- Practical project (BankSim)
- Understanding of performance optimization

✅ **Relevant Projects:**
- BankSim: Shows technical depth
- Kafka Thesis: Shows learning ability
- Personal projects: Shows initiative

✅ **Problem-Solving:**
- 90% performance improvement in BankSim
- Solved concurrency issues
- Optimization mindset

✅ **Full-Stack Understanding:**
- Front-end experience at Famisoft
- Currently working with NextJS + PostgreSQL
- Understand both sides of development

✅ **Learning Mindset:**
- Self-learning Spring Boot
- Built 10+ personal projects
- Completed certifications

✅ **Communication:**
- TOEIC 680 - can communicate in English
- Experience presenting thesis
- Team collaboration experience

---

## SUMMARY: KEY POINTS TO EMPHASIZE

**Your Elevator Pitch:**
```
"I'm a Java back-end developer with hands-on experience in building
high-performance systems. My BankSim project demonstrates my ability
to optimize performance (90% improvement) and handle concurrency.

I have a solid foundation in Java Core, OOP, and databases, with
basic Spring Boot knowledge that I'm actively expanding. My thesis
on Apache Kafka gave me insights into distributed systems.

I'm looking to join a team where I can contribute my Java skills
while learning from experienced developers."
```

**Top 3 Highlights:**
1. 📊 **Performance Optimization:** 8.9ms → 0.9ms (90% improvement)
2. 🏗️ **Architecture Skills:** 3-layer architecture, proper design patterns
3. 📚 **Learning Ability:** Self-taught Spring Boot, Kafka thesis, continuous learning

---

## FINAL CHECKLIST

**Day Before Interview:**
- [ ] Review this file
- [ ] Test BankSim demo
- [ ] Prepare questions for interviewer
- [ ] Review your CV
- [ ] Get good sleep

**Day of Interview:**
- [ ] Dress professionally
- [ ] Arrive 10 minutes early (or join 5 min early for online)
- [ ] Bring notebook and pen
- [ ] Have CV copies ready
- [ ] Smile and be confident!

**During Interview:**
- [ ] Listen carefully
- [ ] Think before answering
- [ ] Use STAR method for behavioral
- [ ] Ask clarifying questions
- [ ] Show enthusiasm
- [ ] Take notes

**After Interview:**
- [ ] Send thank-you email within 24 hours
- [ ] Reflect on questions you couldn't answer well
- [ ] Study those topics for next time

---

## YOU'VE GOT THIS! 💪

Remember:
- You have strong technical skills (60 exercises done!)
- You have real project experience (BankSim)
- You have work experience (Gotik, Famisoft)
- You're prepared!

**Mindset:**
- Interviews are conversations, not interrogations
- They want you to succeed
- It's okay to say "I don't know" sometimes
- Show your passion for coding
- Be yourself!

Good luck with your interview! 🎉
