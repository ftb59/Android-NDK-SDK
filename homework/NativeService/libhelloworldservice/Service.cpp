#define LOG_TAG "Service"

#include <utils/Log.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <unistd.h>

#include <string.h>
#include <cutils/atomic.h>
#include <utils/Errors.h>
#include <binder/IServiceManager.h>
#include <utils/String16.h>
#include <utils/String8.h>
#include "Service.hh"
#include "utils/Log.h"

#include <unistd.h>

namespace android
{
  void Service::instantiate()
  {
    defaultServiceManager()->addService(IHelloWorld::descriptor,
					new Service());
  }
  
  void Service::sayHello(const char *str)
  {
    printf("%s\n", str);
  }
  
  status_t Service::onTransact(uint32_t code,
					 const Parcel &data,
					 Parcel *reply,
					 uint32_t flags)
  {
    CHECK_INTERFACE(IHelloWorld, data, reply);
    if (code == HW_SAYHELLO)
      {
	if (checkCallingPermission(String16("cn.bjtu.helloworldservice.permissions.CALL_SAYHELLO")) == false)
	  return   PERMISSION_DENIED;
	String16 str = data.readString16();
	sayHello(String8(str).string());
	return NO_ERROR;
      }
    return BBinder::onTransact(code, data, reply, flags);
  }
};
