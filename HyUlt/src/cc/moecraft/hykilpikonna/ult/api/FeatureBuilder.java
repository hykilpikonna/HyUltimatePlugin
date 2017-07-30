package cc.moecraft.hykilpikonna.ult.api;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FeatureBuilder
{
    Feature feature;

    public FeatureBuilder()
    {
        feature = new Feature();
    }

    public FeatureBuilder setID(String id)
    {
        feature.setId(id);
        return this;
    }

    public FeatureBuilder setFriendlyName(String friendlyName)
    {
        feature.setFriendlyName(friendlyName);
        return this;
    }

    public FeatureBuilder setCommandRunner(CommandRunner commandRunner)
    {
        feature.setCommandRunner(commandRunner);
        return this;
    }

    public FeatureBuilder setListener(Listener listener)
    {
        feature.setListener(listener);
        return this;
    }

    public FeatureBuilder setPermissionsConfig(PermissionsConfig permissionsConfig)
    {
        feature.setPermissionsConfig(permissionsConfig);
        return this;
    }

    public FeatureBuilder setConfig(Config config)
    {
        feature.setConfig(config);
        return this;
    }

    public FeatureBuilder setDatabase(Database database)
    {
        feature.setDatabase(database);
        return this;
    }

    public FeatureBuilder setMessenger(Messenger messenger)
    {
        feature.setMessenger(messenger);
        return this;
    }

    public Feature build()
    {
        return feature;
    }
}
