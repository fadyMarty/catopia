package com.fadymarty.catopia.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fadymarty.catopia.presentation.onboarding.components.OnBoardingPage
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> "Get Started"
                    1 -> "Next"
                    2 -> "Start Exploring"
                    else -> ""
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row {
            repeat(pages.size) { iteration ->
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.Gray)
                        .size(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val scope = rememberCoroutineScope()

        Button(
            onClick = {
                scope.launch {
                    if (pagerState.currentPage == 2) {
                        viewModel.onEvent(OnBoardingEvent.SaveAppEntry)
                    } else {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            }
        ) {
            Text(text = buttonState.value)
        }
    }
}