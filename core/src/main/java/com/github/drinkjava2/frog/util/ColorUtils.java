/* Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.github.drinkjava2.frog.util;

import java.awt.Color;

/**
 * Color Utilities used in this project
 * 
 * @author Yong Zhu
 * @since 1.0
 */
public class ColorUtils {
	public static final int RED = 0;
	public static final int ORANGE = 1;
	public static final int YELLOW = 2;
	public static final int GREEN = 3;
	public static final int CYAN = 4;
	public static final int BLUE = 5;
	public static final int MAGENTA = 6;
	public static final int GRAY = 7;

	private static final Color[] rainbow = new Color[] { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN,
			Color.BLUE, Color.MAGENTA, Color.GRAY };

	private static int nextColor = 0;

	private ColorUtils() {// default private constr
	}

	public static int nextColorCode() {
		return nextColor++;
	}

	public static Color nextRainbowColor() {// 返回下一个彩虹色
		if (nextColor == rainbow.length)
			nextColor = 0;
		return rainbow[nextColor++];
	}

	public static Color colorByCode(int i) {// 数值取模后返回一个固定彩虹色
		return rainbow[i % rainbow.length];
	}

	public static Color rainbowColor(float i) { // 根据数值大小范围，在8种彩虹色中取值
		if (i <= 20)
			return Color.GRAY;
		if (i <= 30)
			return Color.BLACK;
		if (i <= 50)
			return Color.RED;
		return Color.MAGENTA;
	}
}
