package protocolsupport.protocol.utils.datawatcher.objects;

import io.netty.buffer.ByteBuf;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.utils.datawatcher.DataWatcherObject;

public class DataWatcherObjectByte extends DataWatcherObject<Byte> {

	public DataWatcherObjectByte() {
	}

	public DataWatcherObjectByte(byte b) {
		this.value = b;
	}

	@Override
	public void readFromStream(ByteBuf from, ProtocolVersion version) {
		value = from.readByte();
	}

	@Override
	public void writeToStream(ByteBuf to, ProtocolVersion version) {
		to.writeByte(value);
	}

}
