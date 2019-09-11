package abi35_0_0.expo.modules.backgroundfetch;

import android.content.Context;

import java.util.Map;

import abi35_0_0.org.unimodules.core.ExportedModule;
import abi35_0_0.org.unimodules.core.ModuleRegistry;
import abi35_0_0.org.unimodules.core.Promise;
import abi35_0_0.org.unimodules.core.interfaces.ExpoMethod;
import org.unimodules.interfaces.taskManager.TaskManagerInterface;

class BackgroundFetchModule extends ExportedModule {
  private TaskManagerInterface mTaskManager;

  public BackgroundFetchModule(Context context) {
    super(context);
  }

  @Override
  public String getName() {
    return "ExpoBackgroundFetch";
  }

  @Override
  public void onCreate(ModuleRegistry moduleRegistry) {
    mTaskManager = moduleRegistry.getModule(TaskManagerInterface.class);
  }

  @ExpoMethod
  public void registerTaskAsync(String taskName, Map<String, Object> options, final Promise promise) {
    try {
      mTaskManager.registerTask(taskName, BackgroundFetchTaskConsumer.class, options);
      promise.resolve(null);
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ExpoMethod
  public void unregisterTaskAsync(String taskName, final Promise promise) {
    try {
      mTaskManager.unregisterTask(taskName, BackgroundFetchTaskConsumer.class);
      promise.resolve(null);
    } catch (Exception e) {
      promise.reject(e);
    }
  }
}