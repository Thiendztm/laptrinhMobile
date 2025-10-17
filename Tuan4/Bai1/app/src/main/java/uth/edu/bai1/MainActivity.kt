package uth.edu.bai1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

sealed class Patron(open val id: String, open val name: String) {
    abstract fun getPatronDetails(): String
}

data class Student(
    override val id: String,
    override val name: String,
    val borrowedBooks: List<Book> = emptyList()
) : Patron(id, name) {
    val isBorrowed: Boolean get() = borrowedBooks.isNotEmpty()

    override fun getPatronDetails(): String {
        return "ID: $id, Tên: $name, Loại: Sinh viên"
    }
}

data class Book(val id: String, val title: String, val author: String)

class LibraryViewModel : ViewModel() {

    private val _students = mutableStateOf(listOf(
        Student("079205010726","Trần Minh Thiện"),
        Student("077200100028","Phùng Thanh Độ"),
        Student("022991919199","Bó Đạt Chách")
    ))
    private val _books = mutableStateOf(listOf(
        Book("A01", "500 Bài Code Thiếu nhi", "A"),
        Book("A02", "Con cáo và chùm nho", "B"),
        Book("A03","Lập trình Android", "C")
    ))
    private val _selectedStudent = mutableStateOf<Student?>(null)

    val students: State<List<Student>> = _students
    val books: State<List<Book>> = _books
    val selectedStudent: State<Student?> = _selectedStudent

    fun onAddStudent(student: Student) {
        _students.value = _students.value + student
    }

    fun onAddBook(book: Book) {
        _books.value = _books.value + book
    }

    fun onStudentSelected(student: Student) {
        _selectedStudent.value = student
    }

    fun onAddBookToStudent(book: Book) {
        _selectedStudent.value?.let { sv ->
            _students.value = _students.value.map {
                if (it.id == sv.id)
                    it.copy(borrowedBooks = it.borrowedBooks + book)
                else
                    it
            }
            _selectedStudent.value = _students.value.first { it.id == sv.id }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val libraryViewModel: LibraryViewModel = viewModel()
            BottomBar(viewModel = libraryViewModel)
        }
    }
}

@Composable
fun BottomBar(viewModel: LibraryViewModel) {
    val students by viewModel.students
    val books by viewModel.books
    val selectedStudent by viewModel.selectedStudent

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.home),
                            contentDescription = "Home",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Quản lý") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1155C7),
                        selectedTextColor = Color(0xFF1155C7),
                        unselectedIconColor = Color(0xFFCCCCCC),
                        unselectedTextColor = Color(0xFFCCCCCC)
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.book),
                            contentDescription = "DS Sách",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("DS Sách") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1155C7),
                        selectedTextColor = Color(0xFF1155C7),
                        unselectedIconColor = Color(0xFFCCCCCC),
                        unselectedTextColor = Color(0xFFCCCCCC)
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "Sinh viên",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Sinh viên") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF1155C7),
                        selectedTextColor = Color(0xFF1155C7),
                        unselectedIconColor = Color(0xFFCCCCCC),
                        unselectedTextColor = Color(0xFFCCCCCC)
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> QuanLyScreen(
                    students = students,
                    selectedStudent = selectedStudent,
                    onStudentSelected = { viewModel.onStudentSelected(it) },
                    books = books,
                    onAddBookToStudent = { viewModel.onAddBookToStudent(it) }
                )
                1 -> DSSach(books = books, onAddBook = { viewModel.onAddBook(it) })
                2 -> SinhVien(students = students, onAddStudent = { viewModel.onAddStudent(it) })
            }
        }
    }
}

@Composable
fun QuanLyScreen(
    students: List<Student>,
    selectedStudent: Student?,
    onStudentSelected: (Student) -> Unit,
    books: List<Book>,
    onAddBookToStudent: (Book) -> Unit
) {
    var showDropDown by remember { mutableStateOf(false) }
    var showBookSelector by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 32.dp, start = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hệ thống", fontSize = 30.sp)
            Text("Quản lý thư viện", fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text("Sinh viên", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = selectedStudent?.name ?: "",
                    onValueChange = {},
                    readOnly = true,
                    textStyle = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Tên sinh viên") }
                )
                DropdownMenu(
                    expanded = showDropDown,
                    onDismissRequest = { showDropDown = false },
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    students.forEach { student ->
                        DropdownMenuItem(
                            text = { Text(student.name) },
                            onClick = {
                                onStudentSelected(student)
                                showDropDown = false
                            }
                        )
                    }
                }
            }
            Button(
                onClick = { showDropDown = true },
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Thay đổi", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text("Danh sách sách đang mượn", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            if (selectedStudent == null) {
                Text("Vui lòng chọn sinh viên")
            } else if (!selectedStudent.isBorrowed) {
                Text("Bạn chưa mượn quyển sách nào")
            } else {
                Column {
                    selectedStudent.borrowedBooks.forEach { book ->
                        Text("- ${book.title} (${book.author})")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showBookSelector = true },
                modifier = Modifier.height(56.dp),
                enabled = selectedStudent != null,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Thêm", fontSize = 18.sp)
            }
        }

        if (showBookSelector && selectedStudent != null) {
            AlertDialog(
                onDismissRequest = { showBookSelector = false },
                confirmButton = {},
                title = { Text("Chọn sách để mượn") },
                text = {
                    Column {
                        val availableBooks = books.filter { book ->
                            selectedStudent.borrowedBooks.none { it.id == book.id }
                        }
                        availableBooks.forEach { book ->
                            Button(
                                onClick = {
                                    onAddBookToStudent(book)
                                    showBookSelector = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                            ) {
                                Text("${book.title} (${book.author})")
                            }
                        }
                        if (availableBooks.isEmpty()) {
                            Text("Không còn sách nào để mượn!")
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun DSSach(
    books: List<Book>,
    onAddBook: (Book) -> Unit
) {
    var bookId by remember { mutableStateOf("") }
    var bookTitle by remember { mutableStateOf("") }
    var bookAuthor by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Danh sách Sách", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            if (books.isEmpty()) {
                Text("Chưa có sách")
            } else {
                books.forEach { book ->
                    Text("- ${book.id}: ${book.title} (${book.author})")
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = bookId,
                onValueChange = { bookId = it },
                label = { Text("Mã sách") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                label = { Text("Tên sách") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = bookAuthor,
                onValueChange = { bookAuthor = it },
                label = { Text("Tác giả") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (bookId.isNotBlank() && bookTitle.isNotBlank() && bookAuthor.isNotBlank()) {
                    onAddBook(Book(bookId, bookTitle, bookAuthor))
                    bookId = ""
                    bookTitle = ""
                    bookAuthor = ""
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Thêm sách", fontSize = 18.sp)
        }
    }
}

@Composable
fun SinhVien(
    students: List<Student>,
    onAddStudent: (Student) -> Unit
) {
    var studentId by remember { mutableStateOf("") }
    var studentName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Danh sách Sinh viên", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            if (students.isEmpty()) {
                Text("Khong co sinh vien nao")
            } else {
                // --- OOP Principle 4: Polymorphism in action ---
                // Mặc dù `sv` là một đối tượng `Student`, chúng ta có thể coi nó
                // như một `Patron` và gọi các hàm của `Patron`.
                val patronList: List<Patron> = students
                patronList.forEach { patron ->
                    Text(patron.getPatronDetails()) // Gọi hàm đã được override
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                label = { Text("Mã sinh viên") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = studentName,
                onValueChange = { studentName = it },
                label = { Text("Tên sinh viên") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (studentId.isNotBlank() && studentName.isNotBlank()) {
                    onAddStudent(Student(studentId, studentName))
                    studentId = ""
                    studentName = ""
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Thêm sinh vien", fontSize = 18.sp)
        }
    }
}
