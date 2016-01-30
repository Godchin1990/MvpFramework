/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ziyou.tourGuide.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
//		// 注销广播
//		abortBroadcast();
//
//		// 消息id（每条消息都会生成唯一的一个id，目前是SDK生成）
//		String msgId = intent.getStringExtra("msgid");
//		//发送方
//		String username = intent.getStringExtra("from");
//		// 收到这个广播的时候，message已经在db和内存里了，可以通过id获取mesage对象
//		EMMessage message = EMChatManager.getInstance().getMessage(msgId);
//		EMConversation conversation = EMChatManager.getInstance().getConversation(username);
//		// 如果是群聊消息，获取到group id
//		if (message.getChatType() == EMMessage.ChatType.GroupChat) {
//			username = message.getTo();
//		}
//		if (!username.equals(username)) {
//			// 消息不是发给当前会话，return
//			return;
//		}
	}

}
