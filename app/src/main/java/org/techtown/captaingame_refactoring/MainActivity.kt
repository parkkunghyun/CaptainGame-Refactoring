package org.techtown.captaingame_refactoring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.techtown.captaingame_refactoring.ui.theme.CaptainGameRefactoringTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptainGameRefactoringTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CaptainGame()
                }
            }
        }
    }
}

@Composable
fun CaptainGame() {
    var treasuresCnt by remember { mutableStateOf(0) }
    var currentDirection by remember { mutableStateOf("항해 전") }
    var findTreasure = remember { mutableStateOf("아니요") }

    fun isHappen(direction: String) {
        when(Random.nextInt(1,4)) {
            1 -> { // 보물을 발견
                treasuresCnt++
                currentDirection = direction
                findTreasure.value = "보물을 찾았습니다!"
            }
            2 -> { // 폭풍을 만남
                if (treasuresCnt > 0) {
                    treasuresCnt--
                }
                currentDirection = direction
                findTreasure.value = "폭풍을 만났습니다!!"
            }
            3 -> { // 아무일도 안 일어남
                currentDirection = direction
                findTreasure.value = "아무일도 일어나지 않았습니다..."
            }
        }
    }

    Column {
        Text(text = "찾은 보물의 총 개수 : $treasuresCnt")
        Text(text = "현재 배의 방향 : $currentDirection")
        Text(text = "지금 보물을 발견 했나요? : ${findTreasure.value}")

        Spacer(modifier = Modifier.padding(0.dp, 16.dp))
        
        Button(onClick = { isHappen("East") }) {
            Text(text = "Sail East", color = Color.White)
        }
        Button(onClick = { isHappen("West") }) {
            Text(text = "Sail West", color = Color.White)
        }
        Button(onClick = { isHappen("South") }) {
            Text(text = "Sail South", color = Color.White)
        }
        Button(onClick = { isHappen("North") }) {
            Text(text = "Sail North", color = Color.White)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CaptainGamePreView() {
    CaptainGame()
}