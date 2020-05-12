package com.example.prepotency.test;

import java.util.List;

public class RoomInfo {

    /**
     * 房间ID
     */
    public String roomID;
    /**
     * 房间名称
     */
    public String roomName;
    /**
     * 房间名称 房间预览图
     */
    public String roomPreview;
    /**
     * 主播ID
     */
    public String anchorID;
    /**
     * 主播名称
     */
    public String anchorName;
    /**
     * 房间名称
     */
    public String anchorHead;
    /**
     * 主播的拉流地址
     */
    public String mixedPlayURL;
    /**
     * 房间观众列表
     */
    public List<Audience> audiences;
    /**
     * 房间观众数
     */
    public int audienceCount;


    public static class Audience {
        public String userID;     //观众ID
        public String userName;
        public String userAvatar;
    }

}