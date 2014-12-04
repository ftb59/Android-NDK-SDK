#define LOG_TAG "HelloWorld/Service"

#include <sys/types.h>
#include <unistd.h>
#include <grp.h>

#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <utils/Log.h>

#include "Service.hh"


int	main(int argc, char **argv)
{
  android::Service::instantiate();
  android::ProcessState::self()->startThreadPool();
  android::IPCThreadState::self()->joinThreadPool();

  return(0);
}

