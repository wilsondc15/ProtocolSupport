package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_1_5__1_6__1_7;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleScoreboardTeam;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.serializer.StringSerializer;
import protocolsupport.utils.Utils;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ScoreboardTeam extends MiddleScoreboardTeam {

	@Override
	public RecyclableCollection<ClientBoundPacketData> toData(ProtocolVersion version) {
		ClientBoundPacketData serializer = ClientBoundPacketData.create(ClientBoundPacket.PLAY_SCOREBOARD_TEAM_ID, version);
		StringSerializer.writeString(serializer, version, name);
		serializer.writeByte(mode);
		if ((mode == 0) || (mode == 2)) {
			StringSerializer.writeString(serializer, version, displayName);
			StringSerializer.writeString(serializer, version, prefix);
			StringSerializer.writeString(serializer, version, suffix);
			serializer.writeByte(friendlyFire);
		}
		if ((mode == 0) || (mode == 3) || (mode == 4)) {
			serializer.writeShort(players.length);
			for (String player : players) {
				StringSerializer.writeString(serializer, version, Utils.clampString(player, 16));
			}
		}
		return RecyclableSingletonList.<ClientBoundPacketData>create(serializer);
	}

}
