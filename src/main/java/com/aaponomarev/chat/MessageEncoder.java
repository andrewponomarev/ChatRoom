package com.aaponomarev.chat;

import com.alibaba.fastjson.JSON;
import com.aaponomarev.chat.model.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

        @Override
        public String encode(Message message) throws EncodeException {
                return JSON.toJSONString(message);
        }

        @Override
        public void init(EndpointConfig endpointConfig) {
        }

        @Override
        public void destroy() {
        }
}
