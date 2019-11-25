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
package com.github.drinkjava2.frog.objects;

import com.github.drinkjava2.frog.Env;
import com.github.drinkjava2.frog.Frog;
import com.github.drinkjava2.frog.brain.BrainPicture;
import com.github.drinkjava2.frog.brain.Cell;
import com.github.drinkjava2.frog.brain.organ.Ear;
import com.github.drinkjava2.frog.brain.organ.Eye;
import com.github.drinkjava2.frog.util.StringPixelUtils;

/**
 * ChineseTester used to test test recognition
 *
 * 这是一个临时类，用来测试耳朵对汉字的模式识别及汉字的逆向成像
 * 
 * @author Yong Zhu
 * @since 1.0
 */
public class LetterTester implements EnvObject {
	private static final String STR = "床前明月";

	@Override
	public void build() { // do nothing
	}

	@Override
	public void destory() {// do nothing
	}

	private static final int interval = 80;

	@Override
	public void active(int screen) {
		Frog frog = Env.frogs.get(screen * Env.FROG_PER_SCREEN); // 这个测试只针对每屏的第一只青蛙，因为脑图固定只显示第一只青蛙
		Eye eye = frog.findOrganByName("Eye");
		Ear ear = frog.findOrganByName("Ear");

		int index = Env.step / interval;
		if (Env.step % interval == 0)
			frog.deleteAllPhotons();

		if (index < STR.length()) {
			Cell.blockBackEarPhoton = true;
			BrainPicture.setNote("第" + (index + 1) + "个字训练");
			ear.hearSound(frog, index);
			eye.seeImage(frog, StringPixelUtils.getSanserif12Pixels(STR.substring(index, index + 1)));
		} else {
			int index2 = index % STR.length();
			Cell.blockBackEarPhoton = false;
			BrainPicture.setNote("第" + (index2 + 1) + "个字识别");
			eye.seeImage(frog, StringPixelUtils.getSanserif12Pixels(STR.substring(index2, index2 + 1)));
			if (Env.step % interval > (interval - 2)) {
				int result = ear.readcode(frog);
				System.out.println("result=" + result);
				frog.deleteAllPhotons();
			}
		}
	}

}
