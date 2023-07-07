package guillermo.lagos.testfgm.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import guillermo.lagos.domain.Store

@Composable
fun StoreItem(store: Store) {
    Column {
        Text(
            text = "Store Name: ${store.name}",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Store Code: ${store.code}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Store Address: ${store.address}")
    }
}
