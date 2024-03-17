package com.app.ticketmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import com.app.ticketmaster.ui.theme.TicketMasterTheme
import com.app.ticketmaster.ui.views.TicketmasterScreen
import com.app.ticketmaster.viewmodel.TicketmasterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: TicketmasterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketMasterTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TicketmasterScreen(viewModel = viewModel)
                }
            }
        }
    }
}
