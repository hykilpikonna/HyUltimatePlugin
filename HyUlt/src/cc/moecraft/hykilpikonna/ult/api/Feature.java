package cc.moecraft.hykilpikonna.ult.api;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Feature
{
    private String id;
    private String friendlyName;
    private CommandRunner commandRunner;
    private Listener listener;
    private Config config;
    private Database database;
    private Messenger messenger;
    private PermissionsConfig permissionsConfig;

    public Feature(CommandRunner commandRunner, Listener listener, Config config, Database database, Messenger messenger, PermissionsConfig permissionsConfig)
    {
        this.messenger = messenger;
        this.config = config;
        this.permissionsConfig = permissionsConfig;
        this.database = database;

        this.commandRunner = commandRunner;
        this.listener = listener;
    }

    public Feature() {}

    public CommandRunner getCommandRunner() {
        return commandRunner;
    }

    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PermissionsConfig getPermissionsConfig() {
        return permissionsConfig;
    }

    public void setPermissionsConfig(PermissionsConfig permissionsConfig) {
        this.permissionsConfig = permissionsConfig;
    }
}
