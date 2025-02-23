/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.material3.samples

import androidx.annotation.Sampled
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
@Sampled
fun PrimaryTextTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")
    Column {
        PrimaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Primary tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun PrimaryIconTabs() {
    var state by remember { mutableStateOf(0) }
    val icons = listOf(Icons.Filled.Favorite, Icons.Filled.Favorite, Icons.Filled.Favorite)
    Column {
        PrimaryTabRow(selectedTabIndex = state) {
            icons.forEachIndexed { index, icon ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    icon = { Icon(icon, contentDescription = "Favorite") }
                )
            }
        }
    }
}

@Preview
@Composable
@Sampled
fun SecondaryTextTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")
    Column {
        SecondaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Secondary tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Sampled
@Composable
fun TextTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")
    Column {
        PrimaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Text tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun SecondaryIconTabs() {
    var state by remember { mutableStateOf(0) }
    val icons = listOf(Icons.Filled.Favorite, Icons.Filled.Favorite, Icons.Filled.Favorite)
    Column {
        SecondaryTabRow(selectedTabIndex = state) {
            icons.forEachIndexed { index, icon ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    icon = { Icon(icon, contentDescription = "Favorite") }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Icon tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun TextAndIconTabs() {
    var state by remember { mutableStateOf(0) }
    val titlesAndIcons = listOf(
        "Tab 1" to Icons.Filled.Favorite,
        "Tab 2" to Icons.Filled.Favorite,
        "Tab 3 with lots of text" to Icons.Filled.Favorite
    )
    Column {
        PrimaryTabRow(selectedTabIndex = state) {
            titlesAndIcons.forEachIndexed { index, (title, icon) ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                    icon = { Icon(icon, contentDescription = null) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Text and icon tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun LeadingIconTabs() {
    var state by remember { mutableStateOf(0) }
    val titlesAndIcons = listOf(
        "Tab" to Icons.Filled.Favorite,
        "Tab & icon" to Icons.Filled.Favorite,
        "Tab 3 with lots of text" to Icons.Filled.Favorite
    )
    Column {
        PrimaryScrollableTabRow(selectedTabIndex = state) {
            titlesAndIcons.forEachIndexed { index, (title, icon) ->
                LeadingIconTab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) },
                    icon = { Icon(icon, contentDescription = null) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Leading icon tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ScrollingPrimaryTextTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        "Tab 1",
        "Tab 2",
        "Tab 3 with lots of text",
        "Tab 4",
        "Tab 5",
        "Tab 6 with lots of text",
        "Tab 7",
        "Tab 8",
        "Tab 9 with lots of text",
        "Tab 10"
    )
    Column {
        PrimaryScrollableTabRow(selectedTabIndex = state, indicator = @Composable { tabPositions ->
            if (state < tabPositions.size) {
                val width by animateDpAsState(targetValue = tabPositions[state].contentWidth)
                TabRowDefaults.PrimaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[state]),
                    width = width
                )
            }
        }) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Scrolling primary tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ScrollingSecondaryTextTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        "Tab 1",
        "Tab 2",
        "Tab 3 with lots of text",
        "Tab 4",
        "Tab 5",
        "Tab 6 with lots of text",
        "Tab 7",
        "Tab 8",
        "Tab 9 with lots of text",
        "Tab 10"
    )
    Column {
        SecondaryScrollableTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Scrolling secondary tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ScrollingTextTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        "Tab 1",
        "Tab 2",
        "Tab 3 with lots of text",
        "Tab 4",
        "Tab 5",
        "Tab 6 with lots of text",
        "Tab 7",
        "Tab 8",
        "Tab 9 with lots of text",
        "Tab 10"
    )
    Column {
        PrimaryScrollableTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Scrolling text tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Sampled
@Composable
fun FancyTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    Column {
        SecondaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                FancyTab(
                    title = title,
                    onClick = { state = index },
                    selected = (index == state)
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Fancy tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Sampled
@Composable
fun FancyIndicatorTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")

    // Reuse the default offset animation modifier, but use our own indicator
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        FancyIndicator(
            MaterialTheme.colorScheme.primary,
            Modifier.tabIndicatorOffset(tabPositions[state])
        )
    }

    Column {
        SecondaryTabRow(
            selectedTabIndex = state,
            indicator = indicator
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Fancy indicator tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Sampled
@Composable
fun FancyIndicatorContainerTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        FancyAnimatedIndicator(tabPositions = tabPositions, selectedTabIndex = state)
    }

    Column {
        SecondaryTabRow(
            selectedTabIndex = state,
            indicator = indicator
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Fancy transition tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ScrollingFancyIndicatorContainerTabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        "Tab 1",
        "Tab 2",
        "Tab 3 with lots of text",
        "Tab 4",
        "Tab 5",
        "Tab 6 with lots of text",
        "Tab 7",
        "Tab 8",
        "Tab 9 with lots of text",
        "Tab 10"
    )
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        FancyAnimatedIndicator(tabPositions = tabPositions, selectedTabIndex = state)
    }

    Column {
        SecondaryScrollableTabRow(
            selectedTabIndex = state,
            indicator = indicator
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(title) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Scrolling fancy transition tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Sampled
@Composable
fun FancyTab(title: String, onClick: () -> Unit, selected: Boolean) {
    Tab(selected, onClick) {
        Column(
            Modifier.padding(10.dp).height(50.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier.size(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        color = if (selected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background
                    )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Sampled
@Composable
fun FancyIndicator(color: Color, modifier: Modifier = Modifier) {
    // Draws a rounded rectangular with border around the Tab, with a 5.dp padding from the edges
    // Color is passed in as a parameter [color]
    Box(
        modifier
            .padding(5.dp)
            .fillMaxSize()
            .border(BorderStroke(2.dp, color), RoundedCornerShape(5.dp))
    )
}

@Sampled
@Composable
fun FancyAnimatedIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
    )
    val transition = updateTransition(selectedTabIndex)
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            // Handle directionality here, if we are moving to the right, we
            // want the right side of the indicator to move faster, if we are
            // moving to the left, we want the left side to move faster.
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            // Handle directionality here, if we are moving to the right, we
            // want the right side of the indicator to move faster, if we are
            // moving to the left, we want the left side to move faster.
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }
    ) {
        tabPositions[it].right
    }

    val indicatorColor by transition.animateColor {
        colors[it % colors.size]
    }

    FancyIndicator(
        // Pass the current color to the indicator
        indicatorColor,
        modifier = Modifier
            // Fill up the entire TabRow, and place the indicator at the start
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            // Apply an offset from the start to correctly position the indicator around the tab
            .offset(x = indicatorStart)
            // Make the width of the indicator follow the animated width as we move between tabs
            .width(indicatorEnd - indicatorStart)
    )
}
