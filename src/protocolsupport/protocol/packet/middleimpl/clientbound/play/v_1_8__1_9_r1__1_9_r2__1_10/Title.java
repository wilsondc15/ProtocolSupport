package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_1_8__1_9_r1__1_9_r2__1_10;

import io.netty.handler.codec.EncoderException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleTitle;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.serializer.StringSerializer;
import protocolsupport.protocol.serializer.VarNumberSerializer;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class Title extends MiddleTitle {

	@Override
	public RecyclableCollection<ClientBoundPacketData> toData(ProtocolVersion version) {
		if (action == Action.SET_ACTION_BAR) {
			return RecyclableEmptyList.get();
		}
		ClientBoundPacketData serializer = ClientBoundPacketData.create(ClientBoundPacket.PLAY_TITLE_ID, version);
		int actionId = action.ordinal();
		VarNumberSerializer.writeVarInt(serializer, actionId > 2 ? actionId - 1 : actionId);
		switch (action) {
			case SET_TITLE: {
				StringSerializer.writeString(serializer, version, titleJson);
				break;
			}
			case SET_SUBTITLE: {
				StringSerializer.writeString(serializer, version, subtitleJson);
				break;
			}
			case SET_TIMES: {
				serializer.writeInt(fadeIn);
				serializer.writeInt(stay);
				serializer.writeInt(fadeOut);
				break;
			}
			case HIDE:
			case RESET: {
				break;
			}
			default: {
				throw new EncoderException("Should not reach here");
			}
		}
		return RecyclableSingletonList.create(serializer);
	}

}
