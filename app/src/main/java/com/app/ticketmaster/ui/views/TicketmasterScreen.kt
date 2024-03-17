package com.app.ticketmaster.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.ticketmaster.R
import com.app.ticketmaster.model.Event
import com.app.ticketmaster.network.Resource
import com.app.ticketmaster.ui.theme.lightBlueCyan
import com.app.ticketmaster.ui.theme.loadingBackground
import com.app.ticketmaster.viewmodel.TicketmasterViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketmasterScreen(viewModel: TicketmasterViewModel) {
    val eventsResource by viewModel.events.observeAsState(Resource.Loading())
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (eventsResource is Resource.Loading) {
            viewModel.getEvents()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.primary,
                title = {
                    Text(
                        text = stringResource(R.string.ticket_master_events_home),
                        color = lightBlueCyan,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            fontSize = 18.sp,
                        ),
                    )
                },
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SearchBar(
                    searchText = searchText,
                    onSearchTextChanged = { searchText = it },
                    onSearch = {
                        if (searchText.isNotBlank()) {
                            viewModel.getEventsByCity(searchText)
                        } else {
                            viewModel.getEvents()
                        }
                    },
                )

                Spacer(modifier = Modifier.height(16.dp))
                when (val resource = eventsResource) {
                    is Resource.Loading -> CircularProgressIndicator()
                    is Resource.Success -> {
                        if (resource.data.isNullOrEmpty()) {
                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .border(1.dp, MaterialTheme.colorScheme.primary)
                                    .padding(horizontal = 16.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = stringResource(R.string.no_results_found),
                                )
                            }
                        } else {
                            EventList(resource.data)
                        }
                    }

                    is Resource.Error -> {
                        Text(text = "An error occurred: ${resource.message}")
                    }
                }
            }
        },
    )
}

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearch: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { onSearchTextChanged(it) },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                textStyle = TextStyle(color = Color.Gray),
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_events),
                        color = Color.Gray,
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search),
                        tint = Color.Gray,
                    )
                },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { onSearchTextChanged("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(R.string.clear_text),
                                tint = Color.Gray,
                            )
                        }
                    }
                },
            )
            Button(
                onClick = {
                    onSearch()
                    coroutineScope.launch {
                        keyboardController?.hide()
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.search_button_text),
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun EventList(events: List<Event>) {
    LazyColumn {
        items(events) { event ->
            EventItem(event = event)
        }
    }
}

@Composable
fun EventItem(event: Event) {
    var isLoading by remember { mutableStateOf(true) }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateString = event.dates.start.localDate
    val date: Date? = dateFormat.parse(dateString)

    val formattedDate = date?.let {
        SimpleDateFormat("MMM yy", Locale.getDefault()).format(it)
    } ?: stringResource(R.string.invalid_date)

    val cityName = event._embedded.venues[0].city.name
    val stateCode = event._embedded.venues[0].state.stateCode
    val cityText = "$cityName, $stateCode"

    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(loadingBackground),
                contentAlignment = Alignment.Center,
            ) {
                if (event.images.isNotEmpty()) {
                    event.images.firstOrNull()?.let { image ->
                        AsyncImage(
                            model = image.url,
                            contentScale = ContentScale.Crop,
                            contentDescription = stringResource(R.string.event_image_content_description),
                            modifier = Modifier.fillMaxSize(),
                            onSuccess = {
                                isLoading = false
                            },
                        )
                    }
                }

                if (isLoading) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = event.name,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier.padding(top = 16.dp, end = 8.dp),
                )

                Text(
                    text = formattedDate,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontSize = 12.sp,
                        color = Color.Gray,
                    ),
                    modifier = Modifier.padding(top = 4.dp),
                )

                Text(
                    text = event._embedded.venues[0].name,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontSize = 12.sp,
                        color = Color.Gray,
                    ),
                    modifier = Modifier.padding(top = 4.dp),
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
                    text = cityText,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontSize = 12.sp,
                        color = Color.Gray,
                    ),
                )
            }
        }
    }
}
