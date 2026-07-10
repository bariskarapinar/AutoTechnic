package com.myapp.autotechnic.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.autotechnic.domain.model.*
import com.myapp.autotechnic.ui.theme.*
import com.myapp.autotechnic.ui.viewmodel.AutoViewModel

@Composable
fun BrandListScreen(viewModel: AutoViewModel, onBrandClick: (Brand) -> Unit) {
    val brands by viewModel.brands.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredBrands = remember(brands, searchQuery) {
        if (searchQuery.isEmpty()) brands else brands.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            VibrantTopBar("AUTO TECHNIC")

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .border(1.dp, NeonBlue.copy(alpha = 0.5f), RoundedCornerShape(24.dp)),
                placeholder = { Text("Search Brand...", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = NeonBlue) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GlassWhite,
                    unfocusedContainerColor = GlassWhite,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Box(modifier = Modifier.weight(1f)) {
                if (isLoading && brands.isEmpty()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = NeonBlue)
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(filteredBrands) { brand ->
                            BrandCard(brand) { onBrandClick(brand) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ModelListScreen(viewModel: AutoViewModel, title: String, onModelClick: (CarModel) -> Unit) {
    val models by viewModel.models.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredModels = remember(models, searchQuery) {
        if (searchQuery.isEmpty()) models else models.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            VibrantTopBar(title)

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .border(1.dp, NeonPurple.copy(alpha = 0.5f), RoundedCornerShape(24.dp)),
                placeholder = { Text("Search Model...", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = NeonPurple) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GlassWhite,
                    unfocusedContainerColor = GlassWhite,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Box(modifier = Modifier.weight(1f)) {
                if (isLoading && models.isEmpty()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = NeonPurple)
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredModels) { model ->
                            ModelRow(model) { onModelClick(model) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GenerationListScreen(viewModel: AutoViewModel, title: String, onGenClick: (Generation) -> Unit) {
    val generations by viewModel.generations.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            VibrantTopBar(title)

            Box(modifier = Modifier.weight(1f)) {
                if (isLoading && generations.isEmpty()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = NeonPink)
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(generations) { gen ->
                            GenerationCard(gen) { onGenClick(gen) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SpecsDetailScreen(viewModel: AutoViewModel, title: String) {
    val specs by viewModel.selectedSpecs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            VibrantTopBar(title)

            Box(modifier = Modifier.weight(1f)) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = NeonBlue)
                } else {
                    specs?.let { s ->
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            item {
                                PerformanceDashboard(s)
                            }
                            
                            item {
                                SpecCategory("TRANSMISSION & DRIVE", Icons.Default.Settings) {
                                    DashboardItem("Gearbox", s.transmission, Icons.Default.SettingsInputComponent)
                                    DashboardItem("Drive", s.driveType, Icons.Default.DirectionsCar)
                                    DashboardItem("Fuel", s.fuelConsumption, Icons.Default.LocalGasStation)
                                }
                            }

                            item {
                                SpecCategory("DIMENSIONS", Icons.Default.OpenWith) {
                                    DashboardItem("Length", s.length, Icons.Default.HorizontalRule)
                                    DashboardItem("Width", s.width, Icons.Default.Straighten)
                                    DashboardItem("Height", s.height, Icons.Default.Height)
                                    DashboardItem("Weight", s.weight, Icons.Default.FitnessCenter)
                                    DashboardItem("Boot", s.bootSpace, Icons.Default.Luggage)
                                }
                            }
                        }
                    } ?: Text("No specs found", modifier = Modifier.align(Alignment.Center), color = Color.White)
                }
            }
        }
    }
}

// --- SUB-COMPONENTS ---

@Composable
fun VibrantTopBar(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Brush.verticalGradient(listOf(NeonBlue.copy(alpha = 0.2f), Color.Transparent)))
            .padding(top = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            letterSpacing = 4.sp
        )
    }
}

@Composable
fun BrandCard(brand: Brand, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() }
            .border(0.5.dp, GlassWhite, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .drawBehind { drawCircle(NeonBlue.copy(alpha = 0.1f), radius = size.minDimension) }
            )
            Text(
                text = brand.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun ModelRow(model: CarModel, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, GlassWhite, RoundedCornerShape(12.dp)),
        color = SurfaceDark
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(model.name, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, tint = NeonPurple, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun GenerationCard(gen: Generation, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(1.dp, NeonPink.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(gen.name, color = NeonPink, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(gen.years, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("View Full Specs", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
            }
        }
    }
}

@Composable
fun PerformanceDashboard(specs: TechnicalSpecs) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Brush.linearGradient(listOf(SurfaceDark, DarkBackground)))
            .border(1.dp, NeonBlue.copy(alpha = 0.4f), RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Speed, contentDescription = null, tint = NeonBlue)
            Spacer(modifier = Modifier.width(8.dp))
            Text("ENGINE & PERFORMANCE", color = NeonBlue, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(specs.engine, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BigStat("POWER", specs.power, NeonPurple)
            BigStat("0-100", specs.acceleration0100, NeonPink)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        DashboardItem("Top Speed", specs.topSpeed, Icons.Default.Flag)
        DashboardItem("Torque", specs.torque, Icons.AutoMirrored.Filled.RotateRight)
    }
}

@Composable
fun BigStat(label: String, value: String, color: Color) {
    Column {
        Text(label, color = color, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(value, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun SpecCategory(title: String, icon: ImageVector, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                content()
            }
        }
    }
}

@Composable
fun DashboardItem(label: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.LightGray.copy(alpha = 0.6f), modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(label, color = Color.LightGray, fontSize = 14.sp)
        }
        Text(value, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}
