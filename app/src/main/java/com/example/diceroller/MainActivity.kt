package com.example.diceroller
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier){
    var result by remember { mutableStateOf(1) }
    val imageResource = when(result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        /*将一个 modifier 实参传递到 Column() 函数，然后将其设为修饰符值。
        DiceRollerApp()中定义的modifier实参对象 并传递到Column中
        modifier 实参可确保 Column 函数中的可组合项遵守对 modifier 实例调用的约束条件。*/
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
        //这样可确保该列相对于设备屏幕的宽度居中
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
    }
}
@Preview
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage(modifier = Modifier
        /*因此如果布局内的组件小于可用空间，则可以将 Alignment
        对象传递到 wrapContentSize() 方法，以指定组件应如何在可用空间内对齐*/
        .fillMaxSize()              //布局填充整个屏幕
        .wrapContentSize(Alignment.Center)
        //Alignment.Center会指定组件同时在水平和垂直方向上居中
    )
}