package com.pm.primeerp.data.view;

public class ClientStatus {

    private boolean state;
    private String client_id;
    private String remote_id;

    public ClientStatus(boolean state, String client_id, String remote_id) {
        this.state = state;
        this.client_id = client_id;
        this.remote_id = remote_id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRemote_id() {
        return remote_id;
    }

    public void setRemote_id(String remote_id) {
        this.remote_id = remote_id;
    }
}
