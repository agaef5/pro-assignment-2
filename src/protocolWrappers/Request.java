package protocolWrappers;

public record Request(String handler, String action, Object payload)
{
}
