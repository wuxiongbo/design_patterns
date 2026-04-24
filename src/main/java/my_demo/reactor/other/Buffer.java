package my_demo.reactor.other;

import java.nio.ByteBuffer;

/**
 * capacity：总容量
 * position：当前读/写位置
 * limit：当前边界
 */
public class Buffer {
    // 默认为 “写模式”； 此模式简单理解为 待写数据的空数组
    public final ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
    // 默认为 “写模式”； 此模式简单理解为 待写数据的空数组
    public final ByteBuffer outputBuffer = ByteBuffer.allocate(1024);

    public Buffer() {
    }
}