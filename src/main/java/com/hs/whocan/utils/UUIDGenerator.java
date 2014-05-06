package com.hs.whocan.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by yinwenbo on 13-12-26.
 */

@Service
public class UUIDGenerator {

    public String uuid(){
        return UUID.randomUUID().toString();
    }

    public String shortUuid(){
        UUID uuid = UUID.randomUUID();
        return shortUuid(uuid);
    }

    public String shortUuid(String uuidString){
        UUID uuid = UUID.fromString(uuidString);
        return shortUuid(uuid);
    }

    public String shortUuid(UUID uuid) {
        byte[] byUuid = new byte[16];
        long most = uuid.getMostSignificantBits();
        long least = uuid.getLeastSignificantBits();

        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);

        return Base64.encodeBase64URLSafeString(byUuid);
    }

    public String revertShortUuid(String shortUuid){
        byte[] byUuid = Base64.decodeBase64(shortUuid.concat("=="));
        long most = bytes2long(byUuid, 0);
        long least = bytes2long(byUuid, 8);
        UUID uuid = new UUID(most, least);
        return uuid.toString();
    }

    protected static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }

    protected static long bytes2long(byte[] bytes, int offset) {
        long value = 0;
        for (int i = 7; i > -1; i--) {
            value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
        }
        return value;
    }


}
