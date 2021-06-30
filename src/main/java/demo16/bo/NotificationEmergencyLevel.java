package demo16.bo;

// 枚举 通知的紧急程度 ，包括 SEVERE（严重）、URGENCY（紧急）、NORMAL（普通）、TRIVIAL（无关紧要），
// 不同的紧急程度对应不同的发送渠道
public enum NotificationEmergencyLevel {
    URGENCY, // 紧急
    SEVERE   // 严重
}
