/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.plugin.message.listener;

import java.math.BigInteger;

import io.jpress.model.Taxonomy;
import io.jpress.plugin.message.Message;
import io.jpress.plugin.message.MessageAction;
import io.jpress.plugin.message.MessageListener;

public class ContentCountUpdateListener implements MessageListener {
	@Override
	public void onMessage(Message message) {
		BigInteger[] ids = message.getData();
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				Taxonomy t = Taxonomy.DAO.findById(ids[i]);
				long count = t.findContentCount();
				if (count != 0) {
					t.setContentCount(count);
					t.saveOrUpdate();
				}
			}
		}
	}

	@Override
	public void onRegisterAction(MessageAction messageAction) {
		messageAction.register(Actions.CONTENT_COUNT_UPDATE);
	}

}
