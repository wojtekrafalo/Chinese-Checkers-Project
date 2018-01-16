package client.model;
import common.model.game.*;
import common.utils.Converter;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private ServerHandle serverHandle;
    private LocalSession localSession;
    private List<String> sessions;
    private String nick;
    private int id;

    public Model (String nick, ServerHandle serverHandle) {
        this.nick = nick;
        this.serverHandle = serverHandle;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
    }

    public List<String> getSessions() {
        return sessions;
    }

    public LocalSession getLocalSession() {
        return localSession;
    }

    public void setLocalSession(LocalSession localSession) {
        this.localSession = localSession;
    }
}