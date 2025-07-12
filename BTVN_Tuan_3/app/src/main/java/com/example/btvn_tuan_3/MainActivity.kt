package com.example.btvn_tuan_3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource // Import này cần thiết để load ảnh từ drawable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign // Import này cần cho TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import coil.compose.AsyncImage
import com.example.btvn_tuan_3.ui.theme.BTVN_Tuan_3Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import java.time.format.TextStyle


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVN_Tuan_3Theme {
                JetpackComposeApp()
            }
        }
    }
}

@Composable
fun JetpackComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") { WelcomeScreen(navController) }
        composable("components") { ComponentsListScreen(navController) }
        composable("text_detail") { TextDetailScreen(navController) }
        composable("image_detail") { ImageDetailScreen(navController) }
        composable("button_detail") { ButtonDetailScreen(navController) }
        composable("checkbox_detail") { CheckboxDetailScreen(navController) }
        composable("radiobutton_detail") { RadioButtonDetailScreen(navController) }
        composable("icon_detail") { IconDetailScreen(navController) }
        composable("progressbar_detail") { ProgressBarDetailScreen(navController) }
        composable("textfield_detail") { TextFieldDetailScreen(navController) }
        composable("passwordfield_detail") { PasswordFieldDetailScreen(navController) }
        composable("switch_detail") { SwitchDetailScreen(navController) }
        composable("slider_detail") { SliderDetailScreen(navController) }
        composable("dropdown_detail") { DropdownDetailScreen(navController) }
        composable("column_detail") { ColumnDetailScreen(navController) }
        composable("row_detail") { RowDetailScreen(navController) }
        composable("spacer_detail") { SpacerDetailScreen(navController) }
        composable("card_detail") { CardDetailScreen(navController) }
        composable("iconbutton_detail") { IconButtonDetailScreen(navController) }
        composable("fab_detail") { FloatingActionButtonDetailScreen(navController) }
        composable("snackbar_detail") { SnackbarDetailScreen(navController) }
        composable("dialog_detail") { DialogDetailScreen(navController) }
        composable("topappbar_detail") { TopAppBarDetailScreen(navController) }
        composable("bottomnav_detail") { BottomNavigationDetailScreen(navController) }
        composable("drawer_detail") { DrawerDetailScreen(navController) }
        composable("box_detail") { BoxDetailScreen(navController) }
        composable("surface_detail") { SurfaceDetailScreen(navController) }
        composable("scaffold_detail") { ScaffoldDetailScreen(navController) }
        composable("annotatedtext_detail") { AnnotatedTextDetailScreen(navController) }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Jetpack Compose",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = { navController.navigate("components") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "I'm ready",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

data class ComponentItem(
    val category: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(navController: NavController) {
    val components = listOf(
        ComponentItem("Display", "Text", "Displays text", icon = Icons.Default.Edit, route = "text_detail"),
        ComponentItem("Display", "Image", "Displays an image", icon = Icons.Default.Image, route = "image_detail"),
        ComponentItem("Display", "Button", "Displays a clickable button", icon = Icons.Default.TouchApp, route = "button_detail"),
        ComponentItem("Display", "Checkbox", "Checkbox component", Icons.Default.CheckBox, "checkbox_detail"),
        ComponentItem("Display", "RadioButton", "Radio button", Icons.Default.RadioButtonChecked, "radiobutton_detail"),
        ComponentItem("Display", "Icon", "Display an icon", Icons.Default.Info, "icon_detail"),
        ComponentItem("Display", "ProgressBar", "Progress indicator", Icons.Default.HourglassEmpty, "progressbar_detail"),
        ComponentItem("Input", "TextField", "Input field for text", icon = Icons.Default.Edit, route = "textfield_detail"),
        ComponentItem("Input", "PasswordField", "Input field for passwords", icon = Icons.Default.Lock, route = "passwordfield_detail"),
        ComponentItem("Input", "Switch", "On/off switch", Icons.Default.ToggleOn, "switch_detail"),
        ComponentItem("Input", "Slider", "Slider input", Icons.Default.Tune, "slider_detail"),
        ComponentItem("Input", "DropdownMenu", "Dropdown menu", Icons.Default.ArrowDropDown, "dropdown_detail"),
        ComponentItem("Layout", "Column", "Arranges elements vertically", icon = Icons.Default.ViewColumn, route = "column_detail"),
        ComponentItem("Layout", "Row", "Arranges elements horizontally", icon = Icons.Default.HorizontalSplit, route = "row_detail"),
        ComponentItem("Layout", "Spacer", "Empty space", Icons.Default.SpaceBar, "spacer_detail"),
        ComponentItem("Layout", "Card", "Card with shadow", Icons.Default.CreditCard, "card_detail"),
        ComponentItem("Action", "IconButton", "Icon-only button", Icons.Default.Star, "iconbutton_detail"),
        ComponentItem("Action", "FloatingActionButton", "Circular action button", Icons.Default.Add, "fab_detail"),
        ComponentItem("Feedback", "Snackbar", "Temporary popup message", Icons.Default.Message, "snackbar_detail"),
        ComponentItem("Feedback", "Dialog", "Pop-up dialog window", Icons.Default.Notifications, "dialog_detail"),
        ComponentItem("Navigation", "TopAppBar", "App bar at top", Icons.Default.Menu, "topappbar_detail"),
        ComponentItem("Navigation", "BottomNavigation", "Bottom nav bar", Icons.Default.MoreHoriz, "bottomnav_detail"),
        ComponentItem("Navigation", "Drawer", "Side navigation panel", Icons.Default.MenuOpen, "drawer_detail"),
        ComponentItem("Container", "Box", "Generic container", Icons.Default.AllInbox, "box_detail"),
        ComponentItem("Container", "Surface", "Styled container surface", Icons.Default.Layers, "surface_detail"),
        ComponentItem("Container", "Scaffold", "Basic layout structure", Icons.Default.ViewAgenda, "scaffold_detail"),
        ComponentItem("Typography", "AnnotatedString", "Rich styled text", Icons.Default.TextFormat, "annotatedtext_detail")

    )

    val groupedComponents = components.groupBy { it.category }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("UI Components List", color = MaterialTheme.colorScheme.primary, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            groupedComponents.forEach { (category, items) ->
                item {
                    Text(
                        text = category,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                    )
                }
                items(items) { component ->
                    ComponentCard(component) {
                        navController.navigate(component.route)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentCard(component: ComponentItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB3D8FF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = component.icon,
                    contentDescription = component.title,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = component.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = component.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "TextField Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    Text(text = "The quick ", fontSize = 18.sp)
                    Text(
                        text = "Brown",
                        fontSize = 18.sp,
                        color = Color(0xFFFF9800),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row {
                    Text(text = "fox j u m p s ", fontSize = 18.sp)
                    Text(
                        text = "over",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row {
                    Text(
                        text = "the",
                        fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline
                    )
                    Text(text = " ", fontSize = 18.sp)
                    Text(
                        text = "lazy",
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Text(text = " dog.", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Text Properties:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Biến thể màu sắc", fontSize = 14.sp)
                    Text("• Độ đậm của chữ (đậm, thường)", fontSize = 14.sp)
                    Text("• Trang trí chữ (gạch chân)", fontSize = 14.sp)
                    Text("• Kiểu chữ (nghiêng)", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Images",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Ảnh đầu tiên từ URL (Logo Giao thông vận tải TP.HCM)
                AsyncImage(
                    model = "https://tuyensinhhuongnghiep.vn/upload/images/2023/09/08/truong-dai-hoc-giao-thong-van-tai-tphcm.jpg",
                    contentDescription = "University Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            item {
                // Thêm một Text với URL để giống với ảnh bạn cung cấp
                Text(
                    text = "https://tuyensinhhuongnghiep.vn/upload/images/2023/09/08/truong-dai-hoc-giao-thong-van-tai-tphcm.jpg",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                )
            }

            item {
                // Ảnh thứ hai từ drawable (ví dụ: R.drawable.building_image)
                // THAY THẾ 'R.drawable.building_image' BẰNG ID THỰC CỦA ẢNH CỦA BẠN TRONG THƯ MỤC DRAWABLE
                Image(
                    painter = painterResource(id = R.drawable.building_image), // Hoặc tên file ảnh của bạn
                    contentDescription = "University Building from local",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                // Thêm Text "In app" như trong ảnh
                Text(
                    text = "In app",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Button Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(
                        color = Color.Cyan,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = "Sample Button",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = {}) {
                    Text("Nút 1")
                }
                Button(onClick = {}) {
                    Text("Nút 2")
                }
                Button(onClick = {}) {
                    Text("Nút 3")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Button Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Xử lý sự kiện nhấn (Click handling)", fontSize = 14.sp)
                    Text("• Các kiểu khác nhau (Filled, Outlined, Text)", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh hình dạng và màu sắc", fontSize = 14.sp)
                    Text("• Trạng thái bật/tắt (Enable/Disable)", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButtonDetailScreen(navController: NavController) {
    val radioOptions = listOf("Option A", "Option B", "Option C")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) } // Mặc định chọn Option A

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "RadioButton Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Selected Option: $selectedOption",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Group of Radio Buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                radioOptions.forEach { text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedOption = text } // Khi click vào hàng, chọn radio button
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = { selectedOption = text }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = text, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "RadioButton Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Chọn độc quyền trong một nhóm (Exclusive selection within a group)", fontSize = 14.sp)
                    Text("• Thuộc tính `selected` để kiểm soát trạng thái", fontSize = 14.sp)
                    Text("• Dùng `onClick` để xử lý thay đổi lựa chọn", fontSize = 14.sp)
                    Text("• Thường dùng với `Row` hoặc `Column` để gom nhóm", fontSize = 14.sp)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxDetailScreen(navController: NavController) {
    var isChecked by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Checkbox Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isChecked) "Checkbox is Checked!" else "Checkbox is Unchecked.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Checkbox Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Hai trạng thái: được chọn / không chọn (checked/unchecked)", fontSize = 14.sp)
                    Text("• Tương tác người dùng với `onCheckedChange`", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh giao diện (appearance)", fontSize = 14.sp)
                    Text("• Có thể là ba trạng thái (tri-state) với `null` cho trạng thái không xác định (indeterminate)", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Icon Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp) // Khoảng cách giữa các phần tử
        ) {
            // Icon cơ bản
            Text(
                text = "Basic Icon",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.Info, // Sử dụng icon Info
                contentDescription = "Information Icon",
                modifier = Modifier.size(64.dp), // Kích thước icon
                tint = MaterialTheme.colorScheme.primary // Màu sắc icon
            )

            // Icon với các màu sắc khác nhau
            Text(
                text = "Icons with Different Colors",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite, // Icon trái tim
                    contentDescription = "Favorite Icon",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Red
                )
                Icon(
                    imageVector = Icons.Default.Star, // Icon ngôi sao
                    contentDescription = "Star Icon",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Yellow
                )
                Icon(
                    imageVector = Icons.Default.Build, // Icon công cụ
                    contentDescription = "Build Icon",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray
                )
            }

            // Icon trong một button (ví dụ)
            Text(
                text = "Icon in a Button",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Button(onClick = { /* Do something */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Item")
            }


            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Icon Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Hiển thị đồ họa vector (Display vector graphics)", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh kích thước và màu phủ (tint)", fontSize = 14.sp)
                    Text("• Truy cập nhiều biểu tượng Material Icons", fontSize = 14.sp)
                    Text("• Có thể dùng độc lập hoặc trong các composable khác", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressBarDetailScreen(navController: NavController) {
    // State cho LinearProgressIndicator có giá trị (determinate)
    var linearProgress by remember { mutableStateOf(0.0f) }

    // State cho LinearProgressIndicator không có giá trị (indeterminate)
    var showIndeterminateLinear by remember { mutableStateOf(false) }

    // State cho CircularProgressIndicator có giá trị (determinate)
    var circularProgress by remember { mutableStateOf(0.0f) }

    // LaunchedEffect để tự động tăng tiến trình và hiển thị indeterminate
    LaunchedEffect(Unit) {
        // Linear Progress (Determinate)
        val job1 = launch {
            while (linearProgress < 1f) {
                delay(100) // Đợi 100ms
                linearProgress += 0.01f // Tăng 1%
            }
        }

        // Circular Progress (Determinate)
        val job2 = launch {
            while (circularProgress < 1f) {
                delay(150) // Đợi 150ms
                circularProgress += 0.005f // Tăng 0.5%
            }
        }

        // Indeterminate Linear Progress (Hiển thị sau một khoảng thời gian)
        val job3 = launch {
            delay(2000) // Đợi 2 giây trước khi hiển thị indeterminate
            showIndeterminateLinear = true
            delay(3000) // Hiển thị 3 giây
            showIndeterminateLinear = false
        }

        // Đảm bảo các coroutine hoàn thành trước khi Composable bị loại bỏ (nếu có)
        job1.join()
        job2.join()
        job3.join()
    }


    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "ProgressBar Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Linear Progress Indicator (Determinate)
            Text(
                text = "Linear Progress (Determinate)",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            LinearProgressIndicator(
                progress = linearProgress, // Giá trị từ 0.0f đến 1.0f
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.tertiary // Màu của thanh tiến trình
            )
            Text(text = "${(linearProgress * 100).toInt()}%")


            // Linear Progress Indicator (Indeterminate)
            Text(
                text = "Linear Progress (Indeterminate)",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            if (showIndeterminateLinear) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary
                )
            } else {
                // Có thể hiển thị một Text hoặc Spacer khi không có progress bar
                Text("Waiting to start indeterminate...", color = Color.Gray)
            }


            // Circular Progress Indicator (Determinate)
            Text(
                text = "Circular Progress (Determinate)",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            CircularProgressIndicator(
                progress = circularProgress,
                modifier = Modifier.size(80.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 6.dp // Độ dày của vòng tròn
            )
            Text(text = "${(circularProgress * 100).toInt()}%")


            // Circular Progress Indicator (Indeterminate)
            Text(
                text = "Circular Progress (Indeterminate)",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp),
                color = MaterialTheme.colorScheme.error // Màu của vòng tròn
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "ProgressBar Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Hiển thị tiến trình cụ thể (determinate)", fontSize = 14.sp)
                    Text("• Hiển thị hoạt động đang diễn ra (indeterminate)", fontSize = 14.sp)
                    Text("• Kiểu tuyến tính và hình tròn (Linear and Circular styles)", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh màu sắc và kích thước", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwitchDetailScreen(navController: NavController) {
    var switchState by remember { mutableStateOf(true) } // Trạng thái của Switch, ban đầu là bật

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Switch Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                    .padding(24.dp)
            ) {
                Text(
                    text = "Bật thông báo:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Switch(
                    checked = switchState,
                    onCheckedChange = { newState ->
                        switchState = newState
                    },
                    // Tùy chỉnh màu sắc của Switch (tùy chọn)
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        uncheckedTrackColor = MaterialTheme.colorScheme.surfaceTint
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = if (switchState) "Thông báo đang bật ON" else "Thông báo đang bật OFF",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (switchState) MaterialTheme.colorScheme.primary else Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Switch Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Chuyển đổi giữa hai trạng thái (bật/tắt)", fontSize = 14.sp)
                    Text("• Thuộc tính `checked` cho trạng thái hiện tại", fontSize = 14.sp)
                    Text("• Hàm callback `onCheckedChange` để xử lý tương tác", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh màu sắc và hình dạng", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderDetailScreen(navController: NavController) {
    // Trạng thái của Slider, giá trị float từ 0.0f đến 1.0f
    var sliderValue by remember { mutableStateOf(0.5f) }
    // Trạng thái của Slider với các bước rời rạc
    var discreteSliderValue by remember { mutableStateOf(50f) }

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Slider Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Slider liên tục (Continuous Slider)
            Text(
                text = "Thanh trượt liên tục: ${"%.2f".format(sliderValue * 100)}%",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Slider(
                value = sliderValue,
                onValueChange = { newValue ->
                    sliderValue = newValue
                },
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            )

            // Slider rời rạc (Discrete Slider)
            Text(
                text = "Thanh trượt rời rạc: ${discreteSliderValue.toInt()}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Slider(
                value = discreteSliderValue,
                onValueChange = { newValue ->
                    discreteSliderValue = newValue
                },
                valueRange = 0f..100f, // Phạm vi giá trị từ 0 đến 100
                steps = 9, // Số bước (tức là 10 giá trị: 0, 10, 20,..., 100)
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.tertiary,
                    activeTrackColor = MaterialTheme.colorScheme.tertiaryContainer,
                    inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            )
            // Thêm Text để hiển thị giá trị cho Discrete Slider
            Text(
                text = "Giá trị hiện tại: ${discreteSliderValue.toInt()}",
                fontSize = 16.sp,
                color = Color.Gray
            )


            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Slider Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Chọn một giá trị trong một phạm vi", fontSize = 14.sp)
                    Text("• Bước nhảy liên tục hoặc rời rạc", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh phạm vi giá trị và bước nhảy", fontSize = 14.sp)
                    Text("• `onValueChangeFinished` để xử lý khi người dùng chọn giá trị cuối cùng", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownDetailScreen(navController: NavController) {
    val coffeeSizes = listOf("Small", "Medium", "Large", "Extra Large")
    var expanded by remember { mutableStateOf(false) }
    var selectedCoffeeSize by remember { mutableStateOf(coffeeSizes[0]) }

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "DropdownMenu Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose your coffee size:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // ExposedDropdownMenuBox
            // Đây là một Dropdown Menu với một TextField hiển thị lựa chọn hiện tại
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                },
                modifier = Modifier.fillMaxWidth(0.8f) // Giới hạn chiều rộng
            ) {
                TextField(
                    // `modifier` là rất quan trọng: Đặt nó là `menuAnchor` để TextField hoạt động như điểm neo cho menu
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true, // Không cho phép nhập liệu trực tiếp
                    value = selectedCoffeeSize,
                    onValueChange = { /* Không làm gì vì là readOnly */ },
                    label = { Text("Coffee Size") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors() // Màu sắc mặc định
                )

                // DropdownMenu chính
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false } // Đóng menu khi click ra ngoài
                ) {
                    coffeeSizes.forEach { size ->
                        DropdownMenuItem(
                            text = { Text(size) },
                            onClick = {
                                selectedCoffeeSize = size
                                expanded = false // Đóng menu sau khi chọn
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "You selected: $selectedCoffeeSize",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "DropdownMenu Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Hiển thị danh sách các tùy chọn", fontSize = 14.sp)
                    Text("• Dùng `ExposedDropdownMenuBox` để tích hợp với TextField", fontSize = 14.sp)
                    Text("• Dùng `DropdownMenuItem` cho từng mục riêng lẻ", fontSize = 14.sp)
                    Text("• Điều khiển bằng trạng thái `expanded`", fontSize = 14.sp)
                    Text("• Tự động đóng khi chọn mục hoặc khi bấm ra ngoài", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailScreen(navController: NavController) {
    // State to hold the text input from the first TextField, mimicking the image
    var enteredText by remember { mutableStateOf("") }
    // This state is not directly used for the image's dynamic text, but kept from original code
    // var text2 by remember { mutableStateOf("Filled text field") }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    "TextField", // Changed title to match the image
                    color = MaterialTheme.colorScheme.primary
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // This TextField closely mimics the one in the image
            OutlinedTextField( // Used OutlinedTextField as it looks closer to the image's design
                value = enteredText,
                onValueChange = { enteredText = it },
                label = { Text("Thông tin nhập") }, // Placeholder text from the image
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Text that updates dynamically based on the input of the above TextField
            Text(
                text = "Tự động cập nhật dữ liệu theo textfield: $enteredText",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Red, // Set color to red as in the image
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal // Or FontWeight.Bold if needed
            )


            // The original second TextField and Card are kept but might not be visible in the image
            // You can remove them if you strictly want to match the image
            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "TextField Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Văn bản gợi ý (Placeholder text)", fontSize = 14.sp)
                    Text("• Trạng thái khi được focus", fontSize = 14.sp)
                    Text("• Hỗ trợ kiểm tra hợp lệ (Validation)", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh kiểu hiển thị (Custom styling)", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldDetailScreen(navController: NavController) {
    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("securepassword") }
    var passwordVisible1 by remember { mutableStateOf(false) }
    var passwordVisible2 by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "PasswordField Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            OutlinedTextField(
                value = password1,
                onValueChange = { password1 = it },
                label = { Text("Nhập mật khẩu...") },
                visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible1)
                        Icons.Default.Visibility
                    else Icons.Default.VisibilityOff

                    IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
                        Icon(imageVector = image, contentDescription = "Toggle password visibility")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password2,
                onValueChange = { password2 = it },
                label = { Text("Mật khẩu an toàn") },
                visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible2)
                        Icons.Default.Visibility
                    else Icons.Default.VisibilityOff

                    IconButton(onClick = { passwordVisible2 = !passwordVisible2 }) {
                        Icon(imageVector = image, contentDescription = "Toggle password visibility")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "PasswordField Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Nhập liệu được che (Masked input)", fontSize = 14.sp)
                    Text("• Chuyển đổi hiển thị/ẩn nội dung", fontSize = 14.sp)
                    Text("• Hiển thị chỉ báo bảo mật", fontSize = 14.sp)
                    Text("• Trạng thái kiểm tra hợp lệ (Validation states)", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Column Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFBBDEFB)
                        )
                    ) {
                        Text(
                            text = "Item 1",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF90CAF9)
                        )
                    ) {
                        Text(
                            text = "Item 2",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF64B5F6)
                        )
                    ) {
                        Text(
                            text = "Item 3",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF42A5F5)
                        )
                    ) {
                        Text(
                            text = "Item 4",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Column Layout:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Sắp xếp theo chiều dọc (Vertical arrangement)", fontSize = 14.sp)
                    Text("• Khoảng cách linh hoạt (Flexible spacing)", fontSize = 14.sp)
                    Text("• Tuỳ chọn căn chỉnh (Alignment options)", fontSize = 14.sp)
                    Text("• Nội dung có thể cuộn (Scrollable content)", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Row Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E8)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFC8E6C9)
                        )
                    ) {
                        Text(
                            text = "Item A",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFA5D6A7)
                        )
                    ) {
                        Text(
                            text = "Item B",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF81C784)
                        )
                    ) {
                        Text(
                            text = "Item C",
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Row Layout:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Sắp xếp theo chiều ngang (Horizontal arrangement)", fontSize = 14.sp)
                    Text("• Phân bố đều hoặc theo trọng số (Equal or weighted distribution)", fontSize = 14.sp)
                    Text("• Căn chỉnh theo trục chéo (Cross-axis alignment)", fontSize = 14.sp)
                    Text("• Kích thước linh hoạt (Flexible sizing)", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpacerDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Spacer Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Spacer trong thực tế (Chiều dọc)",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Vertical Spacer Example
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFE0F7FA), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Item A", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            // Spacer creates vertical space
            Spacer(modifier = Modifier.height(24.dp)) // Creates 24.dp height space
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFE0F7FA), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Item B", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Spacer trong thực tế (Chiều ngang)",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Horizontal Spacer Example
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFFFF3E0), RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFFFFCC80)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("1", color = Color.White)
                }
                // Spacer creates horizontal space
                Spacer(modifier = Modifier.width(16.dp)) // Creates 16.dp width space
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFFFFCC80)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("2", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Spacer Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Tạo khoảng trống giữa các Composable", fontSize = 14.sp)
                    Text("• Có thể đặt kích thước cố định (`.width`, `.height`)", fontSize = 14.sp)
                    Text("• Có thể co giãn linh hoạt (`.weight` trong Row/Column)", fontSize = 14.sp)
                    Text("• Hữu ích để kiểm soát bố cục chính xác mà không cần padding", fontSize = 14.sp)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Card Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp) // Khoảng cách giữa các Card
        ) {
            // Card cơ bản với nội dung đơn giản
            Text(
                text = "Basic Card",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Giới hạn chiều rộng
                    .height(100.dp),
                shape = RoundedCornerShape(12.dp), // Bo tròn góc
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Độ cao (đổ bóng)
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant) // Màu nền của Card
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Đây là một thẻ đơn giản",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Card với hình ảnh và văn bản (thường gặp)
            Text(
                text = "Card with Image and Text",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Ảnh ví dụ (sử dụng một ảnh có sẵn trong drawable hoặc placeholder)
                    // Thay R.drawable.your_image_name bằng tên ảnh thực tế của bạn
                    Image(
                        painter = painterResource(id = R.drawable.card), // Thay đổi thành ảnh của bạn
                        contentDescription = "Sample Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(8.dp)), // Cắt ảnh theo hình dạng bo tròn
                        contentScale = ContentScale.Crop // Căn chỉnh ảnh
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tiêu đề bài viết hấp dẫn",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Đây là phần mô tả ngắn gọn của bài viết, nêu bật một số điểm chính.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        lineHeight = 20.sp
                    )
                    // Thêm nút hoặc hành động khác nếu cần
                    Button(
                        onClick = { /* Do something */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text("Đọc thêm")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Card Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Cung cấp một bề mặt có giới hạn (contained surface)", fontSize = 14.sp)
                    Text("• Tuỳ chỉnh hình dạng, độ nổi (elevation) và màu sắc", fontSize = 14.sp)
                    Text("• Lý tưởng để nhóm các nội dung liên quan", fontSize = 14.sp)
                    Text("• Có thể tương tác (ví dụ: có thể nhấn)", fontSize = 14.sp)
                    Text("• Hỗ trợ biểu tượng, tiêu đề và nội dung văn bản", fontSize = 14.sp)
                    Text("• Dễ kết hợp với Row, Column để bố cục linh hoạt", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconButtonDetailScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Icon Button Detail",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Icon Buttons",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Nút biểu tượng chỉ chứa biểu tượng và đại diện cho một hành động.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Demonstrating Icon Buttons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Thử nhấn các nút biểu tượng dưới đây:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Example 1: Simple Icon Button
                IconButton(onClick = {
                    Toast.makeText(context, "Đã nhấn nút Thích", Toast.LENGTH_SHORT).show()
                    Log.d("ICON_BUTTON_CLICK", "Đã nhấn nút Thích")
                }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Thích")
                }

                // Example 2: Icon Button with text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(onClick = {
                        Toast.makeText(context, "Đã nhấn nút Cài đặt", Toast.LENGTH_SHORT).show()
                        Log.d("ICON_BUTTON_CLICK", "Đã nhấn nút Cài đặt")
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Cài đặt")
                    }
                    Text(text = "Cài đặt")
                }

                // Example 3: Disabled Icon Button
                IconButton(onClick = { /* Do nothing */ }, enabled = false) {
                    Icon(Icons.Default.Delete, contentDescription = "Xóa (Đã vô hiệu hóa)")
                }
                Text(
                    text = "Nút Xóa (Đã vô hiệu hóa)",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Icon Button Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Đại diện cho một hành động cụ thể", fontSize = 14.sp)
                    Text("• Chỉ chứa biểu tượng, không có văn bản trực tiếp (thường)", fontSize = 14.sp)
                    Text("• Thường được sử dụng trong TopAppBar hoặc các không gian nhỏ", fontSize = 14.sp)
                    Text("• Có thể được kích hoạt hoặc vô hiệu hóa", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingActionButtonDetailScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Card Detail", // This title seems to be a placeholder from your original code. You might want to change it to "FAB Detail" or similar.
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        },
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {
                // Normal FAB
                FloatingActionButton(
                    onClick = {
                        // This action will log a message to Logcat and keep you on the same screen.
                        Log.d("FAB_CLICK", "Đã nhấn FAB thường")
                        Toast.makeText(context, "Đã nhấn FAB thường", Toast.LENGTH_SHORT).show() // Optional: You can still show a toast if you want
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Thêm")
                }

                // Extended FAB
                ExtendedFloatingActionButton(
                    onClick = {
                        // This action will log a message to Logcat and keep you on the same screen.
                        Log.d("FAB_CLICK", "Đã nhấn Extended FAB")
                        Toast.makeText(context, "Đã nhấn Extended FAB", Toast.LENGTH_SHORT).show() // Optional: You can still show a toast if you want
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Thêm")
                    Spacer(Modifier.width(8.dp))
                    Text("Thêm mục")
                }

                // Small FAB
                SmallFloatingActionButton(
                    onClick = {
                        // This action will log a message to Logcat and keep you on the same screen.
                        Log.d("FAB_CLICK", "Đã nhấn Small FAB")
                        Toast.makeText(context, "Đã nhấn Small FAB", Toast.LENGTH_SHORT).show() // Optional: You can still show a toast if you want
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Thêm")
                }
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Floating Action Buttons (FABs)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "FAB là nút hành động nổi đại diện cho thao tác chính trên màn hình.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "FAB Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Biểu thị hành động chính của màn hình", fontSize = 14.sp)
                    Text("• Có nhiều loại: Thường, Nhỏ, Mở rộng (Extended)", fontSize = 14.sp)
                    Text("• Luôn nổi bật và dễ tiếp cận", fontSize = 14.sp)
                    Text("• Có thể đi kèm biểu tượng và văn bản", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarDetailScreen(navController: NavController) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Snackbar Detail",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Snackbars",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Snackbar cung cấp một thông báo ngắn gọn về thao tác mà người dùng đã thực hiện.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Demonstrating Snackbar
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Nhấn nút dưới đây để hiển thị Snackbar:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Button to show a simple Snackbar
                Button(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Đã thực hiện một hành động!",
                                duration = SnackbarDuration.Short
                            )
                            Log.d("SNACKBAR_DEMO", "Simple Snackbar shown")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hiển thị Snackbar đơn giản")
                }

                // Button to show a Snackbar with an action
                Button(
                    onClick = {
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Đã xóa mục.",
                                actionLabel = "Hoàn tác",
                                duration = SnackbarDuration.Long
                            )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    Log.d("SNACKBAR_DEMO", "Snackbar Action: Hoàn tác được nhấn")
                                    // Handle undo action here
                                }
                                SnackbarResult.Dismissed -> {
                                    Log.d("SNACKBAR_DEMO", "Snackbar bị đóng")
                                    // Handle snackbar dismissal
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hiển thị Snackbar với hành động")
                }
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Snackbar Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Thông báo ngắn gọn, tạm thời", fontSize = 14.sp)
                    Text("• Xuất hiện ở cuối màn hình", fontSize = 14.sp)
                    Text("• Tự động biến mất sau một thời gian", fontSize = 14.sp)
                    Text("• Có thể chứa một hành động tùy chọn (ví dụ: 'Hoàn tác')", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogDetailScreen(navController: NavController) {
    val context = LocalContext.current
    var showAlertDialog by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Dialog Detail",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Dialogs",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Dialog là các cửa sổ nhỏ xuất hiện phía trên nội dung ứng dụng để thông báo hoặc yêu cầu người dùng đưa ra quyết định.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Demonstrating Dialogs
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Nhấn nút dưới đây để hiển thị Dialog:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Button to show a simple AlertDialog
                Button(
                    onClick = { showAlertDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hiển thị AlertDialog")
                }

                // Button to show a Confirmation Dialog
                Button(
                    onClick = { showConfirmationDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hiển thị Dialog Xác nhận")
                }
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Dialog Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Thông báo quan trọng hoặc yêu cầu quyết định", fontSize = 14.sp)
                    Text("• Có thể chứa tiêu đề, nội dung và các nút hành động", fontSize = 14.sp)
                    Text("• Cần người dùng tương tác để đóng (thường)", fontSize = 14.sp)
                    Text("• Có nhiều loại: AlertDialog, Confirmation Dialog, Full-screen Dialog...", fontSize = 14.sp)
                }
            }
        }
    }

    // AlertDialog implementation
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            title = { Text("Thông báo quan trọng") },
            text = { Text("Đây là một thông báo quan trọng. Vui lòng đọc kỹ nội dung này.") },
            confirmButton = {
                TextButton(onClick = {
                    showAlertDialog = false
                    Log.d("DIALOG_DEMO", "AlertDialog: Đồng ý")
                }) {
                    Text("Đồng ý")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAlertDialog = false
                    Log.d("DIALOG_DEMO", "AlertDialog: Hủy")
                }) {
                    Text("Hủy")
                }
            }
        )
    }

    // Confirmation Dialog implementation
    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text("Xác nhận hành động") },
            text = { Text("Bạn có chắc chắn muốn xóa mục này không? Hành động này không thể hoàn tác.") },
            confirmButton = {
                TextButton(onClick = {
                    showConfirmationDialog = false
                    Log.d("DIALOG_DEMO", "Confirmation Dialog: Xác nhận xóa")
                    // Perform deletion action here
                }) {
                    Text("Xác nhận")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showConfirmationDialog = false
                    Log.d("DIALOG_DEMO", "Confirmation Dialog: Hủy bỏ")
                }) {
                    Text("Hủy bỏ")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDetailScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // We are demonstrating different TopAppBars *within* the content column,
            // so this top bar will be for navigation back to the main screen.
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Top App Bar Examples",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp) // Apply horizontal padding once
                .verticalScroll(rememberScrollState()), // Make the column scrollable
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả chung
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Top App Bars",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Top App Bar hiển thị thông tin và hành động trên cùng của màn hình.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Small Top App Bar ---
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Small Top App Bar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                SmallTopAppBar(
                    title = { Text("Tiêu đề nhỏ") },
                    navigationIcon = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Menu", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Small TopAppBar: Menu clicked")
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Favorite", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Small TopAppBar: Favorite clicked")
                        }) {
                            Icon(Icons.Default.Favorite, contentDescription = "Yêu thích")
                        }
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Share", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Small TopAppBar: Share clicked")
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "Chia sẻ")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Center Aligned Top App Bar ---
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Center Aligned Top App Bar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                CenterAlignedTopAppBar(
                    title = { Text("Tiêu đề căn giữa") },
                    navigationIcon = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Menu", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Center Aligned TopAppBar: Menu clicked")
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Info", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Center Aligned TopAppBar: Info clicked")
                        }) {
                            Icon(Icons.Default.Info, contentDescription = "Thông tin")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Medium Top App Bar ---
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Medium Top App Bar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                MediumTopAppBar(
                    title = { Text("Tiêu đề dài hơn có thể cuộn") },
                    navigationIcon = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Menu", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Medium TopAppBar: Menu clicked")
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Favorite", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Medium TopAppBar: Favorite clicked")
                        }) {
                            Icon(Icons.Default.Favorite, contentDescription = "Yêu thích")
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    scrollBehavior = scrollBehavior // Apply scroll behavior
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Large Top App Bar ---
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Large Top App Bar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LargeTopAppBar(
                    title = { Text("Tiêu đề rất dài có thể cuộn và thu gọn") },
                    navigationIcon = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Menu", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Large TopAppBar: Menu clicked")
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Nhấn Share", Toast.LENGTH_SHORT).show()
                            Log.d("TOP_APP_BAR_DEMO", "Large TopAppBar: Share clicked")
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "Chia sẻ")
                        }
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    scrollBehavior = scrollBehavior // Apply scroll behavior
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Top App Bar Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Hiển thị tiêu đề, điều hướng và hành động chính", fontSize = 14.sp)
                    Text("• Có nhiều kích thước: Small, Center Aligned, Medium, Large", fontSize = 14.sp)
                    Text("• Có thể thu gọn khi cuộn (Medium, Large)", fontSize = 14.sp)
                    Text("• Hỗ trợ các biểu tượng hành động ở bên phải", fontSize = 14.sp)
                }
            }
        }
    }
}


data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationDetailScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    val items = listOf(
        BottomNavItem("Trang chủ", Icons.Default.Home),
        BottomNavItem("Tin nhắn", Icons.Default.MailOutline),
        BottomNavItem("Hồ sơ", Icons.Default.Person),
        BottomNavItem("Cài đặt", Icons.Default.Settings)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Bottom Navigation Detail",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            Toast.makeText(context, "Đã chọn ${item.title}", Toast.LENGTH_SHORT).show()
                            Log.d("BOTTOM_NAV_DEMO", "Selected: ${item.title}")
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Bottom Navigation Bar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Thanh điều hướng dưới cùng cho phép người dùng chuyển đổi giữa 3 đến 5 đích cấp cao nhất.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Content based on selected item
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Take available space
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Bạn đã chọn:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = items[selectedItemIndex].title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Icon(
                    imageVector = items[selectedItemIndex].icon,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Bottom Navigation Bar Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Chuyển đổi giữa các màn hình chính", fontSize = 14.sp)
                    Text("• Luôn hiển thị ở cuối màn hình", fontSize = 14.sp)
                    Text("• Tối đa 5 mục điều hướng", fontSize = 14.sp)
                    Text("• Mỗi mục có biểu tượng và nhãn văn bản", fontSize = 14.sp)
                }
            }
        }
    }
}

data class DrawerItem(
    val title: String,
    val icon: ImageVector,
    val route: String // Not directly used for navigation in this demo, but good practice
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerDetailScreen(navController: NavController) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val drawerItems = listOf(
        DrawerItem("Trang chủ", Icons.Default.Home, "home"),
        DrawerItem("Hộp thư đến", Icons.Default.Email, "inbox"),
        DrawerItem("Cài đặt", Icons.Default.Settings, "settings")
    )

    // The ModalNavigationDrawer wraps the entire screen content
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Menu", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 16.dp))
                    drawerItems.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.title) },
                            icon = { Icon(item.icon, contentDescription = null) },
                            selected = false, // You would manage selected state if this was a primary nav
                            onClick = {
                                scope.launch { drawerState.close() }
                                Toast.makeText(context, "Đã chọn: ${item.title}", Toast.LENGTH_SHORT).show()
                                Log.d("DRAWER_DEMO", "Drawer item clicked: ${item.title}")
                                // In a real app, you would navigate here:
                                // navController.navigate(item.route) { popUpTo(navController.graph.startDestinationId) { saveState = true } }
                                // launchSingleTop = true
                                // restoreState = true
                            }
                        )
                    }
                }
            }
        },
        gesturesEnabled = true // Allows swiping to open/close the drawer
    ) {
        // Scaffold acts as the main content of the screen
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Navigation Drawer Detail",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                // Toggle drawer state
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Mở menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại màn hình trước")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                // Tiêu đề và mô tả
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Navigation Drawer",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Navigation Drawer cung cấp quyền truy cập vào các đích chính của ứng dụng.",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Instructions and a button to open the drawer
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Nhấn biểu tượng menu ở góc trên bên trái hoặc vuốt từ cạnh trái để mở Navigation Drawer.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Button(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Text("Mở Navigation Drawer")
                    }
                }

                // Card mô tả tính năng
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Navigation Drawer Features:",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("• Menu điều hướng ẩn/hiện từ cạnh màn hình", fontSize = 14.sp)
                        Text("• Chứa các liên kết đến các phần chính của ứng dụng", fontSize = 14.sp)
                        Text("• Có thể mở bằng biểu tượng menu hoặc vuốt", fontSize = 14.sp)
                        Text("• Phù hợp cho các ứng dụng có nhiều cấp điều hướng", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxDetailScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Box Layout Detail",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Box Layout",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Box là một Composable bố cục cơ bản cho phép xếp chồng các thành phần lên nhau.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Demonstrating Box with layered elements
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Ví dụ về Box với các thành phần xếp chồng:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Example 1: Simple layering
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.LightGray)
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center // Align content within the Box
                ) {
                    // Bottom layer
                    Spacer(modifier = Modifier.size(100.dp).background(Color.Blue.copy(alpha = 0.7f)))
                    // Top layer
                    Text(
                        text = "Layer 1",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Example 2: More complex layering with different alignments
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.DarkGray)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    // Element aligned to top-start
                    Text(
                        text = "Top-Start",
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                    )
                    // Element centered
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.Green.copy(alpha = 0.8f), RoundedCornerShape(25))
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "Center",
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    // Element aligned to bottom-end
                    Text(
                        text = "Bottom-End",
                        color = Color.Cyan,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    )
                }
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Box Layout Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Xếp chồng các Composable lên nhau", fontSize = 14.sp)
                    Text("• Các thành phần được đặt theo thứ tự khai báo (cái sau đè lên cái trước)", fontSize = 14.sp)
                    Text("• Hỗ trợ các tùy chọn căn chỉnh cho các thành phần con", fontSize = 14.sp)
                    Text("• Thích hợp cho các lớp phủ, biểu tượng trên ảnh, v.v.", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurfaceDetailScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Surface Detail",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Surface Composable",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Surface là một khối xây dựng cơ bản của Material Design, cung cấp màu sắc, độ cao và hình dạng.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Demonstrating Surface with different properties
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Các ví dụ về Surface:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Example 1: Basic Surface with default elevation
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceContainerHigh // A distinct surface color
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Surface\nBasic",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Example 2: Surface with higher tonalElevation
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    tonalElevation = 6.dp, // Higher tonal elevation
                    color = MaterialTheme.colorScheme.surfaceContainerHigh
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Surface\nTonal Elevation (6dp)",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Example 3: Surface with shadowElevation
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 8.dp, // Visible shadow
                    color = MaterialTheme.colorScheme.surfaceContainerHigh
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Surface\nShadow Elevation (8dp)",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Example 4: Surface with custom color
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFE0BBE4), // A custom color
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Surface\nCustom Color",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Surface Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Nền tảng cho các thành phần Material Design", fontSize = 14.sp)
                    Text("• Hỗ trợ các thuộc tính như màu sắc, hình dạng, độ cao (elevation)", fontSize = 14.sp)
                    Text("• `tonalElevation` ảnh hưởng đến màu sắc bề mặt dựa trên màu chủ đề", fontSize = 14.sp)
                    Text("• `shadowElevation` tạo ra bóng đổ vật lý", fontSize = 14.sp)
                    Text("• Thích hợp cho Card, ListItem, v.v.", fontSize = 14.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDetailScreen(navController: NavController) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val drawerState = androidx.compose.material3.rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Drawer Menu", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 16.dp))
                    NavigationDrawerItem(
                        label = { Text("Trang chủ") },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            Toast.makeText(context, "Đã nhấn Trang chủ (Drawer)", Toast.LENGTH_SHORT).show()
                            Log.d("SCAFFOLD_DEMO", "Drawer item: Home clicked")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Cài đặt") },
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            Toast.makeText(context, "Đã nhấn Cài đặt (Drawer)", Toast.LENGTH_SHORT).show()
                            Log.d("SCAFFOLD_DEMO", "Drawer item: Settings clicked")
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Scaffold Detail",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Mở Navigation Drawer")
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                        }
                        IconButton(onClick = {
                            Toast.makeText(context, "Đã nhấn Info", Toast.LENGTH_SHORT).show()
                            Log.d("SCAFFOLD_DEMO", "TopAppBar: Info clicked")
                        }) {
                            Icon(Icons.Default.Info, contentDescription = "Thông tin")
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Đã nhấn Favorite", Toast.LENGTH_SHORT).show()
                            Log.d("SCAFFOLD_DEMO", "BottomAppBar: Favorite clicked")
                        }) {
                            Icon(Icons.Default.Favorite, contentDescription = "Yêu thích")
                        }
                        IconButton(onClick = {
                            Toast.makeText(context, "Đã nhấn Settings", Toast.LENGTH_SHORT).show()
                            Log.d("SCAFFOLD_DEMO", "BottomAppBar: Settings clicked")
                        }) {
                            Icon(Icons.Default.Settings, contentDescription = "Cài đặt")
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Đã nhấn FAB Scaffold!",
                                    actionLabel = "Chi tiết",
                                    duration = SnackbarDuration.Long
                                )
                                when (result) {
                                    SnackbarResult.ActionPerformed -> Log.d("SCAFFOLD_DEMO", "Snackbar Action: Detail clicked")
                                    SnackbarResult.Dismissed -> Log.d("SCAFFOLD_DEMO", "Snackbar dismissed")
                                }
                            }
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Thêm")
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                // Tiêu đề và mô tả
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Scaffold Composable",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Scaffold cung cấp một cấu trúc Material Design cơ bản cho màn hình của bạn.",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Instructions and a button to show Snackbar
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Scaffold tích hợp TopAppBar, BottomAppBar, FAB, Snackbar và Navigation Drawer. Hãy tương tác với các thành phần trên màn hình để thấy chúng hoạt động.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    Button(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Thông báo từ nội dung Scaffold!",
                                duration = SnackbarDuration.Short
                            )
                            Log.d("SCAFFOLD_DEMO", "Snackbar from content clicked")
                        }
                    }) {
                        Text("Hiển thị Snackbar từ nội dung")
                    }
                }

                // Card mô tả tính năng
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Scaffold Features:",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("• Cung cấp khung sườn UI chuẩn Material Design", fontSize = 14.sp)
                        Text("• Các 'slot' cho TopBar, BottomBar, FAB, SnackbarHost, Drawer", fontSize = 14.sp)
                        Text("• Tự động xử lý paddingValues cho nội dung", fontSize = 14.sp)
                        Text("• Đơn giản hóa việc xây dựng cấu trúc màn hình", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnotatedTextDetailScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "AnnotatedString Detail",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Tiêu đề và mô tả
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "AnnotatedString",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "AnnotatedString cho phép bạn áp dụng các kiểu (style) khác nhau cho các phần khác nhau của một chuỗi văn bản.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Demonstrating AnnotatedString
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Ví dụ về văn bản có kiểu dáng:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Example 1: Mixed styles
                Text(
                    text = buildAnnotatedString {
                        append("Đây là một ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                            append("đoạn văn bản in đậm ")
                        }
                        append("và ")
                        withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                            append("nghiêng. ")
                        }
                        append("Bạn có thể tùy chỉnh ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline)) {
                            append("màu sắc và gạch chân")
                        }
                        append(" nữa.")
                    },
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Example 2: Clickable text
                Text(
                    text = "Văn bản có thể nhấp (ClickableText):",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                val annotatedLinkString = buildAnnotatedString {
                    append("Truy cập ")
                    pushStringAnnotation(tag = "URL", annotation = "https://www.google.com")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary, textDecoration = TextDecoration.Underline)) {
                        append("Google")
                    }
                    pop()
                    append(" hoặc gọi ")
                    pushStringAnnotation(tag = "PHONE", annotation = "tel:123456789")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary, fontWeight = FontWeight.Bold)) {
                        append("123-456-789")
                    }
                    pop()
                    append(" để biết thêm.")
                }

                ClickableText(
                    text = annotatedLinkString,
                    modifier = Modifier.fillMaxWidth(),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    onClick = { offset ->
                        annotatedLinkString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                            .firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                if (intent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(intent)
                                } else {
                                    Toast.makeText(context, "Không tìm thấy ứng dụng để mở liên kết", Toast.LENGTH_SHORT).show()
                                }
                            }

                        annotatedLinkString.getStringAnnotations(tag = "PHONE", start = offset, end = offset)
                            .firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(annotation.item))
                                if (intent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(intent)
                                } else {
                                    Toast.makeText(context, "Không tìm thấy ứng dụng để gọi", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                )
            }

            // Card mô tả tính năng
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "AnnotatedString Features:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Áp dụng nhiều kiểu (style) trong một `Text`", fontSize = 14.sp)
                    Text("• Hỗ trợ màu sắc, font, kích thước, gạch chân, v.v.", fontSize = 14.sp)
                    Text("• Cho phép tạo các phần văn bản có thể nhấp (ClickableText)", fontSize = 14.sp)
                    Text("• Rất hữu ích cho văn bản phong phú và liên kết", fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetpackComposeAppPreview() {
    BTVN_Tuan_3Theme {
        JetpackComposeApp()
    }
}