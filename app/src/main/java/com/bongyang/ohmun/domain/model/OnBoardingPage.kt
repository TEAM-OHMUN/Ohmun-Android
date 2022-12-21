package com.bongyang.ohmun.domain.model

import androidx.annotation.DrawableRes
import com.bongyang.ohmun.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.img_on_boarding_music,
        title = "음악?",
        description = "취향의 음악을 탐험해보세요!"
    )
    object Second: OnBoardingPage(
        image = R.drawable.img_on_boarding_book,
        title = "책?",
        description = "취향의 책을 탐험해보세요!"
    )
    object Third: OnBoardingPage(
        image = R.drawable.img_on_boarding_movie,
        title = "영화 & 드라마?",
        description = "취향의 영화 & 드라마를 탐험해보세요!"
    )
    object Fourth: OnBoardingPage(
        image = R.drawable.satellite,
        title = "오늘의 문화",
        description = "취향의 문화를 탐험해보세요!"
    )

    // TODO 1 : 추후 온보딩 페이지 추가를 위한 코드
}