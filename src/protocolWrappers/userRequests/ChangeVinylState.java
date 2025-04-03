package protocolWrappers.userRequests;

import server.model.Vinyl;

public record ChangeVinylState(Vinyl vinyl, String state)
{

}
